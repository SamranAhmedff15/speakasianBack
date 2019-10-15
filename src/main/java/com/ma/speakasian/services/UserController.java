package com.ma.speakasian.services;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ma.speakasian.dao.RoleRepository;
import com.ma.speakasian.dao.UserRepository;
import com.ma.speakasian.model.Role;
import com.ma.speakasian.model.User;
import com.ma.speakasian.security.CustomUserDetailsService;
import com.ma.speakasian.security.JwtRequest;
import com.ma.speakasian.security.JwtResponse;
import com.ma.speakasian.security.JwtTokenUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public User signupUser(@RequestBody User user) throws SQLException {
		try {
			String pwd = user.getPassword();
			String encryptPWD = passwordEncoder.encode(pwd);
			user.setPassword(encryptPWD);
			user.setSignupDate(LocalDate.now());
			Set<Role> roles = new HashSet<>();
			Role role = roleRepository.findByRole("USER");
			roles.add(role);
			user.setRoles(roles);
			return userRepository.saveAndFlush(user);

		} catch (DataAccessException e) {
			return null;
		}

	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String Test() {
//		Role role = new Role();
//		Role role1 = new Role();
//		role.setRole("ADMIN");
//		role1.setRole("User");
//		roleRepository.save(role);
//		roleRepository.save(role1);
		return "ok";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {

		authenticate(user.getMail(), user.getPassword());

		final UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getMail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String mail, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mail, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
