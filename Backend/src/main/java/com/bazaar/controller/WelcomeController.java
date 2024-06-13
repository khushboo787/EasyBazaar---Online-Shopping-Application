package com.bazaar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@GetMapping("/")
	public String sayWelcome() {
		return "Welcome to easyBazaar visit notion for more details about APIs : https://www.notion.so/EasyBazaar-com-8813b718c5bd400aac36d0ca2cbf7e08?pvs=4";
	}

}
