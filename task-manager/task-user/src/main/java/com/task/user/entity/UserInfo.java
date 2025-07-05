package com.task.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "tbl_userinfo")
@Data
public class UserInfo {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // or SEQUENCE if preferred
    private Long id; 

	@NotBlank(message = "Username name is mandatory")
	@Size(max = 50, message = "Username name must be less than 50 characters")
	@Column(name = "username", nullable = false, length = 50)
	private String username;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@NotNull(message = "Age is required")
	@Positive(message = "Age must be a positive number")
	@Column(name = "age", nullable = false)
	private Integer age;

	@NotBlank(message = "Password is required")
	@Size(min = 4, max = 100, message = "Password must be at least 4 characters")
	@Column(name = "password", nullable = false)
	private String password;
	
	@NotBlank(message = "Role is required")
	private String role;

}
