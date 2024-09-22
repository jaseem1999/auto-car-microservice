package com.autocar.user.controller.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

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
import org.springframework.web.multipart.MultipartFile;

import com.autocar.user.jwt.JwtRequestFilter;
import com.autocar.user.jwt.JwtUtil;
import com.autocar.user.model.GmailLoginDTO;
import com.autocar.user.model.UserDTO;
import com.autocar.user.model.UserLoginModel;
import com.autocar.user.repository.UserRepository;
import com.autocar.user.services.MyUserDetailsServices;
import com.autocar.user.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserServices userService;
	
	@Autowired
	private BCryptPasswordEncoder encriPasswor;
	
	@Autowired
	private MyUserDetailsServices userDetailsService;
	 
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JwtRequestFilter jwtReq;
	
	@PostMapping("/signup")
	public ResponseEntity<Map<String, String>> signupUser(@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("phone") long phone,
			@RequestParam("img") MultipartFile img){
		
		Map<String, String> map = new HashMap<String, String>();
		UserDTO user = new UserDTO();
		LocalDateTime currentDateTime = LocalDateTime.now();
		user.setEmail(email);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setPhone(phone);
		user.setPassword(encriPasswor.encode(password));
		user.setImg(img.getOriginalFilename());
		
		user.setCreated_at(currentDateTime);
		user.setUpdated_at(currentDateTime);
		user.setCreated_by(0);
		user.setUpdated_by(0);
		user.setStatus((short) 1);
		map=  userService.saveUser(user);
		System.out.println(user);
		
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginModel login){
		Map<String, String> map = new HashMap<String, String>();
		
		
		 try {
	            // Perform authentication
	        	 authenticationManager.authenticate(
	                     new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()))
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
	
	@PutMapping("/change-password")
	public ResponseEntity<Map<String, String>> passwordChange(
			@RequestParam("password") String password,
			@RequestParam("changePassword") String changePassword
			){
		Map<String, String> map = new HashMap<String, String>();
		String jwtemail = jwtReq.getEmail();
		UserDTO user = userRepo.findByEmail(jwtemail);
		if(user != null) {
			if(encriPasswor.matches(password, user.getPassword())) {
				user.setPassword(encriPasswor.encode(changePassword));
				userRepo.save(user);
				map.put("success", "password changed success fully");
				return ResponseEntity.ok(map);
			}else {
				map.put("failed", "re-enter old passwoord");
				return ResponseEntity.ok(map);
			}
		}
		map.put("failed", "unauthrized");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
	}
	
	//Admin get all user
	
	@GetMapping("/admin/get/all")
	public List<UserDTO> adminGetAllUser(){
		List<UserDTO> li = new ArrayList<UserDTO>();
		
		try {
			li = userRepo.findAll();
			return li;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return li;
	}
	
	
	//Option  add new column gmailAccess in userdto
	//if(userdto.gmailaccess == 1)
	//          login and generate token 
	//else normal login
	
	@PostMapping("/gmail/login")
	public ResponseEntity<Map<String, String>> loginWithGmail(
			@RequestBody GmailLoginDTO login
			){
		Map<String, String> map = new HashMap<String, String>();
		UserDTO user = userRepo.findByEmail(login.getGmail());
		LocalDateTime currentTimeDate =  LocalDateTime.now();
		if(user != null) {
			UserDTO gmailUserCreation = new UserDTO();
			gmailUserCreation.setCreated_at(currentTimeDate);
			gmailUserCreation.setUpdated_at(currentTimeDate);
			gmailUserCreation.setCreated_by(0);
			gmailUserCreation.setUpdated_by(0);
			gmailUserCreation.setEmail(login.getGmail());
			gmailUserCreation.setFirstname(login.getUsername());
			gmailUserCreation.setPassword(encriPasswor.encode("asd"));
		}
	
		return ResponseEntity.ok(map);
		
	}
	
}
