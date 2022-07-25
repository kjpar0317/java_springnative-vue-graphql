package com.example.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public abstract class TokenUtils {
    @Value("${token.header-name}")
    private String headerString;
    @Value("${token.prefix}")
    private String tokenPrefix;
    @Value("${token.secret-password}")
    private String secret;
    @Value("${token.duration-ms}")
    private long expirationTime;

    abstract public TokenPayload decodeToken(String authorizationHeader);

    public String getHeaderString() {
        return headerString;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public String getSecret() {
        return secret;
    }

    public long getExpirationTime() {
        return expirationTime;
    }
}
