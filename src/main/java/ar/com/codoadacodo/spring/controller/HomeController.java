package ar.cododacodo.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.cododacodo.spring.domain.User;

@Controller
public class HomeController {
	

	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
