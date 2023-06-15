package com.amusement.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
@MappedSuperclass
public class AbstractUser {

	private String username;

	private String password;

	private String address;

	@Column(unique = true, nullable = false)
	@Pattern(regexp = "^[6-9]\\d{9}")
	private String mobileNumber;

	@Column(unique = true, nullable = false)
	@Email
	private String email;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdOn;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedOn;
	
	//private Boolean isActive;	// 1 -> active // 0 -> not active
	
	@Column(nullable = false)
	private Boolean isDeleted;	// 1 -> deleted // 0 -> not deleted
	
	/**
	 * will send an otp to email with which user can validate himself
	 */
	//private Boolean isValidated; // 1 -> validated // 0 -> not validated
	
	@Enumerated(EnumType.STRING)
	private Role role;

}