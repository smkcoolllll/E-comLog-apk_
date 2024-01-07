package com.cap.service;

import org.springframework.stereotype.Service;

import com.cap.model.User;
@Service
public interface AuthService {

	boolean validateUser(User uObj);

}
