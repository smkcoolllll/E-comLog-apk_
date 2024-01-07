package com.cap.service;

import java.util.List;

import com.cap.dto.LoginDto;
import com.cap.exceptions.USERALREADYEXISTSEXCEPTION;
import com.cap.exceptions.USERNOTFOUNDEXCEPTION;
import com.cap.model.User;

public interface IUserService {

	User saveUser(User uObj) throws USERALREADYEXISTSEXCEPTION;

	User updateUser(User uobj, String email) throws USERNOTFOUNDEXCEPTION;

	List<User> getAllUsers();

	boolean delUser(String email) throws USERNOTFOUNDEXCEPTION;

	User getUserByEmail(String email) throws USERNOTFOUNDEXCEPTION;

    Object register(User registerDto);

    Object verifyAccount(String email, String otp);

    Object regenerateOtp(String email);

    //  Object login(LoginDto loginDto);

    String forgotPassword(String email);

    String setPassword(String email, String newPassword);

}
