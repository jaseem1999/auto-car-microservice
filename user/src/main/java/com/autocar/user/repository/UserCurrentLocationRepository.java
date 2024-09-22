package com.autocar.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autocar.user.model.UserCurrentLocationDTO;
import com.autocar.user.model.UserDTO;

@Repository
public interface UserCurrentLocationRepository extends JpaRepository<UserCurrentLocationDTO, Long> {

	UserCurrentLocationDTO findByUser(UserDTO user);

}
