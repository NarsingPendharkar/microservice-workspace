package com.task.user.util;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private int status;
    private String traceId;
    private String path;
    private LocalDateTime timestamp;
    private T data;
    
    
    
	public ApiResponse(boolean success, String message, int status, String traceId, String path, T data) {
		super();
		this.success = success;
		this.message = message;
		this.status = status;
		this.traceId = traceId;
		this.path = path;
		this.timestamp = LocalDateTime.now();
		this.data = data;
	}
	
	public static <T> ApiResponse<T> success(String message, T data, int status, String path, String traceId) {
        return new ApiResponse<>(true, message, status, traceId, path, data);
    }

    public static <T> ApiResponse<T> error(String message, int status, String path, String traceId) {
        return new ApiResponse<>(false, message, status, traceId, path, null);
    }
    
    

}
