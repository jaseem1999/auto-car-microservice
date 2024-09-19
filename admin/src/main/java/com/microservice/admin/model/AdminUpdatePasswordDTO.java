package com.microservice.admin.model;

public class AdminUpdatePasswordDTO {
	private String email;
	private String password;
	private String changePassword;
	
	public String getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(String changePassword) {
		this.changePassword = changePassword;
	}

	AdminUpdatePasswordDTO(){
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
