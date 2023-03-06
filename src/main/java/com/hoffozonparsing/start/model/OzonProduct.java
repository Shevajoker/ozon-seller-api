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
	private int marketingPrice;
	private int minOzonPrice;
	private int oldPrice;
	private int premiumPrice;
	private int price;
	private int recommendedPrice;
	private int minPrice;
	
	
	public void setMinPrice(String minPrice) {
		this.minPrice = Convert.convertStringPriceToInteger(minPrice);
	}
	
	public void setPrice(String price) {
		this.price = Convert.convertStringPriceToInteger(price);
	}
	
	public void setRecommendedPrice(String recommendedPrice) {
		this.recommendedPrice = Convert.convertStringPriceToInteger(recommendedPrice);
	}
	
	public void setPremiumPrice(String premiumPrice) {
		this.premiumPrice = Convert.convertStringPriceToInteger(premiumPrice);
	}
	
	public void setOldPrice(String oldPrice) {
		this.oldPrice = Convert.convertStringPriceToInteger(oldPrice);
	}
	
	public void setMinOzonPrice(String minOzonPrice) {
		this.minOzonPrice = Convert.convertStringPriceToInteger(minOzonPrice);
	}
	
	public void setMarketingPrice(String marketingPrice) {
		this.marketingPrice = Convert.convertStringPriceToInteger(marketingPrice);
	}
	
}
