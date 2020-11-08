package com.tff;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.tff.account.mapper")
@SpringBootApplication
public class TffApplication {
	public static void main(String[] args) {
		SpringApplication.run(TffApplication.class, args);
	}
}
