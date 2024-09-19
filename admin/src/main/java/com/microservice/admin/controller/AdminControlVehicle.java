package com.microservice.admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.microservice.admin.client.vehicle.VehicleTypeClient;
import com.microservice.admin.jwt.JwtRequestFilter;
import com.microservice.admin.model.Admin;
import com.microservice.admin.model.DriverModel;
import com.microservice.admin.model.VehicleModel;
import com.microservice.admin.model.VehicleTypeModel;
import com.microservice.admin.repository.AdminRepository;

@RestController
@RequestMapping("/admin/control/vehicle")
public class AdminControlVehicle {

	@Autowired
	private VehicleTypeClient vehicleTypeClient;
	
	@Autowired
	private DriverClient driverClient;
	
	@Autowired
	private JwtRequestFilter jwtReq;
	
	@Autowired
	private AdminRepository adminRepo;
	
	
	@PostMapping("/add")
	public ResponseEntity<Map<String, String>> addVehicleType(@RequestParam("vehicleType") String vehicleType) {
		Map<String, String> map = new HashMap<String, String>();
		String jwtemail = jwtReq.getEmail();
		System.out.println(vehicleType);
		
		Admin admin=adminRepo.findByEmail(jwtemail);
		
		if(admin != null) {
			VehicleTypeModel vehicle = new VehicleTypeModel();
			LocalDateTime currentDateTime = LocalDateTime.now();
			
			vehicle.setUpdatedBy((long) 0);
			vehicle.setCreatedBy(admin.getAdminId());
			vehicle.setTypeOfVehicle(vehicleType);
			vehicle.setStatus((short)1);
			vehicle.setCreatedAt(currentDateTime);
			vehicle.setUpdatedAt(currentDateTime);
			
			ResponseEntity<Map<String, String>> response = vehicleTypeClient.addVehicleType(vehicle);
			
			if (response.getStatusCode().is2xxSuccessful()) {
				map.put("success","admin vehicle type add successfully");
	            return ResponseEntity.ok(map);
	        } else {
	        	map.put("failed","admin vehicle_type add failed");
	            return ResponseEntity.status(response.getStatusCode()).body(map);
	        }
		}
		map.put("failed", "failed");
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/get/id")
	public ResponseEntity<VehicleTypeModel> getById(@RequestParam("id") int id) {
	    System.out.println("Requested ID: " + id);
	    
	    VehicleTypeModel vehicleModel = vehicleTypeClient.getByid(id);
	    System.out.println("Retrieved VehicleModel: " + vehicleModel);
	    
	    if (vehicleModel != null) {
	        return ResponseEntity.ok(vehicleModel);
	    } else {
	        return ResponseEntity.notFound().build();  // Return 404 if not found
	    }
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Map<String, String>> addVehicle(
	        @RequestParam("name") String name,
	        @RequestParam("img") MultipartFile img,
	        @RequestParam("reg_number") String reg_number,
	        @RequestParam("expery_reg_date") String expery_reg_date,
	        @RequestParam("engine_number") String engine_number,
	        @RequestParam("brand") String brand,
	        @RequestParam("model") String model,
	        @RequestParam("seat_number") int seat_number,
	        @RequestParam("vehicle_type_id") int vehicle_type_id,
	        @RequestParam("driver_id") long driver_id) {

	    Map<String, String> map = new HashMap<>();
	    VehicleModel vehicle = new VehicleModel();
	    LocalDateTime currentDateTime = LocalDateTime.now();
	    String jwtemail = jwtReq.getEmail();

	    Admin admin = adminRepo.findByEmail(jwtemail);
	    
	    vehicle.setName(name);
	    vehicle.setBrand(brand);
	    vehicle.setReg_number(reg_number);
	    vehicle.setEngin_number(engine_number);

	    // Properly parse and set the expiration registration date
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	    try {
	        java.util.Date utilDate = formatter.parse(expery_reg_date);
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	        vehicle.setExpery_reg_date(sqlDate);
	       
	    } catch (ParseException e) {
	        e.printStackTrace(); // Handle exception
	        map.put("error", "Invalid date format. Please use yyyy-MM-dd");
	        return ResponseEntity.badRequest().body(map);
	    }

	    vehicle.setImg(img.getOriginalFilename());
	    vehicle.setModel(model);
	    vehicle.setSeat_number(seat_number);
	    vehicle.setApprove((short) 1);
	    vehicle.setStatus((short) 1);
	    vehicle.setCreated_at(currentDateTime);
	    vehicle.setUpdated_at(currentDateTime);
	    vehicle.setCreated_by(admin.getAdminId());
	    VehicleTypeModel vehicleType = vehicleTypeClient.getByid(vehicle_type_id);
	    DriverModel driver = driverClient.getByID(driver_id);
	    
	    vehicle.setDriver(driver);
	    vehicle.setVehicle_type(vehicleType);
	    
	    ResponseEntity<Map<String, String>> response =vehicleTypeClient.addVehicle(vehicle);
	    if (response.getStatusCode().is2xxSuccessful()) {
			map.put("success","admin vehicle add successfully");
            return ResponseEntity.ok(map);
        } else {
        	map.put("failed","admin vehicle add failed");
            return ResponseEntity.status(response.getStatusCode()).body(map);
        }
	  
	}
	
	@GetMapping("/get")
	public List<VehicleModel> getAllVehicle(){
		List<VehicleModel> li = new ArrayList<VehicleModel>();
		li=vehicleTypeClient.getAllVehicle();
		return li;
	}


	
}
