package com.autocar.user.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.autocar.user.model.VehicleModel;

@FeignClient(name = "vehicle", url = "http://localhost:8101")
public interface VehicleClient {

	@GetMapping("vehicle/user/get/all")
	public List<VehicleModel> getAllVehicle(); 
	
}
