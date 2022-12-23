package ar.com.codoacodo.spring.dtos;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
	private Long id;
	private String users_id;
	private String roles_id;
	private String username;
	private String password;
	private Set<String> roles;




}