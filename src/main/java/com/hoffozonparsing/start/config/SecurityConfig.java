package com.hoffozonparsing.start.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig{

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
//	@Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.addDialect(new SpringSecurityDialect());
//        return templateEngine;
//    }
	
	
//	@Bean
//	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//		UserDetails user = User.withDefaultPasswordEncoder()
//				.username("useruser")
//				.password("123")
//				.roles("USER")
//				.build();
//		
//		UserDetails admin = User.withDefaultPasswordEncoder()
//				.username("adminuser")
//				.password("123")
//				.roles("ADMIN")
//				.build();
//		
//		
//		return new InMemoryUserDetailsManager(user,admin);
//		
//	}
	
	
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
//		.csrf().disable()
		.authorizeHttpRequests((authz) ->{
			authz.requestMatchers("/registration/**").permitAll();
			authz.requestMatchers("/login/**").permitAll();
			authz.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN");
			authz.requestMatchers("/admin/**").hasRole("ADMIN");
			authz.anyRequest().authenticated();
		})
		.formLogin((form) -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/")
				.permitAll()
				)
		.logout((logout) -> {
			logout.logoutUrl("/logout");
			logout.permitAll();})
		.exceptionHandling().accessDeniedPage("/access-denied");
		
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
