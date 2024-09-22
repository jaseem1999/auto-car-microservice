package com.microservice.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.microservice.admin.client.DriverClient;
import com.microservice.admin.jwt.JwtRequestFilter;
import com.microservice.admin.model.Admin;
import com.microservice.admin.model.DriverModel;
import com.microservice.admin.repository.AdminRepository;


@RestController
@RequestMapping("/admin/control/driver")
public class AdminControlDriverController {
	
	@Autowired
	private JwtRequestFilter jwtReq;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private DriverClient driverClient;

	@PostMapping("/add")
	public ResponseEntity<Map<String, String>> adminAddDriver(@RequestParam("email")String email,
			@RequestParam("password")String password,
			@RequestParam("firstname")String fastname,
			@RequestParam("lastname") String lastname,
			@RequestParam("img")MultipartFile img
			){
		Map<String, String> map = new HashMap<String, String>();
		String jwtemail = jwtReq.getEmail();
		
		Admin admin=adminRepo.findByEmail(jwtemail);
		
		System.out.println(admin);
		DriverModel driver =new DriverModel();
		String file_path = img.getOriginalFilename();
		driver.setEmail(email);
		driver.setFirstName(lastname);
		driver.setLastName(lastname);
		driver.setPassword(password);
		driver.setImg(file_path);
		driver.setCreatedBy(admin.getAdminId());
		driver.setUpdatedBy((long) 0);
		
		
		ResponseEntity<Map<String, String>> response=driverClient.addDriverAdmin(driver);
		
		if (response.getStatusCode().is2xxSuccessful()) {
			map.put("success","admin driver add successfully");
            return ResponseEntity.ok(map);
        } else {
        	map.put("failed","admin driver add failed");
            return ResponseEntity.status(response.getStatusCode()).body(map);
        }
		
	}
	
	@GetMapping("/get/id")
	public DriverModel getDriverById(@RequestParam("id") long id) {
		DriverModel driver = driverClient.getByID(id);
		
		if(driver != null) {
			return driver;
		}else {
			return null;
		}
	}
	
	@GetMapping("/get")
	public List<DriverModel> getAlldriver(){
		List<DriverModel> li = new ArrayList<DriverModel>();
		li=driverClient.adminGetAllDriver();
		return li;
	}
	
}
