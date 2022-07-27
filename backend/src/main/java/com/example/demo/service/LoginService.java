package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NotUserAuthException;
import com.example.demo.model.Login;
import com.example.demo.repository.UsersRepository;
import com.example.demo.security.TokenGenerator;

@Service
public class LoginService {
	@Autowired
	private TokenGenerator tk;

	@Autowired
	private UsersRepository userRepo;
	
	public Login doLogin(String userId, String passwd) throws NotUserAuthException {
		return userRepo.getUserByLoginInfo(userId, passwd)
				.map(m -> new Login(m.id(), tk.build(m.id(), m.role()))).orElseThrow(() -> new NotUserAuthException());
    }
}
