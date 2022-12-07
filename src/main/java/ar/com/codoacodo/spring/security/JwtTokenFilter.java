package ar.com.codoacodo.spring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter{
	
	
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
@Override
protected void doFilterInternal(
		HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
	
	String jwt = getToken(request);
	if (jwt == null) {
		filterChain.doFilter(request, response);
		return;
	}
	if(!this.jwtProvider.validateToken(jwt)) {
		filterChain.doFilter(request, response);
		return;
	}
	//username viene informado en el jwt
	String usernameFromJwtToken = this.jwtProvider.getUsernameFromToken(jwt);
	
	// validar que sea valido
	UserDetails userDetails = myUserDetailsService.loadUserByUsername(usernameFromJwtToken);
	
	//guardar el usuario en el contexto de seguridad
	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
	
	//guardo en el contexto de seguridsad a auth
	SecurityContextHolder.getContext().setAuthentication(auth);
	
	filterChain.doFilter(request, response);
	
}

private String getToken(HttpServletRequest request) {
	String header = request.getHeader("Authorization");
	if(header == null) {
		return header;
	}
		if(!header.startsWith("Bearer") ) {
			return header ;
		}
		return header.replace("Bearer", "");
		
}
}
