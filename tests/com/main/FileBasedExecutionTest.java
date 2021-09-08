package com.main;

import org.junit.jupiter.api.Test;

public class FileBasedExecutionTest {

	@Test
	public void test() {
		String args[] = {"input.txt"};
		FileBasedExecution.main(args);
	}

}
