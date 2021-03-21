package com.alphaone.example.book.security.jwt.utils;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alphaone.example.book.security.model.User;
import com.alphaone.example.book.security.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JwtProviderTest {
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserRepository userRepository;

	@Test
	public void testCreateToken() {
		User user = userRepository.findByUserName("nn").orElseThrow();
		jwtProvider.createToken(user.getUserName(), user.getRoles());
	}
	
}
