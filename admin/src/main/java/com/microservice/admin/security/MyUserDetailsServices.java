package com.microservice.admin.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.admin.model.Admin;
import com.microservice.admin.repository.AdminRepository;


@Service
public class MyUserDetailsServices implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Admin admin= adminRepo.findByEmail(username);
		
		if (admin != null) {
            return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(), new ArrayList<>()); // Add authorities if needed
        } else {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
	}

}
