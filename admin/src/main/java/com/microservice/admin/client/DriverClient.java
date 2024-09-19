package com.microservice.admin.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.admin.model.DriverModel;

@FeignClient(name = "driver", url = "http://localhost:8101")
public interface DriverClient {
    @PostMapping("/driver/admin/add")
    ResponseEntity<Map<String, String>> addDriverAdmin(@RequestBody DriverModel driver);
    
    @GetMapping("/driver/admin/get/id")
    public DriverModel getByID(@RequestParam("id") long id);
    
    @GetMapping("/driver/admin/get/all")
    public List<DriverModel> adminGetAllDriver();
}
