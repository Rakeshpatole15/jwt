package com.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.Repository.UserRepo;
import com.jwt.entity.User;

@RestController
public class home {
	
	@Autowired
	private UserRepo userRepo;
    
	@RequestMapping("/welcome")
	public String welcome() {
		
		String text = "This is an private page ";
		text+="This page is not allowe to Unothenticated Users";
		return text;
	}
	
	
	@RequestMapping("/getUsers")
	public String getUsers() {
		return "{\"name\":\"Rakesh\"}";
	}
	
	@GetMapping("/getUsers")
	public List<User> getUser() {
		return this.userRepo.findAll();
	}
	
	
	
	//Adding the User
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		
		return userRepo.save(user);
	}
	
	//Deleting the Users
	@DeleteMapping("/deleteuser/{id}")
	public String deletUser(@PathVariable int id) {
		 this.userRepo.deleteById(id);
		 return "User Deleted Sucessfully.."; 
	}
	
	//Updating the User
	@PutMapping("/updateUser/{id}")
	public String updateUser(@RequestBody User user, @PathVariable String id) {
		this.userRepo.save(user);
		return"User Udated sucessfully..";
	}
	
	
}
