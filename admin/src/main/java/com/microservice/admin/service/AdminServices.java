package com.microservice.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.admin.model.Admin;
import com.microservice.admin.repository.AdminRepository;

@Service
public class AdminServices {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public String addAdmin(Admin admin) {
		
		
		try {
			adminRepository.save(admin);
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		
		return null;
	}
}
