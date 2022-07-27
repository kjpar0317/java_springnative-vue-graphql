package com.example.demo.security;

public interface TokenGenerator {
	String build(Object id, Object role);
}
