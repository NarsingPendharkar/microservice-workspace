package com.task.user.controller;

import java.util.List;

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

import com.task.user.dto.UserConstants;
import com.task.user.entity.UserInfo;
import com.task.user.service.UserInfoService;
import com.task.user.util.ApiResponse;
import com.task.user.util.TraceIdUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {
	private final UserInfoService userInfoService;

	public UserInfoController(UserInfoService userInfo) {
		this.userInfoService = userInfo;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<UserInfo>> createUser(@Valid @RequestBody UserInfo userInfo
			) {
		UserInfo created = userInfoService.createUser(userInfo);
		ApiResponse<UserInfo> response = ApiResponse.success(
				UserConstants.SUCCESS_CREATE_USER,
				created,
				HttpStatus.CREATED.value(), 
				TraceIdUtil.getPath(),
				TraceIdUtil.getTraceId());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<UserInfo>>> getAllUsers() {
		List<UserInfo> users = userInfoService.getAllUsers();
		ApiResponse<List<UserInfo>> response = ApiResponse
				.success("All users retrieved successfully!",
				users,
				HttpStatus.OK.value(),
				TraceIdUtil.getPath(),
				TraceIdUtil.getTraceId());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UserInfo>> getUserById(@PathVariable Long id) {
		ApiResponse<UserInfo> response=ApiResponse
				.success(
						UserConstants.SUCCESS_FETCH_USER,
						userInfoService.getUserById(id),
						HttpStatus.FOUND.value(),
						TraceIdUtil.getPath(),
						TraceIdUtil.getTraceId());
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UserInfo>> updateUser(@PathVariable Long id, @Valid @RequestBody UserInfo userInfo) {
		ApiResponse<UserInfo> response=ApiResponse
				.success(
						UserConstants.SUCCESS_USER_UPDATE,
						userInfoService.updateUser(id, userInfo),
						HttpStatus.OK.value(),
						TraceIdUtil.getTraceId(),
						TraceIdUtil.getTraceId());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
		ApiResponse<Void> response=ApiResponse
				.success(
						UserConstants.SUCCESS_USER_DELETE,
						null,
						HttpStatus.NO_CONTENT.value(),
						TraceIdUtil.getTraceId(),
						TraceIdUtil.getTraceId());
		return ResponseEntity.ok(response);
	}
}
