package business;

import account.UserImp;

public class BusinessLogic {
	public static long caculateInterest(int principal, int years, int rate) {
		float x = principal * years * rate;
		long interest = (long) Math.ceil(x / 100);
		return interest;

	}

	public static long totalAmount(long total, long interest) {
		return (total + interest);
	}

	public static void adjustEmiAndLumsum(UserImp user, int emiNumber, int lumsum) {
		user.setLumsum(lumsum, emiNumber);
		updateBalance(user, emiNumber, lumsum);
	}

	public static void updateBalance(UserImp user, int emiNumber, int lumsumAmount) {
		updateEmiList(user, emiNumber);
		int lumsumEmiCount = (int) Math.floor(lumsumAmount / user.getEmiPerMonth());
		int totalEmiCount = user.getEmiCount();
		int exactEmiAmount = lumsumEmiCount * user.getEmiPerMonth();
		if (lumsumAmount > exactEmiAmount) {
			updateEmiList(user, emiNumber);
			int diff = lumsumAmount - exactEmiAmount;
			updateRemainingEmi(user, diff, lumsumEmiCount + 1);
		}else if (lumsumAmount == exactEmiAmount) {
			updateEmiList(user, emiNumber);
		}else {
			System.out.println("Bank is lucky today. user has paid more than total ");
		}
	}

	public static String getBalance(UserImp user, int emiNumber) {
		int emiPerMon = user.getEmiPerMonth();
		long valueAfterEmi = emiPerMon * emiNumber;
		long lumsum = 0;
		for (int i = 1; i <= emiNumber; i++) {
			lumsum += user.getLumsum(i);
		}
		long total = valueAfterEmi + lumsum;
		int lumsumEmiCount = (int) Math.floor(lumsum / user.getEmiPerMonth());
		int remEmiCount = user.getEmiCount() - (lumsumEmiCount+emiNumber);
		return total + " " + remEmiCount;
	}
	
	private static void updateRemainingEmi(UserImp user, int diff, int i) {
		long paidAmt = user.getPaidAmt();
		int[] emiList = user.getEmiList();
		paidAmt += diff;
		emiList[i] -= diff;
		user.setPaidAmt(paidAmt);
		user.setEmiList(emiList);
	}
	private static void updateEmiList(UserImp user, int emiNumber) {
		long paidAmt = user.getPaidAmt();
		int[] emiList = user.getEmiList();
		for (int i = 1; i < emiNumber; i++) {
			paidAmt += emiList[i];
			emiList[i] = 0;
		}
		user.setPaidAmt(paidAmt);
		user.setEmiList(emiList);
	}
}
