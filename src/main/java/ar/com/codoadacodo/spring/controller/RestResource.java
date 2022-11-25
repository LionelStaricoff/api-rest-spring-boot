package ar.cododacodo.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.cododacodo.spring.domain.User;

@RestController
public class RestResource {
	
	private User user;
	
	@RequestMapping("/home")
	public void home() {
		//this.user.getId();
		System.out.println("escuchando en /");
	}
	

	@RequestMapping("/user/show")
	public User showUser() {
		User u = new User();
		return u;
	}
	
	
}
