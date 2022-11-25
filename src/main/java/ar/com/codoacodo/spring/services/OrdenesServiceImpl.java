package ar.com.codoacodo.spring.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.cododacodo.spring.domain.Ordenes;
import ar.com.codoacodo.spring.repository.OrdenesRepository;

@Service
@Transactional
public class OrdenesServiceImpl implements OrdenService {
	@Autowired
	private OrdenesRepository repository;

	@Override
	public Ordenes save(Ordenes entity) {
		// TODO Auto-generated method stub
		return this.repository.save(entity);
	}

	@Override
	public Ordenes getById(Long id) {
		
		return this.repository.findById(id).orElse(Ordenes.builder().build());
	}

}