package com.example.githublogin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

//Controlling the authentication mechanism
@Configuration
public class SecurityAdapter{
	
	//Authentication configuration
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		// @formatter:off
		http
			.authorizeHttpRequests(a -> a
					.requestMatchers("/", "/error").permitAll()
					.anyRequest().authenticated()
			)
			.exceptionHandling(e -> e
					.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))		
			)
			.csrf(c -> c
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			)
			.logout(l -> l
					.logoutSuccessUrl("/").permitAll()
			)
			.oauth2Login();
		// @formatter:on
		return http.build();
	}

}
