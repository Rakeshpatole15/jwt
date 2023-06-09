 package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.helper.JwtUtil;
import com.jwt.model.jwtRequest;
import com.jwt.model.jwtResponse;
import com.jwt.services.CustomUserDetailsServices;

@RestController
public class jstController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsServices customUserDetailsServices;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody jwtRequest JwtRequest ) throws Exception{
		
    System.out.println(JwtRequest);
    try {
    	
    	this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(JwtRequest.getUsername(), JwtRequest.getPassword()));
    	
    }catch (UsernameNotFoundException e) {
		e.printStackTrace();
		throw new Exception("Bad Credentials");
	}catch (BadCredentialsException e) {
		
		e.printStackTrace();
		throw new Exception("Bad Credetials");
	}
    
   UserDetails userDetails= this.customUserDetailsServices.loadUserByUsername(JwtRequest.getUsername());
   
   String token = this.jwtUtil.generateToken(userDetails);
   System.out.println("JWT "+ token);
   
   return ResponseEntity.ok(new jwtResponse(token));
		
	}

}
