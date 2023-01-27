package ar.com.codoacodo.spring.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "roles")
public class Roles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "role",length = 50,nullable = false,unique = true)
	private String role;
	
	
	public Roles agregarRoles (Long id,String role) {

		Roles r = new Roles(id, role);
		return r;
	}


	public Roles(String role) {
		super();
		this.role = role;
	}


	public Roles(Long id) {
		super();
		this.id = id;
	}
	
}
