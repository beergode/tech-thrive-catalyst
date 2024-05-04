//package com.beergode.decisionmaker.common.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		return http
//				.authorizeHttpRequests(authorize -> authorize
//						.requestMatchers("/actuator/**").permitAll()
//						.requestMatchers(HttpMethod.GET, "/", "/surveys/**").permitAll()
//						.anyRequest().hasRole("user")
//				)
//				.oauth2ResourceServer(oauth2 -> oauth2.jwt(
//						jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
//				.sessionManagement(sessionManagement ->
//						sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.csrf(AbstractHttpConfigurer::disable)
//				.build();
//	}
//
//	@Bean
//	public JwtAuthenticationConverter jwtAuthenticationConverter() {
//		var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
//		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
//
//		var jwtAuthenticationConverter = new JwtAuthenticationConverter();
//		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//		return jwtAuthenticationConverter;
//	}
//}
