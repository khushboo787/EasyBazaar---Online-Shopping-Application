package com.bazaar.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.bazaar.dto.OrderDTO;
import com.bazaar.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;
	private Double totalPrice;
	private Integer numberOfProduct;
	

	 @JsonIgnore
	 @OneToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name="customerId")
	 private Customer customer;
	
	 @Embedded
	 @ElementCollection
	 private List<com.bazaar.dto.ProductDTO> products = new ArrayList<>();
}