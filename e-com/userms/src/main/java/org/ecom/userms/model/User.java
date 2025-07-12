/*
 * 
 */
package org.ecom.userms.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // Use "users" to avoid conflict with "user" keyword in some DBs
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Enter valid name !")
    private String name;

    @Email(message = "Enter valid email !")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Enter valid password !")
    private String password;

    @NotBlank(message = "Enter valid role !")
    private String role;
}
