package ar.com.codoacodo.spring.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private Long id;
	
	@Column(name = "role",length = 50,nullable = false,unique = true)
	private String role;
}
