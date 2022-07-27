package com.example.demo.exception;

public class NotUserAuthException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MSG = "인증되지 않은 유저입니다.";

	public NotUserAuthException() {
		super(ERROR_MSG);
	}

	public NotUserAuthException(String message) {
		super(message);
	}
}