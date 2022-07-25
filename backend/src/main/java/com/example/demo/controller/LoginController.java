package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.exception.NotUserAuthException;
import com.example.demo.model.Login;
import com.example.demo.service.LoginService;

import reactor.core.publisher.Mono;

@Controller
public class LoginController {
	@Autowired
	private LoginService service;
	
	@MutationMapping
	public Mono<Login> login(@Argument("id") String id, @Argument("password") String password) throws NotUserAuthException {
		return Mono.just(service.doLogin(id, password));
	}
}
