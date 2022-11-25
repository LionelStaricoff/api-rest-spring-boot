package ar.com.codoacodo.spring.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.codoacodo.spring.domain.Cupones;
import ar.com.codoacodo.spring.domain.EstadoOrdenes;
import ar.com.codoacodo.spring.domain.Ordenes;
import ar.com.codoacodo.spring.domain.Socios;
import ar.com.codoacodo.spring.dtos.OrdenDTO;
import ar.com.codoacodo.spring.services.OrdenService;

@RestController
public class OrdenResource {
	
	@Autowired
	private OrdenService ordenService;
	
    //post a localhost:8080/orden
    // request un archivo json
	/*{
	 *  id:
		titulos:
		precio:
		sociosId
		estadoOrdenId
		montoTotal:
		cuponesId:
	}
	*/
	//si quiero crear ordenes
	@PostMapping(value="/orden")
	public ResponseEntity <Ordenes> post(
			//@Valid
			@RequestBody OrdenDTO ordenDto
			) {
		Ordenes ordenDb=null;
		if(ordenDto.getId() == null){
			ordenDb =Ordenes.builder()
			.montoTotal(ordenDto.getMontoTotal())
			.socio(Socios.builder().id(ordenDto.getSocioId()).build())
			.estado(EstadoOrdenes.builder().id(ordenDto.getEstadoId()).build())
			.cupon(ordenDto.getCuponId() != null ? Cupones.builder().id(ordenDto.getCuponId()).build() : null)
			.fechaCreacion(new Date())  // ahora se esta crreando
			.build();
			ordenDb =this.ordenService.save(ordenDb);
		}
		ordenDb.setId(ordenDto.getId());
		ordenDb.setMontoTotal(ordenDto.getMontoTotal());
		//ordenDb.setSocio(ordenDto.getSocioId());
		// no devuelve el objeto completo video 25  minuto 47
		return ResponseEntity.status(HttpStatus.CREATED).body(ordenDb);
		
	}
	
	}

