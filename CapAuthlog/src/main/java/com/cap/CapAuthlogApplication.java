package com.cap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CapAuthlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapAuthlogApplication.class, args);
	}

}
