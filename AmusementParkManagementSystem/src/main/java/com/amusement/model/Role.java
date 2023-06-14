package com.amusement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Role {
	
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	private final String roleName;
}
