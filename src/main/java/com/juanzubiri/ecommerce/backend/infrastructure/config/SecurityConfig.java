package com.juanzubiri.ecommerce.backend.infrastructure.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.juanzubiri.ecommerce.backend.infrastructure.jwt.JwtAuthorizationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	private final JwtAuthorizationFilter authorizationFilter;
	
	public SecurityConfig(JwtAuthorizationFilter authorizationFilter) {
		super();
		this.authorizationFilter = authorizationFilter;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.cors(
				          cors->cors.configurationSource(
				        		         request -> {
				        		        	 CorsConfiguration corsConfiguration = new CorsConfiguration();
				        		        	 corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
				        		        	 corsConfiguration.setAllowedMethods(Arrays.asList("*"));
				        		        	 corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
				        		        	 return corsConfiguration;
				        		         }
				        		      )
				          )
		.csrf(csrf -> csrf.disable()).authorizeHttpRequests(
				aut -> 
				aut.requestMatchers("/api/v1/admin/categories/**").hasRole("ADMIN")
				.requestMatchers("/api/v1/admin/products/**").hasRole("ADMIN")
				.requestMatchers("/api/v1/orders/**").hasRole("USER")
				.requestMatchers("/api/v1/payments/success").permitAll()
				.requestMatchers("/api/v1/payments/**").hasRole("USER")
				.requestMatchers("/api/v1/home/**").permitAll()
				.requestMatchers("/images/**").permitAll()
				.requestMatchers("/api/v1/security/**").permitAll().anyRequest().authenticated()
	).addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
		
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder () {
		
		return new BCryptPasswordEncoder();
		
	}

}
