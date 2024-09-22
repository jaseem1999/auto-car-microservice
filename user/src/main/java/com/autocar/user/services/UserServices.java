package com.autocar.user.services;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.autocar.user.model.UserDTO;
import com.autocar.user.repository.UserRepository;

import net.bytebuddy.asm.Advice.Return;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepo;
	
	public Map<String, String> saveUser(UserDTO user){
		Map<String, String> map = new HashMap<String, String>();
		if(user != null) {
			try {
				userRepo.save(user);
				map.put("success", "user signup success fully");
				return map;
			} catch (Exception e) {
				map.put("exception", e.getMessage());
				return map;
			}
		}
		map.put("failed","sign up failed");
		return map;
	}
	
	
}
