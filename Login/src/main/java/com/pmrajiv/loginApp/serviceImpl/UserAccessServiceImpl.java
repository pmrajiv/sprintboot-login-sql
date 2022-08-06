package com.pmrajiv.loginApp.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pmrajiv.loginApp.dto.Users;
import com.pmrajiv.loginApp.service.UserService;

@Service
public class UserAccessServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		List<Users> userDetails = userService.findUserByEmail(username);
		List<SimpleGrantedAuthority> roles=null;
		if(userDetails.isEmpty()) {
			throw new UsernameNotFoundException("User " + username + " was not found");
		}
		else {
			roles =Arrays.asList(new SimpleGrantedAuthority("Admin"));
			return new User(userDetails.get(0).getEmail(),userDetails.get(0).getPassword(),roles);
		}
		
	}

}
