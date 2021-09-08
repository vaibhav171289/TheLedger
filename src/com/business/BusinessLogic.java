package com.business;

import com.account.UserImp;

public class BusinessLogic extends Business {

	private static BusinessLogic blObj = null;

	public static BusinessLogic build() {
		if (blObj == null)
			blObj = new BusinessLogic();
		return blObj;
	}

	public long caculateInterest(int principal, int years, int rate) {
		float x = principal * years * rate;
		long interest = (long) Math.ceil(x / 100);
		return interest;

	}

	public long totalAmount(long total, long interest) {
		return (total + interest);
	}

	public void adjustEmiAndLumsum(UserImp user, int emiNumber, int lumsum) {
		// synchronisation on the user object if we move with multithtreading approach
		synchronized (user) {
			if (!isValidEmiNumber(user, emiNumber))
				return;
			user.setLumsum(lumsum, emiNumber);
			updateBalance(user, emiNumber, lumsum);
		}
	}

	private void updateBalance(UserImp user, int emiNumber, int lumsumAmount) {

		// first update the current emi list with the current emi
		updateEmiList(user, emiNumber);
		long paidAmt = user.getPaidAmt();
		if (paidAmt >= user.getTotal()) {
			System.err.print("User has already paid the complete emi" );
			if(lumsumAmount > 0) System.err.println( "... "+lumsumAmount + " is not required");
			return;
		}
		// calculating how many emi will lumsum amount help to reduce
		int lumsumEmiCount = (int) Math.floor(lumsumAmount / user.getEmiPerMonth());

		// update the total paid amount
		user.setPaidAmt(paidAmt + lumsumAmount);

		// update the emi value in emi list from the last of user emi list
		updateRemainingEmi(user, lumsumAmount, user.getReduceEmi(), user.getReduceEmi() - lumsumEmiCount);
	}

	public String getBalance(UserImp user, int emiNumber) {
		synchronized (user) {
			if (!isValidEmiNumber(user, emiNumber))
				return null;
			int emiPerMon = user.getEmiPerMonth();
			// calculating emi paid till this point
			long valueAfterEmi = emiPerMon * emiNumber;
			long lumsum = 0;
			// consider all the lumsum paid till this emi number
			for (int i = 0; i <= emiNumber; i++) {
				lumsum += user.getLumsum(i);
			}
			long total = valueAfterEmi + lumsum;

			// rounding off total as it is already paid
			if (total > user.getTotal())
				total = user.getTotal();
			int remEmiCount = 0;
			// calculating the after effect of lumsum emi till this point
			int lumsumEmiCount = (int) Math.floor(lumsum / user.getEmiPerMonth());
			remEmiCount = user.getEmiCount() - (lumsumEmiCount + emiNumber);
			// it is rounding to 0 since we have already paid emi for this month
			if (remEmiCount < 0)
				remEmiCount = 0;
			return total + " " + remEmiCount;
		}
	}

	private void updateRemainingEmi(UserImp user, int lumsum, int i, int j) {
		int[] emiList = user.getEmiList();
		// traverse till the lumsumemi count from current adjusted value
		while (i >= j) {

			if (lumsum == 0 || emiList[i] == 0)
				break;
			if (emiList[i] <= lumsum) {
				lumsum -= emiList[i];
				emiList[i] = 0;
			} else {
				emiList[i] -= lumsum;
				lumsum = 0;
			}
			i--;

		}
		if (emiList[i] == 0) {
			System.err.println("User has already paid the complete loan ");
			return;
		}
		// if current emi is exactly paid and nothing is remaining
		if (i < emiList.length - 1 && emiList[i + 1] == 0)
			user.setReduceEmi(i);
		else
			user.setReduceEmi(i + 1);
		user.setEmiList(emiList);
	}

	private void updateEmiList(UserImp user, int emiNumber) {
		long paidAmt = user.getPaidAmt();
		int[] emiList = user.getEmiList();
		// add the current emi amount to paidAmount till the emiNumber
		for (int i = 0; i <= emiNumber; i++) {
			if (i == user.getReduceEmi()) {
				paidAmt = user.getTotal();
			} else {
				paidAmt += emiList[i];
			}
			emiList[i] = 0;
		}
		// update all the values in user object again
		user.setPaidAmt(paidAmt);
		user.setEmiList(emiList);
	}

	private boolean isValidEmiNumber(UserImp user, int emiNumber) {
		if (emiNumber > user.getEmiCount())
			return false;
		else
			return true;
	}

}
