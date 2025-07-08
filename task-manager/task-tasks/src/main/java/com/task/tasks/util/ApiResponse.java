package com.task.tasks.util;

import java.time.Instant;

import lombok.Data;

@Data
public class ApiResponse<T> {
	private final Instant timestamp;
    private final int status;
    private final String message;
    private final String path;
    private final String error;
    private final T data;

    private ApiResponse(T data, int status, String message, String path, String error) {
        this.timestamp = Instant.now();
        this.status = status;
        this.message = message;
        this.path = path;
        this.error = error;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data, String message, String path) {
        return new ApiResponse<>(data, 200, message, path, null);
    }

    public static <T> ApiResponse<T> error(String message, String path, String error, int status) {
        return new ApiResponse<>(null, status, message, path, error);
    }


}
