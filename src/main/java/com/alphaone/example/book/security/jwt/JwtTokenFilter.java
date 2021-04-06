package com.alphaone.example.book.security.jwt;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import com.alphaone.example.book.security.jwt.utils.JwtProvider;
import com.alphaone.example.book.security.model.User;
import com.alphaone.example.book.security.service.UserDetailService;
import com.alphaone.example.book.security.service.UserService;

/**
 * Filter used to parser the JWT token and set the authentication context
 * @author Ngoc
 *
 */
public class JwtTokenFilter extends GenericFilterBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);
	@SuppressWarnings("unused")
	private static final String BEARER = "Bearer ";

	private final UserDetailService userDetailService;
	
	private final UserService userService;
	
	private final JwtProvider jwtProvider;
	
	public JwtTokenFilter(UserDetailService userDetailService, UserService userService, JwtProvider jwtProvider) {
		super();
		this.userDetailService = userDetailService;
		this.userService = userService;
		this.jwtProvider = jwtProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Process request to check for a JSON Web Token");
		}
		// Check for Authorization:Bearer JWT
		String headerValue = ((HttpServletRequest) request).getHeader("Authorization");
		
		// FIXME: this getBearerToken now simulates the JWT logic: it add the correct JWT token into the request
		getBearerToken(headerValue).ifPresent(token -> {
			userDetailService.loadUserByJwtToken(token).ifPresent(userDetails -> {
				SecurityContextHolder.getContext().setAuthentication(
						new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
			});
		});

		//move on to the next filter in the chains
        chain.doFilter(request, response);
	}

	/**
     * if present, extract the jwt token from the "Bearer <jwt>" header value.
     *
     * @param headerVal
     * @return jwt if present, empty otherwise
     */
    private Optional<String> getBearerToken(String headerVal) {
//        if (headerVal != null && headerVal.startsWith(BEARER)) {
//            return Optional.of(headerVal.replace(BEARER, "").trim());
//        }
//        return Optional.empty();
    	
    	// Provide dummy impl for token. This is to be replace with a real API Gateway for login
    	User user = userService.findByUserName("mm"); // Get one user to create token
    	return Optional.of(jwtProvider.createToken(user.getUserName(), user.getRoles()));
    }
}
