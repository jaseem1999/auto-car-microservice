package com.autocar.user.controller.user;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autocar.user.jwt.JwtRequestFilter;
import com.autocar.user.model.AddressDTO;
import com.autocar.user.model.UserDTO;
import com.autocar.user.repository.UserAddressRepository;
import com.autocar.user.repository.UserRepository;

@RestController
@RequestMapping("/user/address")
public class UserAddressController {
	
	@Autowired
	private JwtRequestFilter jwtReq;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserAddressRepository addressRepo;

	
	@PostMapping("/add")
	public ResponseEntity<Map<String, String>> addAddress(
			@RequestParam("address_type") String address_type,
			@RequestParam("latitude") BigDecimal latitude,
			@RequestParam("longitude") BigDecimal longitude,
			@RequestParam("location_name") String location_name
			){
		Map<String, String> map = new HashMap<String, String>();
		String jwtemail = jwtReq.getEmail();
		
		UserDTO user = userRepo.findByEmail(jwtemail);
		
		if(user != null) {
			AddressDTO address = new AddressDTO();
			address.setAddress_type(address_type);
			address.setLatitude(latitude);
			address.setLongitude(longitude);
			address.setLocation_name(location_name);
			LocalDateTime currentDateTime = LocalDateTime.now();
			address.setCreated_at(currentDateTime);
			address.setUpdated_at(currentDateTime);
			address.setCreated_by((long) 0);
			address.setCreated_by((long) 0);
			address.setStatus((short) 1);
			address.setUser(user);
			System.out.println(address);
			try {
				addressRepo.save(address);
				map.put("success", "user address added successfully");
				return ResponseEntity.ok(map);
			} catch (Exception e) {
				// TODO: handle exception
				map.put("exception",e.getMessage());
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
			}
			
		}
		
		map.put("failed", "user not found");
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
	}
	
	@GetMapping("/get")
	public List<AddressDTO> getAllAddressByUser(){
		List<AddressDTO> list = new ArrayList<AddressDTO>();
		
		String jwtemail = jwtReq.getEmail();
		UserDTO user = userRepo.findByEmail(jwtemail);
		
		try {
			list = addressRepo.findByUser(user);
			return list;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	@DeleteMapping("/delete/id")
	public ResponseEntity<Map<String, String>> userDeleteAddressByAddressId(@RequestParam("id") long id){
		Map<String, String> map = new HashMap<String, String>();
		String jwtemail = jwtReq.getEmail();
		UserDTO user = userRepo.findByEmail(jwtemail);
		if(user != null) {
			try {
				AddressDTO address = addressRepo.findById(id).orElse(null);
				if(address != null && address.getUser().getUser_id() == user.getUser_id()) {
					try {
						addressRepo.deleteById(id);
						map.put("success", "user address deleted success fully");
						return ResponseEntity.ok(map);
					} catch (Exception e) {
						// TODO: handle exception
						map.put("Exception", e.getMessage());
						return ResponseEntity.ok(map);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				map.put("Exception", e.getMessage());
				return ResponseEntity.ok(map);
			}
			
		}
		
		map.put("failed", "Unauthorized");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
	}
	
}
