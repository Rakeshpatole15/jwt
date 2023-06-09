package com.jwt.model;

public class jwtRequest {
	String username;
	String password;
	
	public jwtRequest() {
		super();
	}

	public jwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "jwtRequest [username=" + username + ", password=" + password + "]";
	}
	
	
	

}
