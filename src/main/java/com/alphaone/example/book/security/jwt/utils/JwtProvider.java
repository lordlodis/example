package com.alphaone.example.book.security.jwt.utils;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.alphaone.example.book.security.model.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	
	private final String ROLES_KEY = "roles";
	
	private String secretKey;
	private long validityInMs;
	
	@Autowired
	public JwtProvider(
			@Value("${security.jwt.token.secret-key}") String secretKey, 
			@Value("${security.jwt.token.expiration}") long validityInMs) {
		super();
		this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		this.validityInMs = validityInMs;
	}
		
	/**
	 * @param userName
	 * @param roles
	 * @return The JWT token
	 */
	@SuppressWarnings("deprecation")
	public String createToken(String userName, List<Role> roles) {
		// Add username to the payload
		Claims claims = Jwts.claims().setSubject(userName);
		
		// Convert roles to Spring Security SimpleGrantedAuthority objects,
		// Add Simple Granted Authority objects to claims
		claims.put(ROLES_KEY, roles.stream()
						.map(role -> new SimpleGrantedAuthority(role.getAuthority()))
						.filter(Objects::nonNull)
						.collect(Collectors.toList()));
		
		// Build the token
		Date now = new Date();
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + validityInMs))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	
	/**
	 * Validate the JWT 
	 * @param token
	 * @return true if valid
	 */
	public boolean isValidToken(String token) {
		try {
			Jwts.parserBuilder().build().parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
	
	/**
	 * @param token
	 * @return List of Authority from the given token
	 */
	public List<GrantedAuthority> getRoles(String token) {
		@SuppressWarnings("unchecked")
		List<Map<String, String>> roleClaims = Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.get(ROLES_KEY, List.class);
		
		return roleClaims.stream().map(roleClaim -> 
				new SimpleGrantedAuthority(roleClaim.get("authority")))
				.collect(Collectors.toList());
	}
	
	/**
	 * @param token
	 * @return User name from the token
	 */
	public String getUserName(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
}
