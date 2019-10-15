package com.ma.speakasian.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ma.speakasian.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRole(String role);
}
