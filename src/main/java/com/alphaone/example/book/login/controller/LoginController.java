package com.alphaone.example.book.login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

//	@Autowired
//	private UserService userService;
//
//	@PostMapping("/signin")
//	public String login(@RequestBody @Valid LoginDto loginDto) {
//		return userService.signin(loginDto.getUserName(), loginDto.getPassword())
//				.orElseThrow(() -> new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed"));
//	}
//
//	@PostMapping("/signup")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@ResponseStatus(HttpStatus.CREATED)
//	public User signup(@RequestBody @Valid LoginDto loginDto) {
//		return userService
//				.signup(loginDto.getUserName(), loginDto.getPassword(), loginDto.getFirstName(), loginDto.getLastName())
//				.orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User already exists"));
//	}
}
