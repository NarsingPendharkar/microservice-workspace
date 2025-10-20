package org.school.gatewayms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FallbackController {

	 @GetMapping("/studentFallback")
	    public ResponseEntity<String> studentFallback() {
	        return ResponseEntity
	                .status(HttpStatus.SERVICE_UNAVAILABLE)
	                .body("Student Service is currently unavailable. Please try again later.");
	    }

	    @GetMapping("/addressFallback")
	    public ResponseEntity<String> addressFallback() {
	        return ResponseEntity
	                .status(HttpStatus.SERVICE_UNAVAILABLE)
	                .body("Address Service is currently unavailable. Please try again later.");
	    }
}
