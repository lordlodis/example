package com.alphaone.example.book.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.alphaone.example.book.security.jwt.utils.JwtProvider;
import com.alphaone.example.book.security.model.User;
import com.alphaone.example.book.security.repository.UserRepository;

import static org.springframework.security.core.userdetails.User.*;

import java.util.Optional;

@Component
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' does not exist."));
		
		return withUsername(username)
				.password(user.getUserPwd())
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.authorities(user.getRoles())
				.build();				
	}

	/**
	 * @param token
	 * @return UserDetails for the given token
	 */
	public Optional<UserDetails> loadUserByJwtToken(String token) {
		String userName = jwtProvider.getUserName(token);
		return Optional.of(loadUserByUsername(userName));		
	}

}
