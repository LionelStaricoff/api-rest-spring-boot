package ar.com.codoacodo.spring.controllers;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.codoacodo.spring.domain.Roles;
import ar.com.codoacodo.spring.domain.Users;
import ar.com.codoacodo.spring.dtos.UsersDTO;
import ar.com.codoacodo.spring.services.UsersService;

@RestController
public class UsersResource {
	/*
	List<Roles> roles =
            Stream.of(new Roles(1l, "guess"),
            		new Roles(1l, "guess")).collect(Collectors.toList()); */
	
	//inyectamos el servicio de usuario
	@Autowired
	private UsersService userService;
	
	@GetMapping(value="/users/{id}")
	public UsersDTO get(
			@PathVariable(name="id", required = true)
			Long id
			) {
		
		
	Optional<Users> users =  this.userService.obtenerPorId(id);
		
		UsersDTO dto = null;
		if(!users.isEmpty()) {
			
		//	Set<Roles> rolesStrs =users.get().getRoles();
					
				
			/*
					users.get(new Roles ())
					
					.getRoles()
					.stream()
					.map(r -> "ROLE_"+r.getRole())
					.collect(Collectors.toSet());
			*/
			dto = UsersDTO.builder()
					.username(users.get().getUsername())
					.roles(users.get().getRoles())
					.build();			
		}
		return dto;
	}
	
	@GetMapping(value= "/users/name/{name}")
	public ResponseEntity<Users> getUserByName(
				@PathVariable(name="name", required=true) String name
			){
		return ResponseEntity.ok(this.userService.findByName(name));
	}
	
	
	
	//si quiero crear usuarios
	//autorizando al usuario ADMIN 
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value="/users")
	public ResponseEntity<Users> post(
		
			
			//@Valid
			@RequestBody UsersDTO usersDTO
			) {
		Users  usersDB = new Users();
		
		
		if(usersDTO.getId() == null){
		   

         
		  
           
			
			usersDB =Users.builder()
					.username(usersDTO.getUsername())
					.password(crearPassword(usersDTO.getPassword()) )
					.roles(usersDTO.getRoles() )   
			        .build() ;
		
			
		try {
			
			usersDB = this.userService.save(usersDB);
		
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
			
			
	
			
		}else {
	  
			try {
				usersDB = this.userService.obtenerUsersPorId(usersDTO.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	
		}
	
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usersDB );
		
		

		
	}
	
	@Bean
	public  static PasswordEncoder PasswordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	
	private  String crearPassword(String password) {
		
		
		 String passwordE = "";
		try {
			 passwordE = new String(PasswordEncode().encode(password).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 return passwordE;
	}

	
	
//eliminar users
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> delete(
			 @PathVariable(name = "id", required = true)
			Long id 
			){
		
		
		// controlando la exception si el objeto no existe
		try {
		this.userService.eliminar(id);
		}catch(RuntimeException re) {
			
			System.out.println(re.getMessage());
		
		}
		var res = new HashMap<String, String>();
		res.put("code", "200");
		res.put("msj", "ORDEN ELIMINADA");
		//return ResponseEntity.ok(res);
		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}

	
	
	
	//traer todos los users
	@GetMapping(value="/users",produces = "application/json")
	public ResponseEntity<List<Users>> findAll() {
		//POST: 200 > OK
		return ResponseEntity.ok(this.userService.findAll());
	}
	
	
	
	//metodo para setear roles, lo uso en crear users
	public static Set<Roles> convertir(String UsersId,String RolesId){
		
		
			Set<Roles> roles = new HashSet<>() ;
			Roles r = new Roles();
			Roles RolesUsersId = new Roles();
				
			if( RolesId == null )
			{r.setRole("2"); 
			}else if(RolesId.toLowerCase() == "admin"){
				r.setRole("1");
			}else {r.setRole("2");  };
			
				
				RolesUsersId.setRole(UsersId);
						
				roles.add(r);
				roles.add(RolesUsersId);
		
				System.out.println("set de roles"+roles);
			return roles;
		}
	
	//metodo para setear roles, lo uso en crear users
	public static Set<Roles> convertir2(Long RolesId,Users usersDB){
		
		  	Set<Roles> roles = new HashSet<>() ;
			Roles uId = new Roles();
			Roles RolesUsersId = new Roles();
				
			
			
			    uId.setId(usersDB.getId());
			// el usersDB.getId es null
				
				RolesUsersId.setRole(RolesId.toString());
						
				roles.add(uId);
				roles.add(RolesUsersId);
		
				System.out.println("set de roles"+roles);
			return roles;
		}
	
	/*
	public Roles buscarRoles(UsersDTO usersDTO, Users  usersDB) {
	Roles RolesUsersId = new Roles();
	var res = new HashMap<String, String>();
	res.put(usersDB.getId().toString(), usersDTO.getRoles().iterator().next());
			RolesUsersId.setRole(res.toString());
		return	RolesUsersId;
	}
	

	*/
}

