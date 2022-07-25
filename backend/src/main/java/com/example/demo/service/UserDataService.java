package com.example.demo.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;

@Service
public class UserDataService implements UserDetailsService {
	@Autowired
	private UsersRepository userRepo;

	@Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Users user = userRepo.getUserByLoginId(id);
		
		if(user != null) {
			return createUserDetails(user);
		}

		throw new UsernameNotFoundException(id + " -> 유저 정보를 찾을 수 없습니다.");
    }

    // DB 에 User 값이 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Users user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.role());

        return new User(
                user.id(),
                user.password(),
                Collections.singleton(grantedAuthority)
        );
    }
}