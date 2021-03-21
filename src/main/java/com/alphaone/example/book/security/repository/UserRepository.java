package com.alphaone.example.book.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alphaone.example.book.security.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * @param userName
	 * @return The user for the given userName
	 */
	Optional<User> findByUserName(String userName);
}
