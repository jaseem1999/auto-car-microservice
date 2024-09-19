package com.microservice.admin.controller;

import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.admin.jwt.JwtUtil;
import com.microservice.admin.model.Admin;
import com.microservice.admin.model.AdminLogin;
import com.microservice.admin.model.AdminUpdatePasswordDTO;
import com.microservice.admin.repository.AdminRepository;
import com.microservice.admin.security.MyUserDetailsServices;
import com.microservice.admin.service.AdminServices;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private AdminServices adminService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private MyUserDetailsServices userDetailsService;
	
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder; 
	 
	 @Autowired
	 private AuthenticationManager authenticationManager;
	
	
	 @PostMapping("/add")
	 public ResponseEntity<Map<String, String>> signupAdmin(
	     @RequestBody AdminLogin signup ) {

	     Map<String, String> map = new HashMap<>();
	     Admin admin = new Admin();

	     admin.setEmail(signup.getEmail());
	     admin.setPassword(passwordEncoder.encode(signup.getPasssword()));
	     LocalDateTime currentDateTime = LocalDateTime.now();
	     admin.setCreatedAt(currentDateTime);
	     admin.setUpdatedAt(currentDateTime);
	     admin.setCreatedBy(0L);
	     admin.setStatus((short) 0);
	     admin.setActive((short) 0);

	     if (admin.getEmail().contains("@") && admin.getEmail().contains(".")) {
	         String error = adminService.addAdmin(admin);
	         if (error == null) {
	             map.put("success", "Admin added successfully");
	             return ResponseEntity.ok(map);
	         }
	         map.put("failed", error);
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	     } else {
	         map.put("failed", "Invalid email format");
	         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
	     }
	 }

	
	
	@GetMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody AdminLogin login){
		
		Map<String, String> map = new HashMap<String, String>();
		
	
		
		 try {
	            // Perform authentication
	        	 authenticationManager.authenticate(
	                     new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPasssword()))
	                ;

	            // If authentication is successful, you can return a success message or token
	            map.put("status", "success");
	            map.put("message", "Login successful");
	            
	            final UserDetails userDetails = userDetailsService.loadUserByUsername(login.getEmail());
	            
	            
	            final String jwt = jwtUtil.generateToken(userDetails);
	            map.put("jwt", jwt);

	            return ResponseEntity.ok(map);

	        } catch (org.springframework.security.core.AuthenticationException e) {
	            // Handle authentication failure
	            map.put("status", "error");
	            map.put("message", "Invalid username or password");
	            System.out.println(e);

	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
	        }
	}
	
	
	 // Ensure to configure this bean

	    @PutMapping("/update-password")
	    public ResponseEntity<Map<String, String>> updateAdmin(@RequestBody AdminUpdatePasswordDTO admin) {
	        Map<String, String> map = new HashMap<>();

	        // Hash the new password before saving
	        Admin ad = adminRepo.findByEmail(admin.getEmail());
	        
	        String hashedPassword = passwordEncoder.encode(admin.getChangePassword());
	        ad.setPassword(hashedPassword);
	        ad.setUpdatedAt(LocalDateTime.now());
	        ad.setUpdatedBy(ad.getAdminId()); // Ensure 'updatedBy' is correctly managed

	        adminRepo.save(ad);

	        map.put("success", "Password updated successfully");
	        return ResponseEntity.ok(map);
	    }
	
		
}
