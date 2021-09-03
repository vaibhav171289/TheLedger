package account;

import static org.junit.Assert.*;

import org.junit.Test;

import account.UserImp;

public class UserTest {

	@Test
	public void test() {
		UserImp user = new UserImp("IDIDI", "Dale", 10000 ,5 ,4);
		System.out.println(user.toString());
	}

}
