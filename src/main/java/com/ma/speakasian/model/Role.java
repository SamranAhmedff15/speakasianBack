package com.ma.speakasian.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.beans.factory.annotation.Autowired;

import com.ma.speakasian.dao.RoleRepository;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {
	@Id
	@GeneratedValue
	private Long role_id;
	private String role;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<User> users = new HashSet<>();
}
