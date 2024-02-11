package com.microservice.departmentservice.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
    
    private LocalDateTime timestamp;

    private String message;

    private String path;

    private String errorCode;
    
    public ErrorDetails() {
		
	}

	public ErrorDetails(LocalDateTime timestamp, String message, String path, String errorCode) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.path = path;
		this.errorCode = errorCode;
	}
}
