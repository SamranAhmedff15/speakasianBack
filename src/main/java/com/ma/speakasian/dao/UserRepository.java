package com.ma.speakasian.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ma.speakasian.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByMail(String mail);

}
