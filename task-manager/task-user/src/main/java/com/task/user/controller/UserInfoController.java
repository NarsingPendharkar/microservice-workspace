package com.task.user.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.user.entity.UserInfo;
import com.task.user.service.UserInfoService;
import com.task.user.util.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {


	private final UserInfoService userInfoService;
	
	public UserInfoController (UserInfoService userInfo) {
		this.userInfoService=userInfo;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<UserInfo>> createUser(@Valid @RequestBody UserInfo userInfo,
	                                                        HttpServletRequest request) {

	    UserInfo created = userInfoService.createUser(userInfo);

	    String traceId = (String) request.getAttribute("traceId");

	    ApiResponse<UserInfo> response = ApiResponse.success(
	            "User created successfully!",
	            created,
	            HttpStatus.CREATED.value(),
	            request.getRequestURI(),
	            traceId != null ? traceId : UUID.randomUUID().toString()
	    );

	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
    public ResponseEntity<ApiResponse<List<UserInfo>>> getAllUsers(HttpServletRequest request) {
        List<UserInfo> users = userInfoService.getAllUsers();

        ApiResponse<List<UserInfo>> response = ApiResponse.success(
                "All users retrieved successfully!",
                users,
                HttpStatus.OK.value(),
                request.getRequestURI(),
                UUID.randomUUID().toString()
        );

        return ResponseEntity.ok(response);
    }

	@GetMapping("/{id}")
	public ResponseEntity<UserInfo> getUserById(@PathVariable UUID id) {
	    return ResponseEntity.ok(userInfoService.getUserById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserInfo> updateUser(@PathVariable UUID id, @Valid @RequestBody UserInfo userInfo) {
	    return ResponseEntity.ok(userInfoService.updateUser(id, userInfo));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
	    userInfoService.deleteUser(id);
	    return ResponseEntity.noContent().build();
	}

}
