package com.helper;

import java.util.concurrent.ConcurrentHashMap;

import com.account.UserImp;
import com.business.BusinessLogic;

public class SystemHelper implements Helper {
	public static SystemHelper helper = null;
	// map to keep the list of active loans;
	ConcurrentHashMap<String, ConcurrentHashMap<String, UserImp>> bankToAccountDetails;

	public SystemHelper() {
		bankToAccountDetails = new ConcurrentHashMap<String, ConcurrentHashMap<String, UserImp>>();
	}

	public static SystemHelper getInstance() {
		if (helper == null)
			helper = new SystemHelper();
		return helper;
	}
	
    //wrapper around business logic as an exposed functions to user
	@Override
	public boolean addActiveUserEntry(String bank, String userName, UserImp user) {
		if (bankToAccountDetails.containsKey(bank)) {
			ConcurrentHashMap<String, UserImp> accountToUser = bankToAccountDetails.get(bank);
			accountToUser.put(userName, user);
		} else {
			bankToAccountDetails.put(bank, new ConcurrentHashMap<String, UserImp>());
			ConcurrentHashMap<String, UserImp> accountToUser = bankToAccountDetails.get(bank);
			accountToUser.put(userName, user);
		}
		return true;
	}
	@Override
	public void repayment(String bank, String username, int lumsum, int currentEmi) {
		ConcurrentHashMap<String, UserImp> bankDetail = bankToAccountDetails.getOrDefault(bank, null);
		if (bankDetail != null) {
			UserImp user = bankDetail.get(username);
			if (user != null) {
				BusinessLogic o = BusinessLogic.build();
				o.adjustEmiAndLumsum(user, currentEmi, lumsum);
			}
		}
	}
	@Override
	public String showBalance(String bank, String username, int emiNumber) {
		ConcurrentHashMap<String, UserImp> bankDetail = bankToAccountDetails.getOrDefault(bank, null);
		if (bankDetail != null) {
			UserImp user = bankDetail.get(username);
			if (user != null) {
				BusinessLogic o = BusinessLogic.build();
				return bank + " " + username + " " + o.getBalance(user, emiNumber);
			}
			else
				return username + " is not in the system";
		} else
			return bank + " is not in the system";
	}
	
}
