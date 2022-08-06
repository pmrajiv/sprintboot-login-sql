package com.pmrajiv.loginApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmrajiv.loginApp.dto.Users;
import com.pmrajiv.loginApp.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	  public String greetingSubmit(@ModelAttribute Users users, Model model) {
	    model.addAttribute("users", users);
	    System.out.println(users);
	    if(!users.getName().equals("") && !users.getEmail().equals("") && !users.getPassword().equals("")) {
	    String output = userService.saveUser(users);
	    if(output.equals("success")) {
	    	return "redirect:/";
	    }else {
	    	return "redirect:/signup?error=true";
	    }
	    }else return "redirect:/signup?error=true";
	    
	  }
}
