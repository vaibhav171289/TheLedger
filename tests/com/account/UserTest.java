package com.account;

import org.junit.jupiter.api.Test;

public class UserTest {

	@Test
	public void test() {
		UserImp user = new UserImp("IDIDI", "Dale", 10000 ,5 ,4);
		System.out.println(user.toString());
	}

}
