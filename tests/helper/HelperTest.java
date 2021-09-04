package helper;

import org.junit.Test;

import account.UserImp;

public class HelperTest {
    private SystemHelper helper = SystemHelper.getInstance();
    
	@Test
	public void addEntrytest() {
	    String bank = "HDFC";
	    String userName = "John";
	    int p = 100000;
	    int d= 5;
	    int r=5;
	    UserImp user = new UserImp(bank,userName,p,d,r);
		boolean res = helper.addActiveUserEntry(bank, userName, user);
		assert(res == true);
		System.out.println(user.toString());
		
	}

}
