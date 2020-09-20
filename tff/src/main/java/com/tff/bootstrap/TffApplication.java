package com.tff.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@RestController
public class TffApplication {
	
	private static final Logger log = LoggerFactory.getLogger(TffApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(TffApplication.class, args);
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		log.debug("test");
		return String.format("Hello %s", name);
	}
	
	/**
	 * 这里实现统一入访，支持各种格式的报文转换
	 */
	@PostMapping("/tff")
	public void dispatch() {
		
	}

}
