package com.hoffozonparsing.start.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hoffozonparsing.start.dto.UserDto;
import com.hoffozonparsing.start.model.Role;
import com.hoffozonparsing.start.model.User;
import com.hoffozonparsing.start.repository.RoleRepository;
import com.hoffozonparsing.start.repository.UserRepository;
import com.hoffozonparsing.start.util.TbConstants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void saveUser(UserDto userDto) {
		Role role = roleRepository.findByName(TbConstants.Roles.USER);
		
		if(role == null) {
			role = roleRepository.save(new Role(TbConstants.Roles.USER));
		}
		
		User user = new User(userDto.getName(), userDto.getEmail()
				, passwordEncoder.encode(userDto.getPassword()), Arrays.asList(role));
		userRepository.save(user);
	}

	@Override
	public User findUserByEmail(String email) {

		return userRepository.findByEmail(email);
	}

}
