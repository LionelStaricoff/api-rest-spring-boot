package ar.com.codoacodo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.codoacodo.spring.domain.Ordenes;
@Repository
public interface OrdenesRepository extends JpaRepository<Ordenes, Long>{
// ctr+o+o aparecen todos los metodos que tiene jpaRepository
}
