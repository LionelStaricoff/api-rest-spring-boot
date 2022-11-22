package ar.cododacodo.spring.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name="ordenes")
public class Ordenes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Column(name="fecha_creacion")
	private Date fechaCreacion; 
	//relacion muchos a uno
	@ManyToOne
	@JoinColumn(name="socios_id", referencedColumnName = "id")
	private Socios socio;
	@ManyToOne
	@JoinColumn(name="estados_ordenes_id", referencedColumnName = "id")
	private EstadoOrdenes estado;
	@Column(name="monto_total", nullable = false)
	private Float montoTotal;
	@ManyToOne
	@JoinColumn(name="cupones_id", referencedColumnName = "id", nullable=true)
	private Cupones cupon;

}


