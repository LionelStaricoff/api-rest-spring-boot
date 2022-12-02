package ar.com.codoacodo.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity
public class MainSecurity extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtEntryPoint jwtEntryPoint;
	
	@Autowired
	private MyUserDetailsService myUserDetailService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//cargamos los datos de nuestro usuario y nuestro pasword
		auth.userDetailsService(this.myUserDetailService)
		.passwordEncoder(this.PasswordEncoder());
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		//configurando permiso de peticion 
		http
		.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.GET,"/").permitAll() //permite que pasen las peticiones por get directo del root a la base sin autenticar
		.antMatchers(new String[]{"/auth/**","/orden/"}).permitAll()
		.anyRequest().authenticated()
		.and()
		//exception handler > error
		.exceptionHandling().authenticationEntryPoint(this.jwtEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
	
	// antes de cada peticion request debemos meter nuestro filtro jwt
		http.addFilterAfter(this.jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
}
	
	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}
	
	@Bean
	public BCryptPasswordEncoder PasswordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
}
