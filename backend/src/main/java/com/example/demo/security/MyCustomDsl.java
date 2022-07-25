package com.example.demo.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import com.example.demo.security.jwt.JWTAuthorizationFilter;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
    private TokenUtils tokenUtils;
    
    public MyCustomDsl(TokenUtils tokenUtils) {
    	this.tokenUtils = tokenUtils;
    }
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		http.addFilter(new JWTAuthorizationFilter(authenticationManager, this.tokenUtils));
	}

	public static MyCustomDsl customDsl(TokenUtils tu) {
		return new MyCustomDsl(tu);
	}
}