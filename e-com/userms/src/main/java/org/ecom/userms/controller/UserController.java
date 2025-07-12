/*
 * 
 */
package org.ecom.userms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.ecom.userms.config.UserConfig;
import org.ecom.userms.model.User;
import org.ecom.userms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private  UserConfig userConfig;
	@Autowired

	private  UserService userService;
	@Autowired

	private  WebClient webClient;
	@Autowired

	private  ObjectMapper mapper;

//     Create new user
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		return ResponseEntity.ok(userService.saveUser(user));
	}

	// Get all users
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	// Get user by ID
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.getUserById(id);
		return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Get user by Email
	@GetMapping("/email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		Optional<User> user = userService.getUserByEmail(email);
		return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}


	@GetMapping("/externalapi")
	public Flux<User> mapUser1() {
		return webClient.get().uri("https://dummyjson.com/users").retrieve().bodyToMono(String.class)
				.doOnNext(json -> System.out.println("Received data: " + json)) // ✅ console log
				.flatMapMany(json -> {
					try {
						JsonNode root = mapper.readTree(json);
						JsonNode usersArray = root.get("users");

						List<User> users = new ArrayList<>();
						for (JsonNode node : usersArray) {
							User user = new User();
							user.setName(node.get("username").asText());
							user.setEmail(node.get("email").asText());
							user.setPassword(node.get("password").asText());
							user.setRole(node.get("role").asText());
							users.add(user);
						}

						return Flux.fromIterable(users); // ✅ returns list to Postman
					} catch (Exception e) {
						return Flux.error(e);
					}
				});
	}

}
