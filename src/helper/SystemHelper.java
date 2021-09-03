package helper;

import java.util.concurrent.ConcurrentHashMap;

import account.UserImp;
import business.BusinessLogic;

public class SystemHelper {
	public static SystemHelper helper = null;
	//map to keep the list of active loans;
	ConcurrentHashMap<String, ConcurrentHashMap<String, UserImp>> bankToAccountDetails;
	public SystemHelper() {
		bankToAccountDetails = new ConcurrentHashMap<String,  ConcurrentHashMap<String, UserImp>>();
	}
	public static SystemHelper getInstance() {
		if(helper ==null) helper = new SystemHelper();
		return helper;
	}
	public boolean addActiveUserEntry(String bank, String userName, UserImp user) {
		if(bankToAccountDetails.containsKey(bank)) {
			ConcurrentHashMap<String, UserImp> accountToUser = bankToAccountDetails.get(bank);
			accountToUser.put(userName,user);
		}
		else {
		  bankToAccountDetails.put(bank , new ConcurrentHashMap<String, UserImp>());
		  ConcurrentHashMap<String, UserImp> accountToUser = bankToAccountDetails.get(bank);
		   accountToUser.put(userName,user);
		}
		return true;
	}

	public void repayment(String bank,  String username, int lumsum, int currentEmi) {
		UserImp user = bankToAccountDetails.get(bank).get(username);
		BusinessLogic.adjustEmiAndLumsum(user, currentEmi, lumsum);
	}
	
	public String showBalance(String bank, String username, int emiNumber) {
		UserImp user = bankToAccountDetails.get(bank).get(username);
		return bank+" " + username+" "+BusinessLogic.getBalance(user, emiNumber);
	}
}
