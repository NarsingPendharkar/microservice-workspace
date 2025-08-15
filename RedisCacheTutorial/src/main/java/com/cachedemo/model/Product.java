package com.cachedemo.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_product")
public class Product implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Please enter valid product name !")
	private String name;
	@NotBlank(message = "Please enter valid product name !")
	@Size(min = 10 , max = 20,message = "Description should be between 10 to 20 char ")
	private String description;
	@Positive(message = "price should not be negative !")
	private Double price;
	@Positive(message = "Qunatity should not be negative !")
	private Long quantity;


}
