package com.cap.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.Duration;

import com.cap.dto.LoginDto;
import com.cap.exceptions.USERALREADYEXISTSEXCEPTION;
import com.cap.exceptions.USERNOTFOUNDEXCEPTION;
import com.cap.model.User;
import com.cap.repository.IUserRepository;
import com.cap.util.EmailUtil;
import com.cap.util.OtpUtil;

import jakarta.mail.MessagingException;

@Service
public class UserServiceImplementation implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public User saveUser(User uObj) throws USERALREADYEXISTSEXCEPTION {
		User addObj = null;
		Optional<User> regUser = this.userRepository.findById(uObj.getEmail());
		if (regUser.isPresent()) {
			throw new USERALREADYEXISTSEXCEPTION();
		} else {
			addObj = this.userRepository.save(uObj);
		}
		return addObj;
	}

	@Override
	public User updateUser(User uobj, String email) throws USERNOTFOUNDEXCEPTION {
		Optional<User> userOptional = this.userRepository.findById(email);
		User uObj = null;
		User updatedData = null;
		if (userOptional.isPresent()) {
			uObj = userOptional.get();
			uObj.setEmail(uobj.getEmail());
			uObj.setFirstName(uobj.getFirstName());
			uObj.setLastName(uobj.getLastName());

			uObj.setAddress(uobj.getAddress());

			uObj.setMobile(uobj.getMobile());
			uObj.setPassword(uobj.getPassword());

			updatedData = this.userRepository.save(uObj);
		} else {
			throw new USERNOTFOUNDEXCEPTION();
		}
		return updatedData;
	}

	@Override
	public User getUserByEmail(String email) throws USERNOTFOUNDEXCEPTION {
		Optional<User> userOptional = this.userRepository.findById(email);
		User uObj = null;

		if (userOptional.isPresent()) {
			uObj = userOptional.get();
		} else {
			throw new USERNOTFOUNDEXCEPTION();
		}

		return uObj;
	}

	@Override
	public List<User> getAllUsers() {

		System.out.print("running getAll users");
		return this.userRepository.findAll();
	}

	@Override
	public boolean delUser(String email) throws USERNOTFOUNDEXCEPTION {
		Optional<User> userOptional = this.userRepository.findById(email);
		boolean status = false;
		if (userOptional.isPresent()) {
			this.userRepository.delete(userOptional.get());
			status = true;
		} else {
			throw new USERNOTFOUNDEXCEPTION();
		}
		return status;
	}

	// ****--------------------------------------------------***********

	@Autowired
	private OtpUtil otpUtil;
	@Autowired
	private EmailUtil emailUtil;

	public String register(User registerDto) {
		String otp = otpUtil.generateOtp();
		try {
			emailUtil.sendOtpEmail(registerDto.getEmail(), otp);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send otp please try again");
		}
		User user = new User();
		user.setFirstName(registerDto.getFirstName());
		user.setLastName(registerDto.getLastName());
		user.setMobile(registerDto.getMobile());
		user.setAddress(registerDto.getAddress());
		user.setEmail(registerDto.getEmail());
		user.setPassword(registerDto.getPassword());
		user.setOtp(otp);
		user.setOtpGeneratedTime(LocalDateTime.now());
		userRepository.save(user);
		return "User registration successful";
	}

	public String verifyAccount(String email, String otp) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
				LocalDateTime.now()).getSeconds() < (1 * 60)) {
			user.setActive(true);
			userRepository.save(user);
			return "OTP verified you can login";
		}
		return "Please regenerate otp and try again";
	}

	public String regenerateOtp(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		String otp = otpUtil.generateOtp();
		try {
			emailUtil.sendOtpEmail(email, otp);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send otp please try again");
		}
		user.setOtp(otp);
		user.setOtpGeneratedTime(LocalDateTime.now());
		userRepository.save(user);
		return "Email sent... please verify account within 1 minute";
	}

	// public String login(LoginDto loginDto) {
	// User user = userRepository.findByEmail(loginDto.getEmail())
	// .orElseThrow(
	// () -> new RuntimeException("User not found with this email: " +
	// loginDto.getEmail()));
	// if (!loginDto.getPassword().equals(user.getPassword())) {
	// return "Password is incorrect";
	// } else if (!user.isActive()) {
	// return "your account is not verified";
	// }
	// return "Login successful";
	// }

	public String forgotPassword(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		String newPassword = generateNewPassword();
		user.setPassword(newPassword);
		userRepository.save(user);

		try {
			emailUtil.sendPasswordResetEmail(email, newPassword);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send password reset email. Please try again.");
		}

		return "Password reset email sent. Please check your email for further instructions.";
	}

	public String setPassword(@RequestParam String email, @RequestHeader String newPassword) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		user.setPassword(newPassword);
		userRepository.save(user);
		return "Password successfully set.";
	}

	private String generateNewPassword() {
		int length = 10; // Define the length of the password
		String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String specialChars = "!@#$%^&*()_-+=<>?";

		String allowedChars = uppercaseLetters + lowercaseLetters + numbers + specialChars;

		StringBuilder password = new StringBuilder();

		// Generate random characters from the allowed characters set
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(allowedChars.length());
			password.append(allowedChars.charAt(randomIndex));
		}

		return password.toString();
	}

}
