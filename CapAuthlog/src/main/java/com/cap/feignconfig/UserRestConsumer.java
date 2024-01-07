package com.cap.feignconfig;

import com.cap.model.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cap.model.User;

@FeignClient(name = "regsiter-service", url = "http://localhost:8082/")
public interface UserRestConsumer {

	@GetMapping("/getUserByEmail/{email}")
	public User getUserByEmailHandler(@PathVariable String email);

}
