package com.pmrajiv.loginApp.service;

import java.util.List;

import com.pmrajiv.loginApp.dto.Users;

public interface UserService {

	String saveUser(Users userDetails);
	
	Users findUserById(Users userDetails);
	
	List<Users> findUserByEmail(String emailId);
}
