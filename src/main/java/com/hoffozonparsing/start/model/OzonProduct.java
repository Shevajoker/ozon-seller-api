package com.hoffozonparsing.start.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class OzonProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String idAnrex; 
	private String marketingPrice;
	private String minOzonPrice;
	private String oldPrice;
	private String premiumPrice;
	private int price;
	private String recommendedPrice;
	private int minPrice;
	
	
	public void setMinPrice(String minPrice) {
		if (minPrice != null && minPrice.length()>5) {
			minPrice = minPrice.substring(0, minPrice.length()-5);
		this.minPrice = Integer.valueOf(minPrice);
		} else {
			this.minPrice = -1;
		}
	}
	
	public void setPrice(String price) {
		if (price != null && price.length()>5) {
			price = price.substring(0, price.length()-5);
		this.price = Integer.valueOf(price);
		} else {
			this.price = -1;
		}
	}
	
}
