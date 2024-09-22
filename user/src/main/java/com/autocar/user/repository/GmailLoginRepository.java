package com.autocar.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autocar.user.model.GmailLoginDTO;

@Repository
public interface GmailLoginRepository extends JpaRepository<GmailLoginDTO, Long> {

}
