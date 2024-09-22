package com.autocar.user.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autocar.user.client.VehicleClient;
import com.autocar.user.model.VehicleModel;

@RestController
@RequestMapping("/user/vehicle")
public class UserManageVehicles {
	
	@Autowired
	private VehicleClient vehicleClient;

	@GetMapping("/get")
	public List<VehicleModel> getAllVehicle() {
		List<VehicleModel> li = new ArrayList<VehicleModel>();
		
		li = vehicleClient.getAllVehicle();
		
		return li;
	}
	
}
