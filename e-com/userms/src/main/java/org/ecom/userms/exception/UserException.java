package org.ecom.userms.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class UserException {
	
	@ExceptionHandler(exception ={Exception.class,NoResourceFoundException.class})
	public Map<String, Object> handleException(Exception ex,HttpServletRequest req){
		Map<String, Object> response=new HashMap<>();
		response.put("error", ex.getMessage());
		response.put("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.put("message", "Internal server error");
		response.put("path", req.getRequestURI());
		return response;
		
	}

}
