package com.microservice.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.admin.client.UserClient;
import com.microservice.admin.model.UserModel;

@RestController
@RequestMapping("/admin/control/user")
public class AdminControlUser {

	@Autowired
	private UserClient userClient;
	
	@GetMapping("/get/all")
	public List<UserModel> getAllUser(){
		List<UserModel> li = new ArrayList<UserModel>();	
		li=userClient.findAllUsers();
		return li;
	}
	
}
