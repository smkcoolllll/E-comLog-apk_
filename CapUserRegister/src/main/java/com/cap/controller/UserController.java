package com.cap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.dto.LoginDto;
import com.cap.exceptions.USERALREADYEXISTSEXCEPTION;
import com.cap.exceptions.USERNOTFOUNDEXCEPTION;
import com.cap.model.User;
import com.cap.service.IUserService;

import jakarta.mail.MessagingException;

@RestController

@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

	@Autowired
	private IUserService userService;

	private ResponseEntity<?> responseEntity;

	// @PostMapping("/register")
	// public ResponseEntity<?> saveUserHandler(@RequestBody User uObj) throws USERALREADYEXISTSEXCEPTION {
	// 	try {
	// 		User saveUser = this.userService.saveUser(uObj);
	// 		responseEntity = new ResponseEntity(saveUser, HttpStatus.CREATED);
	// 	} catch (USERALREADYEXISTSEXCEPTION e) {
	// 		responseEntity = new ResponseEntity("User Already Exist", HttpStatus.CONFLICT);
	// 		e.printStackTrace();
	// 	}
	// 	return responseEntity;

	// }

	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsersHandler() {
		List<User> users = this.userService.getAllUsers();
		responseEntity = new ResponseEntity(users, HttpStatus.OK);
		return responseEntity;

	}

	@GetMapping("/getUserByEmail/{email}")
	public ResponseEntity<?> getUserByEmailHandler(@PathVariable String email) throws USERNOTFOUNDEXCEPTION {
		try {
			User singleUser = this.userService.getUserByEmail(email);
			responseEntity = new ResponseEntity(singleUser, HttpStatus.OK);
		} catch (USERNOTFOUNDEXCEPTION e) {
			responseEntity = new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
			e.printStackTrace();

		}
		return responseEntity;
	}

	@DeleteMapping("/delUserByEmail/{email}")
	public ResponseEntity<?> delUserHandler(@PathVariable String email) throws USERNOTFOUNDEXCEPTION {

		try {
			boolean status = this.userService.delUser(email);
			responseEntity = new ResponseEntity(status, HttpStatus.OK);
		} catch (USERNOTFOUNDEXCEPTION e) {
			responseEntity = new ResponseEntity("User Not found", HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return responseEntity;

	}

	@PutMapping("/updateUser/{email}")
	public ResponseEntity<?> updateUserHandler(@RequestBody User uObj, @PathVariable String email)
			throws USERNOTFOUNDEXCEPTION {
		try {
			User updatedData = this.userService.updateUser(uObj, email);
			responseEntity = new ResponseEntity(updatedData, HttpStatus.OK);
		} catch (USERNOTFOUNDEXCEPTION e) {
			responseEntity = new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// **********-----------------------------------------------------------------------------**********************************

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User registerDto) {
		String response = (String) userService.register(registerDto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/verify-account")
	public ResponseEntity<String> verifyAccount(@RequestParam("email") String email, @RequestParam("otp") String otp) {
		String response = (String) userService.verifyAccount(email, otp);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/regenerate-otp")
	public ResponseEntity<String> regenerateOtp(@RequestParam("email") String email) {
		String response = (String) userService.regenerateOtp(email);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// @PostMapping("/login")
	// public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
	// 	String response = (String) userService.login(loginDto);
	// 	return new ResponseEntity<>(response, HttpStatus.OK);
	// }

	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) throws MessagingException {
		String response = userService.forgotPassword(email);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/set-password")
	public ResponseEntity<String> setPassword(@RequestParam("email") String email,
			@RequestParam("newPassword") String newPassword) {
		String response = userService.setPassword(email, newPassword);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
