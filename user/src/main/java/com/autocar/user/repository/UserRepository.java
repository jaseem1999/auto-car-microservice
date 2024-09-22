package com.autocar.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autocar.user.model.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

	UserDTO findByEmailAndPassword(String email, String password);

	public UserDTO findByEmail(String email);

}
