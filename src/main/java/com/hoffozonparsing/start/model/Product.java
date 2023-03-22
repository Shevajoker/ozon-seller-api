package com.hoffozonparsing.start.model;


import lombok.Data;

@Data
//@Entity
public class Product {

	private String name;
	private String id_anrex; 
	private String marketing_price;
	private String min_ozon_price;
	private String old_price;
	private String premium_price;
	private String price;
	private String recommended_price;
	private String min_price;

	
}
