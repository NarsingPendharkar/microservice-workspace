package org.ecom.userms.controller;

import org.ecom.userms.config.JwtUtils;
import org.ecom.userms.dto.JwtResponse;
import org.ecom.userms.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			System.out.println("1");
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			System.out.println("2");
			String jwt = jwtUtils.generateToken(userDetails);
			System.out.println("3");
			return ResponseEntity.ok(new JwtResponse(jwt));

		} catch (Exception ex) {
			return ResponseEntity.status(401).body("Invalid username or password !");
		}
	}
}
