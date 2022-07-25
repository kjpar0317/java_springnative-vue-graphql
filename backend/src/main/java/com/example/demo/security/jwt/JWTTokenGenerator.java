package com.example.demo.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.security.TokenGenerator;

@Component
public class JWTTokenGenerator implements TokenGenerator {
    @Autowired
    private JWTTokenUtils tokenUtils;

    @Override
    public String build(Object id, Object role) {
        return JWT.create()
                .withSubject(id.toString())
                .withClaim("role", role.toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenUtils.getExpirationTime()))
                .sign(Algorithm.HMAC256(tokenUtils.getSecret().getBytes()));
    }
}
