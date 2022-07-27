package com.example.demo.security.jwt;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entity.UserEntity;
import com.example.demo.security.TokenPayload;
import com.example.demo.security.TokenUtils;

@Component
public class JWTTokenUtils extends TokenUtils {
	public TokenPayload decodeToken(String authorizationHeader) {
		DecodedJWT decodedToken = JWT.require(Algorithm.HMAC256(getSecret().getBytes())).build()
				.verify(authorizationHeader.replace(getTokenPrefix(), ""));

		return new TokenPayload(decodedToken.getSubject(), decodedToken.getClaim("role").as(UserEntity.Role.class));
	}
}
