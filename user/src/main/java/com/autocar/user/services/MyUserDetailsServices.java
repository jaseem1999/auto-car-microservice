package com.autocar.user.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.autocar.user.model.UserDTO;
import com.autocar.user.repository.UserRepository;

@Service
public class MyUserDetailsServices implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = userRepo.findByEmail(username);
		
		if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>()); // Add authorities if needed
        } else {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
	}

}
