package com.hoffozonparsing.start.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long id;
	@NotEmpty(message = "Введите имя.")
	private String name;
	@NotEmpty(message = "Введите email.")
	private String email;
	@NotEmpty(message = "Введите пароль.")
	private String password;
	
	
}
