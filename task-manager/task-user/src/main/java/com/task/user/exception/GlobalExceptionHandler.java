package com.task.user.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.task.user.util.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleFieldValidation(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		Map<String, Object> body = new HashMap<>();
		String trace = (String) request.getAttribute("traceId");
		ex.getBindingResult().getFieldErrors().forEach(error -> body.put(error.getField(), error.getDefaultMessage()));
		ApiResponse<Map<String, String>> res = ApiResponse.error("Validation failed", HttpStatus.BAD_REQUEST.value(),
				request.getRequestURI(), trace);
		return ResponseEntity.badRequest().body(res);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleAllExceptions(Exception ex, HttpServletRequest request) {
		return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", request);
	}

	private ResponseEntity<ApiResponse<Object>> buildErrorResponse(HttpStatus status, String message,
			HttpServletRequest request) {
		return ResponseEntity.status(status).body(
				ApiResponse.error(message, status.value(), request.getRequestURI(), UUID.randomUUID().toString()));
	}

	
}
