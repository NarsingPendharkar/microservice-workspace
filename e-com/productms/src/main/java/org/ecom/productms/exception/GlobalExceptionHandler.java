package org.ecom.productms.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    

	@ExceptionHandler({ BindException.class, MethodArgumentNotValidException.class })
	public ResponseEntity<Map<String, Object>> handleBindingException(Exception ex, HttpServletRequest req) {
	    Map<String, Object> response = new HashMap<>();
	    Map<String, String> fieldErrors = new HashMap<>();

	    if (ex instanceof BindException bindEx) {
	        bindEx.getBindingResult().getFieldErrors().forEach(error -> 
	            fieldErrors.put(error.getField(), error.getDefaultMessage())
	        );
	    } else if (ex instanceof MethodArgumentNotValidException validEx) {
	        validEx.getBindingResult().getFieldErrors().forEach(error -> 
	            fieldErrors.put(error.getField(), error.getDefaultMessage())
	        );
	    }

	    response.put("error", ex.getClass().getSimpleName());
	    response.put("errorCode", HttpStatus.BAD_REQUEST.value());
	    response.put("message", "Validation failed for one or more fields");
	    response.put("fieldErrors", fieldErrors);
	    response.put("path", req.getRequestURI());

	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex, HttpServletRequest req) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", ex.getClass().getSimpleName());
        response.put("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("message", "Internal server error");
        response.put("path", req.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
