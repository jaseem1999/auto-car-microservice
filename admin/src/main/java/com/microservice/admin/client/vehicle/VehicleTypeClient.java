package com.microservice.admin.client.vehicle;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.admin.model.VehicleModel;
import com.microservice.admin.model.VehicleTypeModel;

@FeignClient(name = "vehicle", url = "http://localhost:8101")
public interface VehicleTypeClient {

	@PostMapping("/vehicle-type/admin/add")
	public ResponseEntity<Map<String, String>> addVehicleType(@RequestBody VehicleTypeModel vehicleModel);
	
	@GetMapping("/vehicle-type/admin/get/id")
	public VehicleTypeModel getByid(@RequestParam("id") int id);
	
	@PostMapping("/vehicle/admin/add")
	public ResponseEntity<Map<String, String>> addVehicle(@RequestBody VehicleModel vehicle);
	
	@GetMapping("/vehicle/admin/get/all")
	public List<VehicleModel> getAllVehicle();
	
}
