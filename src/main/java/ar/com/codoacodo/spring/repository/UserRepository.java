package ar.com.codoacodo.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.codoacodo.spring.domain.Users;
/*
 * repositorio de la entidad User
 * la jpaRepository es una libreria con comandos sql cargados y listos para usar
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	
	//named method
	//select * from users where nombre = 'jose';
	public Users findByUsername(String name);

	public Users save(Optional<Users> usersDB);
	
	
	//public Users findByUserNameAndLastname(String name);

/* implementar ambos metodos
	//armando una query nativa
	@Query(
			nativeQuery = true,
			value = "SELECT * FROM table WERE name = 'juan'",
			name = "buscarAjuanQuery"
			)
	public Users buscarAjuan();
	
	//query relacionadoa objetos de la tabla
	@Query(
			value = "SELECT * from Users as u  WERE u.username =:name"
			)
	public Users buscarAjuan(String name);
	
*/
}
