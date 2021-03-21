package com.alphaone.example.book.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alphaone.example.book.security.model.Role;

public interface RoleRepository  extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(String roleName);

}
