package com.pmrajiv.loginApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pmrajiv.loginApp.dto.Users;
import com.pmrajiv.loginApp.repository.UserRepository;
import com.pmrajiv.loginApp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String saveUser(Users userDetails) {
		List<Users> user = findUserByEmail(userDetails.getEmail());
		if(user.size()<1) {
			userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
			try {
				userRepository.save(userDetails);
				return "success";
			}catch(Exception e){
				throw new UsernameNotFoundException("Error creating user");
			}
			
		}
		else return "failed";
	}

	@Override
	public Users findUserById(Users userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> findUserByEmail(String emailId) {
		return userRepository.findByEmail(emailId);
	}

}
