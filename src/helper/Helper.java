package helper;

import account.UserImp;

public interface Helper {
	public boolean addActiveUserEntry(String bank, String userName, UserImp user);

	public void repayment(String bank, String username, int lumsum, int currentEmi);

	public String showBalance(String bank, String username, int emiNumber);
}
