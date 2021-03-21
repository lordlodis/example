package com.alphaone.example.book.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alphaone.example.book.security.jwt.JwtTokenFilter;
import com.alphaone.example.book.security.jwt.utils.JwtProvider;
import com.alphaone.example.book.security.service.UserDetailService;
import com.alphaone.example.book.security.service.UserService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
//		http.formLogin();
//		http.oauth2Login();
		
		http.authorizeRequests()
			.antMatchers("/packages/**").permitAll()
			.antMatchers("/swagger-ui/**").permitAll()
			.antMatchers("/books").permitAll()
			.antMatchers("/users/signin").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/signin").permitAll()
			.anyRequest().authenticated();
		
		http.csrf().disable();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Add filter to handle Jwt token
		http.addFilterBefore(new JwtTokenFilter(userDetailService, userService, jwtProvider), 
				UsernamePasswordAuthenticationFilter.class);
	}

	@Override
    public void configure(WebSecurity web) throws Exception {
        // Allow swagger to be accessed without authentication
        web.ignoring().antMatchers("/v2/api-docs")//
                .antMatchers("/swagger-resources/**")//
                .antMatchers("/swagger-ui.html")//
                .antMatchers("/configuration/**")//
                .antMatchers("/webjars/**")//
                .antMatchers("/public");
    }
	
	/**
	 * Expose AuthenticationManager as Bean
	 */
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	/** Expose PasswordEncoder as a Bean */
	@Bean
	public PasswordEncoder getEncoder() {
	    return new BCryptPasswordEncoder(12);
	}
	
}
