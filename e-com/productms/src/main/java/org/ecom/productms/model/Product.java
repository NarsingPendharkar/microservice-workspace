package org.ecom.productms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_product")
public class Product{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Please enter valid product name !")
	private String name;
	@NotBlank(message = "Please enter Description !")
	private String description;
	@Positive(message = "price should not be negative !")
	private Double price;
	@Positive(message = "Qunatity should not be negative !")
	private Long quantity;


}
