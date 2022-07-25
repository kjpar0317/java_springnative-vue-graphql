package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotUserAuthException;
import com.example.demo.model.Login;
import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.security.TokenGenerator;

@Service
public class LoginService {
	@Autowired
	private TokenGenerator tk;

	@Autowired
	private UsersRepository userRepo;
	
	public Login doLogin(String userId, String passwd) throws NotUserAuthException {
		Users user = userRepo.getUserByLoginInfo(userId, passwd); 
		
		if(user != null) {
			return new Login(user.id(), tk.build(user.id(), user.role()));
		}
		
		throw new NotUserAuthException();
    }
}
