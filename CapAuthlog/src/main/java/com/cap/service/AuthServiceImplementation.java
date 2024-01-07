package com.cap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.feignconfig.UserRestConsumer;
import com.cap.model.User;


@Service
public class AuthServiceImplementation implements AuthService {
	
	@Autowired
	private UserRestConsumer restConsumer;

	@Override
	public boolean validateUser(User uObj) {
		User regDetails = this.restConsumer.getUserByEmailHandler(uObj.getEmail());
		
		if(uObj.getEmail().equals(regDetails.getEmail()) && uObj.getPassword().equals(regDetails.getPassword())) {
			System.out.println("Successfully logged in!");
			return true;
		}
		else
		{
			System.out.println("Invalid Passowrd!");
			return false;
		}
	}

}
