package net.projectsync.springsecurity.dto;

import net.projectsync.springsecurity.enums.Role;

public class RegistrationResponse {
	
	private String message;
	
	private String username;
	
	private Role role;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}