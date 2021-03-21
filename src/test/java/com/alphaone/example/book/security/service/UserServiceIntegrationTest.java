package com.alphaone.example.book.security.service;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.*;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alphaone.example.book.security.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testSignup() {
		Optional<User> user = userService.signup("dummyUsername", "user", "dummyFN", "dummyLN");
		
		assertThat(user.get().getUserPwd(), not("dummyPassword"));
		System.out.println("Encoded password = " + user.get().getUserPwd());		
	}
	
}
