package com.microservice.admin.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.microservice.admin.model.UserModel;

@FeignClient(name = "user", url = "http://localhost:8102")
public interface UserClient {
	
	@GetMapping("/user/admin/get/all")
	public List<UserModel> findAllUsers();
	
}
