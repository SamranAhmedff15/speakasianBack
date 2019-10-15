package com.ma.speakasian;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ma.speakasian.dao.RoleRepository;
import com.ma.speakasian.model.Role;

@SpringBootApplication
public class SpeakasianApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeakasianApplication.class, args);
	}

}
