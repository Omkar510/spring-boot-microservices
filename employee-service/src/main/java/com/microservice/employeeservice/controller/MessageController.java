package com.microservice.employeeservice.controller	;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageController {

	@Value("${spring.boot.message}")
	private String message;
	
	@GetMapping("/users/message")
	public Map<String, String> message() {
		
		Map<String, String> map = new HashMap<>();
		
		map.put("message", message);
		
		return map;
	}
}
