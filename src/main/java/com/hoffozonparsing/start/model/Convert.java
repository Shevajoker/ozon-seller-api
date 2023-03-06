package com.hoffozonparsing.start.model;

public class Convert {

	static Integer convertStringPriceToInteger(String stringPrice) {
		
		if (stringPrice != null && stringPrice.length()>5) {
			stringPrice = stringPrice.substring(0, stringPrice.length()-5);
		return Integer.valueOf(stringPrice);
		} else {
			return -1;
		}
		
	}	
	
}
