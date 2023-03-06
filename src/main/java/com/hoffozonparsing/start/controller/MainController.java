package com.hoffozonparsing.start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoffozonparsing.start.parser.HoffParser;
import com.hoffozonparsing.start.parser.OzonParser;

@Controller
@RequestMapping("/")
public class MainController {

	
	@GetMapping
	public String main(Model model) {
		
//		HoffParser parser = new HoffParser(); //7-23 8-50
//
//		model.addAttribute("name", parser.getContent("url"));
		
		return "index";
	}
	

	@GetMapping("/test")
	public String test() {
		return "test"; 
	}
	
	
}
