package com.alphaone.example.book.security.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alphaone.example.book.security.jwt.utils.JwtProvider;
import com.alphaone.example.book.security.model.Role;
import com.alphaone.example.book.security.model.User;
import com.alphaone.example.book.security.repository.RoleRepository;
import com.alphaone.example.book.security.repository.UserRepository;

@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	private AuthenticationManager authManager;
	
	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private PasswordEncoder passwordEncoder;
	
	private JwtProvider jwtProvider;
	
	@Autowired
	public UserService(AuthenticationManager authManager, UserRepository userRepository, RoleRepository roleRepository
			, PasswordEncoder passwordEncoder, JwtProvider jwtProvider
			) {
		super();
		this.authManager = authManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtProvider = jwtProvider;
	}
	
	/**
	 * @return All Users in the system
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	/**
	 * @param userName
	 * @return The user for the given name
	 */
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName).orElse(null);
	}

	/**
	 * Performance login
	 * @param userName
	 * @param password
	 * @return The Authentication token
	 */
	public Authentication login(String userName, String password) {
		return authManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
	}	
	
	/**
	 * @param userName
	 * @param password
	 * @return The JWT, or empty if not logged in
	 */
	public Optional<String> signin(String userName, String password) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Loging in for User " + userName);			
		}
		Optional<String> token = Optional.empty();
		Optional<User> user = userRepository.findByUserName(password);
		if (user.isPresent()) {
			try {
				authManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
				token = Optional.of(jwtProvider.createToken(userName, user.get().getRoles()));
			} catch (AuthenticationException e) {
				if (LOGGER.isInfoEnabled()) {
					LOGGER.info("Login failed for User " + userName);			
				}
			}
		}
		return token;
	}
	
	/** Create a new user */
	public Optional<User> signup(String username, String password, String firstName, String lastName) {
		if (!userRepository.findByUserName(username).isPresent()) {
			Role role = roleRepository.findByName("ROLE_USER").orElseThrow();
			return Optional.of(userRepository.save (
						new User(username, passwordEncoder.encode(password), firstName, lastName, role)
					));
		}
		return Optional.empty();
	}
}
