package com.hoffozonparsing.start.service;

import com.hoffozonparsing.start.dto.UserDto;
import com.hoffozonparsing.start.model.User;

public interface UserService {

	public void saveUser(UserDto userDto);
	public User findUserByEmail(String email);
}
