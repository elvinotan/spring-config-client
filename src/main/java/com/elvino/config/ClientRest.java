package com.elvino.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/api")
public class ClientRest {

	@Value("${app.test.message:Default Test Message}")
	private String message;
	
	@GetMapping(value="/client")
	public String client() {
		return "Property app.test.message is "+message;
	}
}
