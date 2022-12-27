package ar.com.codoacodo.spring.services;

import java.util.List;
import java.util.Optional;

import ar.com.codoacodo.spring.domain.Users;
import ar.com.codoacodo.spring.dtos.UsersDTO;

public interface UsersService {
	public Optional<Users> obtenerPorId(Long id);
	
	public Users obtenerUsersPorId(Long id);

	public Users findByName(String name);

	public Users save(Users usersDB);

	Users save(Optional<Users> usersDB);

	public void eliminar(Long id);

	public List<Users> findAll();

	public void update(Users usersDB);

	Users save(Users usersDB, UsersDTO usersDTO);

	




	
	
}
