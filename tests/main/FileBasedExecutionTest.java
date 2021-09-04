package main;

import org.junit.Test;

public class FileBasedExecutionTest {

	@Test
	public void test() {
		String args[] = {"input.txt"};
		FileBasedExecution.main(args);
	}

}
