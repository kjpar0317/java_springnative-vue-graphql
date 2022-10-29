package com.example.demo.utils;

import java.util.function.Consumer;

public class LambdaTest {
	public LambdaTest print() {
		System.out.println("wow");
		return this;
	}
	public LambdaTest print2() {
		System.out.println("great");
		return this;
	}
	public static void test(Consumer<LambdaTest> block) {
		var test = new LambdaTest();
		block.accept(test);
	}
}
