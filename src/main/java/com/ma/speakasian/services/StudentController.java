package com.ma.speakasian.services;

import java.io.Console;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/state")
	public String state(){
		
		return "";

	}
}
