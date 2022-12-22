package ar.com.codoacodo.spring.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "users")
public class Users {
	//ctrl+shift+o
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username",nullable = false, unique = true)	
	private String username;
	
	@Column(name="password",nullable = false)
	private String password;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="users_roles",
		joinColumns = @JoinColumn(name="users_id"),
		inverseJoinColumns = @JoinColumn(name="roles_id")
	)
	private Set<Roles> roles;
}

//ids for this class must be manually assigned before calling save(): ar.com.codoacodo.spring.domain.Roles; nested exception is org.hibernate.id.IdentifierGenerationException: ids for this class must be manually assigned before calling save(): ar.com.codoacodo.spring.domain.Roles
//object references an unsaved transient instance - save the transient instance before flushing
