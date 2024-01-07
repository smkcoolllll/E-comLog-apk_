package com.cap.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "User")
public class User {

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "mobile")
	private String mobile;

	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "password")
	private String password;
	
	@Column(name = "active")
	private boolean active;
  
	@Column(name = "otp")
	private String otp;
  
	@Column(name = "otpGeneratedTime")
	private LocalDateTime otpGeneratedTime;

	public User() {
		super();
	}

	public User(String firstName, String lastName, String mobile, String email, String address, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", mobile=" + mobile + ", email=" + email
				+ ", address=" + address + ", password=" + password + "]";
	}

}
