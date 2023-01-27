package ar.com.codoacodo.spring.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.codoacodo.spring.domain.Roles;
import ar.com.codoacodo.spring.domain.Users;
import ar.com.codoacodo.spring.dtos.UsersDTO;
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
	public Users save(Users usersDB,UsersDTO usersDTO)throws NullPointerException {
		
		
		
		this.userRepository.save(usersDB);
		 
		
		 Users ou   = findByName(usersDB.getUsername());
		
		
		Users u = Users.builder().id(ou.getId())
				.password(ou.getPassword())
				.username(ou.getUsername())
				.roles(ou.getRoles())
				//.roles(ou.getRoles()==null? convertir2( ou.getId(), usersDTO.getRoles()) : ou.getRoles())
				.build();
		
		
		
		
		/*
		Set<Roles> rol = new HashSet<>();
		
		 this.userRepository.save(usersDB);
		 usersDB  = findByName(usersDB.getUsername());
		 
		 usersDB.setRoles(Roles.builder().id(usersDB.getId())
				 .role(usersDTO.getRoles().i).build() );;
		 
		 rol.add( new Roles (usersDB.getId(),usersDTO.getRoles().isEmpty()? usersDTO.getRoles().iterator().next().toString() :"ROLE_"+ "1" )) ;
		 usersDB.setRoles(rol);
		 */
		 
		 return this.userRepository.save(u);
	
	}

	@Override
	public void eliminar(Long id) {
		this.userRepository.deleteById(id);
		
	}

	@Override
	public List<Users> findAll() {
		
		return this.userRepository.findAll();
	}

	@Override
	public void update(Users usersDB) {
		 this.userRepository.save(usersDB);
		 
		
	}
	
	
	public static Set<Roles> convertir2(Long RolesId,Long usersDB){
		
	  	Set<Roles> roles = new HashSet<>() ;
		//Roles uId = new Roles();
		Roles RolesUsersId = new Roles();
		var res = new HashMap<String, String>();
		res.put(RolesId.toString(), usersDB.toString());
		
		
		
		   // uId.setId(usersDB);
		// el usersDB.getId es null
			
			try {
				RolesUsersId.setRole( RolesId.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			//roles.agregar(uId,RolesUsersId);
			roles.add(RolesUsersId.toString().equals(res)? 
		RolesUsersId: RolesUsersId.agregarRoles(RolesId, usersDB.toString() ) );
	
			System.out.println("set de roles"+roles);
		return roles;
		
	}
	
	
	
	
	public static Set<Roles> convertir1(Long RolesId,Users usersDB){
		
	  	Set<Roles> roles = new HashSet<>() ;
		Roles uId = new Roles();
		Roles RolesUsersId = new Roles();
			
		
		
		    uId.setId(usersDB.getId());
		// el usersDB.getId es null
			
			try {
				RolesUsersId.setRole( RolesId.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			roles.add(uId);
			roles.add(RolesUsersId);
	
			System.out.println("set de roles"+roles);
		return roles;
	}
	


	@Override
	public Users save(Users usersDB) {
		 return this.userRepository.save(usersDB);
	}

}
