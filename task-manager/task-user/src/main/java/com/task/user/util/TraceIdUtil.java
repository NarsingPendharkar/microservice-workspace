package com.task.user.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

public class TraceIdUtil {
	
	private TraceIdUtil() {
		
	}
	
	public static String getTraceId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) return null;

        HttpServletRequest request = attributes.getRequest();
        Object traceId = request.getAttribute("traceId");
        return traceId != null ? traceId.toString() : null;
    }

    public static String getPath() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) return null;
        return attributes.getRequest().getRequestURI();
    }


}
