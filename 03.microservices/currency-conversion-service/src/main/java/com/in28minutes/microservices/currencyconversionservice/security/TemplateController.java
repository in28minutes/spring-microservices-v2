package com.in28minutes.microservices.currencyconversionservice.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

	@GetMapping("login")
	public String getLoginView() {
		return "login";
	}
	
	@GetMapping("sucess")
	public String getSuccessView() {
		return "sucess";
	}
	
	@GetMapping("logout")
	public String getLogoutView() {
		return "logout";
	}
}
