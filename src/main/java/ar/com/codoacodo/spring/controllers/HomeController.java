package ar.com.codoacodo.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("redirect:/swagger-ui/");
	
	/* metodo anterion para levantar el index en templates
	@RequestMapping("/")
	public String index() {
		return "index";
		//src/main/resources/templates/index.html
		  */
		 
	}
}
