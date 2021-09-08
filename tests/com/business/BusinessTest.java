package com.business;

import org.junit.jupiter.api.Test;

import com.account.UserImp;

public class BusinessTest {

	@Test
	public void Interesttest() {
		BusinessLogic o = BusinessLogic.build();
		long interest = o.caculateInterest(5142, 5, 4);
		assert (interest == 1029);
		interest = o.caculateInterest(0, 5, 4);
		assert (interest == 0);
		interest = o.caculateInterest(5142, 0, 4);
		assert (interest == 0);
		interest = o.caculateInterest(5142, 5, 0);
		assert (interest == 0);
		interest = o.caculateInterest(1421, 5, 2);
		assert (interest == 143);
		interest = o.caculateInterest(1000214, 15, 8);
		assert (interest == 1200257);

	}

	@Test
	public void calculateReplayment() {
		UserImp user = new UserImp("IDIDI", "Dale", 100000, 5, 5);
		BusinessLogic o = BusinessLogic.build();
		String str = o.getBalance(user, 3);
		assert (str.equals(6252 + " " + 57));

		o.adjustEmiAndLumsum(user, 5, 3000);
		assert (user.getPaidAmt() == 13420);
		str = o.getBalance(user, 5);
		assert (str.equals(13420 + " " + 54));

		o.adjustEmiAndLumsum(user, 10, 5000);
		assert (user.getPaidAmt() == 28840);
		str = o.getBalance(user, 10);
		assert (str.equals(28840 + " " + 47));

		o.adjustEmiAndLumsum(user, 10, 1000);
		assert (user.getPaidAmt() == 29840);
		str = o.getBalance(user, 10);
		assert (str.equals(29840 + " " + 46));

		o.adjustEmiAndLumsum(user, 10, 15000);
		assert (user.getPaidAmt() == 44840);
		str = o.getBalance(user, 10);
		assert (str.equals(44840 + " " + 39));

		str = o.getBalance(user, 5);
		assert (str.equals(13420 + " " + 54));

		o.adjustEmiAndLumsum(user, 25, 2084);
		assert (user.getPaidAmt() == 78184);
		str = o.getBalance(user, 10);
		assert (str.equals(44840 + " " + 39));
		str = o.getBalance(user, 25);
		assert (str.equals(78184 + " " + 23));

		o.adjustEmiAndLumsum(user, 27, 1672);
		assert (user.getPaidAmt() == 84024);

		o.adjustEmiAndLumsum(user, 27, 4168);
		assert (user.getPaidAmt() == 88192);
		

		o.adjustEmiAndLumsum(user, 58, 10000);
		assert (user.getPaidAmt() == 125000);
		str = o.getBalance(user, 58);
		assert (str.equals(125000 + " " + 0));

		o.adjustEmiAndLumsum(user, 60, 10000);
		assert (user.getPaidAmt() == 125000);
		str = o.getBalance(user, 60);
		assert (str.equals(125000 + " " + 0));

		UserImp user1 = new UserImp("IDIDI", "John", 1000, 1, 1);
		o.adjustEmiAndLumsum(user1, 12, 0);
		assert (user1.getPaidAmt() == 1010);
		str = o.getBalance(user1, 12);
		assert (str.equals(1010 + " " + 0));
	}
}
