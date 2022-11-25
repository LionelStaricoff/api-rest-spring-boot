package ar.com.codoacodo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.codoacodo.spring.domain.Users;
/*
 * repositorio de la entidad User
 * la jpaRepository es una libreria con comandos sql cargados y listos para usar
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

}