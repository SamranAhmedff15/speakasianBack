package com.ma.speakasian.services;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ma.speakasian.model.Student;

@RestController
@RequestMapping("/sensei")
public class SenseiController {

	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/students")
	public String studentList(){
		return "Okay";

	}
}
