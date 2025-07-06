package com.task.tasks.util;

import java.time.Instant;

import lombok.Data;

@Data
public class ResponseBuilder<T> {
	 private Instant timestamp;
	    private int status;
	    private String message;
	    private String path;
	    private String error;
	    private T data;
	    
	    public ResponseBuilder(T data, int status, String message, String path, String error) {
	        this.timestamp = Instant.now();
	        this.status = status;
	        this.message = message;
	        this.path = path;
	        this.error = error;
	        this.data = data;
	    }

	    
    

}
