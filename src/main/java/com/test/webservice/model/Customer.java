package com.test.webservice.model;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
	
	String fullName;
	
	String emailAddress;
	
	String phoneNumber;
	
	
	public Customer(String fullName, String emailAddress, String phoneNumber) {
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}

	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
