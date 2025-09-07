package org.ecom.userms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class UserConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthTokenFilter authTokenFilter) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Disable CSRF for APIs & H2 console
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/admin/**").hasAuthority("ADMIN")
						.requestMatchers("/api/user/**").hasAuthority("USER")
						.requestMatchers("/api/register", "/api/auth/**", "/h2-console/**").permitAll().anyRequest()
						.authenticated())
				.exceptionHandling(
						exception -> exception.accessDeniedHandler((request, response, accessDeniedException) -> {
							response.setStatus(403);
							response.getWriter().write("You are not authorized to access this resource!");
						}))
				.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
				.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // Important for H2 Console

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
}
