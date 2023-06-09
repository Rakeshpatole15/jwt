package com.jwt.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServices implements UserDetailsService {

	@Override
	
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		if (userName.equals("Rakesh")) {
			
			return new  User("Rakesh", "Rakesh123", new ArrayList<>());
			
		}else {
			throw new UsernameNotFoundException("User Not Found!!"); 
		}
		
	}
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//	    if (userName.equals("Rakesh")) {
//	        return new User("Rakesh", "Rakesh123", new ArrayList<>());
//	    } else {
//	        throw new UsernameNotFoundException("User Not Found!!");
//	    }
//	}


}
