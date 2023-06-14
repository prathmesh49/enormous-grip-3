package com.amusement.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractUser {
	
	private String username;
	
	private String password;
	
	private String address;
	
	private String mobileNumber;
	
	private String email;
	
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedOn;
	
	
	//private Boolean isActive;	// 1 -> active // 0 -> not active
	
	
	private Boolean isDeleted;	// 1 -> deleted // 0 -> not deleted
	
	/**
	 * will send an otp to email with which user can validate himself
	 */
	//private Boolean isValidated; // 1 -> validated // 0 -> not validated
	
	
	private Role role;
	
}
