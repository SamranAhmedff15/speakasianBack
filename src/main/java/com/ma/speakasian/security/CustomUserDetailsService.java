package com.ma.speakasian.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ma.speakasian.dao.UserRepository;
import com.ma.speakasian.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		User user = userRepository.findByMail(mail);
		CustomUserDetails userDetails = null;
		if(user!= null) {
			userDetails = new CustomUserDetails();
			userDetails.setUser(user);
			return userDetails;
		}
		else {
			throw new UsernameNotFoundException("Adresse mail introuvable");
		}
		
	}
	
	
	

}
