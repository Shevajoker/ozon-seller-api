package com.hoffozonparsing.start.exception;

public class OzonProductNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

//	private String message;
	
	public OzonProductNotFoundException() {
		super("ANREX product not found!");
	}
	
	
	
}
