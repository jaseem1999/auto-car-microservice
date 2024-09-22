package com.autocar.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autocar.user.model.AddressDTO;
import com.autocar.user.model.UserDTO;


@Repository
public interface UserAddressRepository extends JpaRepository<AddressDTO, Long> {

	List<AddressDTO> findByUser(UserDTO user);


}
