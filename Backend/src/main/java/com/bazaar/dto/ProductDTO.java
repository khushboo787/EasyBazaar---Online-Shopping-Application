package com.bazaar.dto;




import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bazaar.enums.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
	
	private Integer productId;
	private String productName;
	private String picture;
	private Integer quantity;
	private Double marketPrice;
	private Double AfterDiscountPrice;
	private String productDescription;
	private Double discount;
	
	@Enumerated(EnumType.STRING)
	private CategoryType category;
	
	
}
