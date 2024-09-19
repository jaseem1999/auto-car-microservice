package com.microservice.admin.model;

public class AdmiJwt {
	
	private final String jwt;

	public AdmiJwt(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	
}
