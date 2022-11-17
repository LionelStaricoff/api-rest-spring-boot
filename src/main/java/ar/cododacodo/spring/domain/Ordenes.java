package ar.cododacodo.spring.domain;


import java.util.Date;

import lombok.Data;

@Data
public class Ordenes {
	private long id;
	private Date fechaCreacion; 
	private Socios socio;
	private EstadoOrdenes estado;
	private Float montoTotal;
	private Cupones cupon;

}
