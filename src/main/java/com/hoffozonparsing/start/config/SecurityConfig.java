package com.hoffozonparsing.start.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.boot.autoconfigure.security.servlet.*;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig{

	
	
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("useruser")
				.password("123")
				.roles("USER")
				.build();
		
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("adminuser")
				.password("123")
				.roles("ADMIN")
				.build();
		
		
		return new InMemoryUserDetailsManager(user,admin);
		
	}
	
	
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.authorizeHttpRequests((authz) ->{
//			authz.requestMatchers("/").permitAll();
			authz.requestMatchers("/*").hasRole("USER");
			authz.requestMatchers("/admin").hasRole("ADMIN");
		})
		.formLogin()
		.and()
		.logout()
		;
//		.httpBasic(Customizer.withDefaults());
		return httpSecurity.build();
	}
	
	
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer(HttpSecurity httpSecurity) throws Exception {
//		
//		httpSecurity.authorizeHttpRequests(authz -> {
//			authz.requestMatchers("/").permitAll();
//			authz.requestMatchers("/info").hasRole("USER");
//			authz.requestMatchers("/admin").hasRole("ADMIN");
//		})
//		.formLogin()
//		.and()
//		.logout();
////		.httpBasic(Customizer.withDefaults());
//		
//		
//		return null;
		
//	}
	
}
