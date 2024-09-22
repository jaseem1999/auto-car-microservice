package com.autocar.user.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autocar.user.jwt.JwtRequestFilter;
import com.autocar.user.model.UserCurrentLocationDTO;
import com.autocar.user.model.UserDTO;
import com.autocar.user.repository.UserCurrentLocationRepository;
import com.autocar.user.repository.UserRepository;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserCurrentLocationRepository userCurrentLocationRepository;
	
	@Autowired
	private JwtRequestFilter jwtReq;
	
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/hi")
	public String test() {
		return "success";
	}
	
	//after test pass implementation on user sign up time remove REST API annotation
	@PostMapping("/add/current/location")
	public ResponseEntity<Map<String, String>> addUserCurrentLocation(
			@RequestParam("locationName") String locationName,
			@RequestParam("latitude") BigDecimal latitude,
			@RequestParam("longitude") BigDecimal longitude
			){
		Map<String, String> map = new HashMap<String, String>();
		
		String email=jwtReq.getEmail();
		
		UserDTO user = userRepo.findByEmail(email);
		
		UserCurrentLocationDTO location = new UserCurrentLocationDTO();
		
		if(user != null) {
			LocalDateTime currentDateTime = LocalDateTime.now();
			location.setCreated_at(currentDateTime);
			location.setUpdated_at(null);
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			location.setStatus((short) 1);
			location.setLocation_name(locationName);
			location.setUser(user);
			try {
				userCurrentLocationRepository.save(location);
				map.put("success", "location added");
				return ResponseEntity.ok(map);
			} catch (Exception e) {
				// TODO: handle exception
				map.put("exception",e.getMessage());
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
			}
		}
		map.put("failed", "user not fond");
		return ResponseEntity.ok(map);
	}
	
	//after test pass implementation on user login time remove REST API annotation
	@PutMapping("/update/current/location")
	public ResponseEntity<Map<String, String>> updateUserCurrentLocation(
			@RequestParam("locationName") String locationName,
			@RequestParam("latitude") BigDecimal latitude,
			@RequestParam("longitude") BigDecimal longitude
			){
		Map<String, String> map = new HashMap<String, String>();
		
		String email=jwtReq.getEmail();
		
		UserDTO user = userRepo.findByEmail(email);
		
		UserCurrentLocationDTO location = new UserCurrentLocationDTO();
		
		if(user != null) {
			try {
				location = userCurrentLocationRepository.findByUser(user);
				if(location != null) {
					LocalDateTime currentDateTime = LocalDateTime.now();
					location.setUpdated_at(currentDateTime);
					location.setLatitude(latitude);
					location.setLongitude(longitude);
					location.setStatus((short) 1);
					location.setLocation_name(locationName);
					try {
						userCurrentLocationRepository.save(location);
						map.put("success", "location updated");
						return ResponseEntity.ok(map);
					} catch (Exception e) {
						// TODO: handle exception
						map.put("exception",e.getMessage());
						return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				map.put("exception",e.getMessage());
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
			}
			
			
		}
		map.put("failed", "user not fond");
		return ResponseEntity.ok(map);
	}

}
