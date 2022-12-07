package ar.com.codoacodo.spring.security;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.com.codoacodo.spring.domain.Users;
import ar.com.codoacodo.spring.services.UsersService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersService usersService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		//si servicio de acceso a la base
		Users users = this.usersService.findByName(username);
		
		
		//roles del usuario
		Set<SimpleGrantedAuthority> roles = users.getRoles()
		.stream()
		.map(r -> new SimpleGrantedAuthority(r.getRole()))
		.collect(Collectors.toSet());
		
		//org.springframework.security.core.userdetails.UserDetails
		return new User(users.getUsername(), users.getPassword(), roles);
	}

}
