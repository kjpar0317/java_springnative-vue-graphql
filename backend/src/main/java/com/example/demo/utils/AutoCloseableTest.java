package com.example.demo.utils;

import java.util.function.Consumer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutoCloseableTest implements AutoCloseable {
	private AutoCloseableTest() {
		log.info("created");
	}
	
	public AutoCloseableTest print() {
		log.info("print");
		return this;
	}
	
	public void close() {
		log.info("closed");
	}
	
	public static void use(Consumer<AutoCloseableTest> block) {
		AutoCloseableTest autocloz = new AutoCloseableTest();
		
		try {
			block.accept(autocloz);
		} finally {
			autocloz.close();
		}
	}
}
