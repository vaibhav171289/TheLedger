package business;

import org.junit.Test;

public class BusinessTest {

	@Test
	public void test() {
		long interest = BusinessLogic.caculateInterest(5142, 5, 4);
		assert(interest == 1029);
		interest = BusinessLogic.caculateInterest(0, 5, 4);
		assert(interest == 0);
		interest = BusinessLogic.caculateInterest(5142, 0, 4);
		assert(interest == 0);
		interest = BusinessLogic.caculateInterest(5142, 5, 0);
		assert(interest == 0);
		interest = BusinessLogic.caculateInterest(1421, 5, 2);
		assert(interest == 143);
		interest = BusinessLogic.caculateInterest(1000214, 15, 8);
		assert(interest == 1200257);
		
	}

}
