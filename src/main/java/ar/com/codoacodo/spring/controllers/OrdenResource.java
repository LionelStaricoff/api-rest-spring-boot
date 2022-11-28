package ar.com.codoacodo.spring.controllers;

import java.util.Date;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
			this.ordenService.save(ordenDb);
			
		
			
		}
		/*
		ordenDb.setId(ordenDto.getId());
		ordenDb.setMontoTotal(ordenDto.getMontoTotal());
		ordenDb.setSocio(Socios.builder().id(ordenDto.getSocioId()).build() );
		ordenDb.setEstado(EstadoOrdenes.builder().id(ordenDto.getEstadoId()).build() );
		ordenDb.setCupon(Cupones.builder().id(ordenDto.getCuponId()).build());
		ordenDb.setFechaCreacion(new Date());
		*/
		
		//ordenDb.setSocio(ordenDto.getSocioId());
		// no devuelve el objeto completo video 25  minuto 47
		//return ResponseEntity.status(HttpStatus.CREATED).body(ordenDb);
		Ordenes aux = this.ordenService.getById(ordenDb.getId());
		//return ResponseEntity.status(HttpStatus.CREATED).body(aux);
	    return ResponseEntity.ok(aux);
	    //return ResponseEntity.status(HttpStatus.CONFLICT).body(ordenDB);
		
	}
	
	@PutMapping(value= "/orden/{id}")
	public ResponseEntity<?> put(
			@PathVariable(name="id", required=true)
			Long id,
			@RequestBody
			OrdenDTO ordenDto){
		
               //logica
		Ordenes ordenDB = this.ordenService.getById(id);
		//404 no se encontro el recurso/orden/1
		if(ordenDB == null) {
			return ResponseEntity.notFound().build();
		}
		//ctr+shift+i para verificar el resultado de los if()
		//409 conflict || o si el identificador id que pedimos es distinto
		if(ordenDB.isEstadoFinal() || !id.equals(ordenDB.getId()) || ordenDB.isMontoNull() ) {
		//mandando el objeto entero en case de conflicto
		//return ResponseEntity.status(HttpStatus.CONFLICT).body(ordenDB);
			
			//seteando un mapa para no mandar el objeto
			var res = new HashMap<String, String>();
			res.put("code", "409");
			res.put("msj", "ORDEN EN ESTADO INVALIDA");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
		}
		
		// actualizo el o los datos ejemplo estado
		if(ordenDto.getEstadoId() !=null ) {
			EstadoOrdenes nuevoEstados = EstadoOrdenes.builder().id(ordenDto.getEstadoId()).build();
			ordenDB.setEstado(nuevoEstados);
		}
		// si hay mas datos que modificar ejemplo el monto
		//problemas con el 
		if(ordenDto.getMontoTotal() !=null ) {
		Ordenes nuevaOrden = Ordenes.builder().montoTotal(ordenDto.getMontoTotal()).build();
		ordenDB.setMontoTotal(nuevaOrden.getMontoTotal());
		}
		
		this.ordenService.update(ordenDB);
		//tira un error EstadoOrdenes.getEstadoFinal()\" is null
		return ResponseEntity.ok(ordenDB);
	}
	
	}

