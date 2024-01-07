package com.cap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cap.filter.JWTFilter;


@SpringBootApplication

public class CapUserRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapUserRegisterApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterRegistraionBean = new FilterRegistrationBean();
		filterRegistraionBean.setFilter(new JWTFilter());
		filterRegistraionBean.addUrlPatterns("/cap/users/getAllUsers");
		
		
		return filterRegistraionBean ;
		
	}

}
