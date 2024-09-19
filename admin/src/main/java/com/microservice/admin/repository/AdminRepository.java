package com.microservice.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.admin.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	Admin findByEmailAndPassword(String email, String password);

	Admin findByEmail(String email);
}
