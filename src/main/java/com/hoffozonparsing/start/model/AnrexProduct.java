package com.hoffozonparsing.start.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AnrexProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String model;
	private String idAnrex;
	private String oldPrice;
	private int price;
	
	public void setPrice(String price) {
		this.price = Integer.valueOf(price);
	}
	
	
	
//	public void setIdAnrex(String id) {
//		this.id = Long.valueOf(id).longValue();
//	}
//	
}
