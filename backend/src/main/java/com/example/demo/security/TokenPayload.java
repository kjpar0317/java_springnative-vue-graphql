package com.example.demo.security;

import com.example.demo.entity.UserEntity;

public class TokenPayload {
    private final String username;	
    private final UserEntity.Role role;

    public TokenPayload(String username, UserEntity.Role role) {
    	this.username = username;
    	this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity.Role getRole() {
        return role;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .append("username", username)
//                .append("role", role)
//                .toString();
//    }
}
