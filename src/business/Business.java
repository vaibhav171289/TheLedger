package business;

import account.UserImp;

public abstract class Business {
	public abstract long caculateInterest(int principal, int years, int rate) ;
	public abstract long totalAmount(long total, long interest);
	public abstract  void adjustEmiAndLumsum(UserImp user, int emiNumber, int lumsum) ;
	public abstract String getBalance(UserImp user, int emiNumber);
}
