package ar.com.codoacodo.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.codoacodo.spring.domain.Users;
import ar.com.codoacodo.spring.repository.UserRepository;

@Service
@Transactional
public class UsersServiceImpl implements UsersService{
	
	//necesito el/los repositorio
	@Autowired
	private UserRepository userRepository;
	
	//abre la coneccion con la db
	public Optional<Users> obtenerPorId(Long id) {
		return this.userRepository.findById(id);
	}

	@Override
	public Users findByName(String name) {
		
		return this.userRepository.findByUsername(name);
	}



	

	@Override
	public Users save(Optional<Users> usersDB) {
		// TODO Auto-generated method stub
		return this.userRepository.save(usersDB);
	}

	@Override
	public Users obtenerUsersPorId(Long id) {
		Optional<Users> ou = this.userRepository.findById(id);
		Users u = Users.builder().id(ou.get().getId())
				.password(ou.get().getPassword())
				.username(ou.get().getUsername())
				.roles(ou.get().getRoles())
				.build();
				
		 return u;
	}

	@Override
	public Users save(Users usersDB) {
		return this.userRepository.save(usersDB);
	}


}
