package org.ecom.userms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto {
	
	private Long id;
	private String name;
	private String email;
	@JsonIgnore
	private String password;
	private String role;

}
