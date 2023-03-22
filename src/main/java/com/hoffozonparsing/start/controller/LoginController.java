package com.hoffozonparsing.start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hoffozonparsing.start.dto.UserDto;
import com.hoffozonparsing.start.model.User;
import com.hoffozonparsing.start.service.UserService;

import jakarta.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@GetMapping("/registration")
	public String registrationForm(Model model) {
		UserDto userDto = new UserDto();
		
		model.addAttribute("user", userDto);
		return "registration";
	}
	
	
	
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model) {
		User existUser = userService.findUserByEmail(userDto.getEmail());
		
		if(existUser != null) {
			bindingResult.rejectValue("email", null, "Пользователь уже зарегистрирован.");
		}
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("user", userDto);
			return "registration";
		}
		
		userService.saveUser(userDto);
		
		return "redirect:/registration?success";
	}
	
	
	
	
	
}
