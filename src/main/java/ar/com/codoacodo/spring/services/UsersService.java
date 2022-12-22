package ar.com.codoacodo.spring.services;

import java.util.Optional;

import ar.com.codoacodo.spring.domain.Users;

public interface UsersService {
	public Optional<Users> obtenerPorId(Long id);
	
	public Users obtenerUsersPorId(Long id);

	public Users findByName(String name);

	public Users save(Users usersDB);

	Users save(Optional<Users> usersDB);

	




	
	
}
