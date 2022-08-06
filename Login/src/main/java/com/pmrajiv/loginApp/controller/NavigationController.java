package com.pmrajiv.loginApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

	@GetMapping("/")
	public String getLogin() {
		return "login/login.html";
	}
	@GetMapping("/signup")
	public String getSignedUp() {
		return "login/signup.html";
	}
//	@GetMapping("/signup?error=true")
//	public String getSignedUpError() {
//		return "login/signup.html";
//	}
	
	@GetMapping("/dashboard")
	public String getDashboard() {
		return "dashboard/welcome.html";
	}
}
