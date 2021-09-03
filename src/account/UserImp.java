package account;

import java.util.Arrays;

import business.BusinessLogic;

public class UserImp extends User{
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getEmiCount() {
		return emiCount;
	}
	public void setEmiCount(int emiCount) {
		this.emiCount = emiCount;
	}
	public long getPaidAmt() {
		return paidAmt;
	}
	public void setPaidAmt(int paidAmt) {
		this.paidAmt = paidAmt;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getPrincipalAmount() {
		return principalAmount;
	}
	public void setPrincipalAmount(int principalAmount) {
		this.principalAmount = principalAmount;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public long getInterest() {
		return interest;
	}
	public void setInterest(long interest) {
		this.interest = interest;
	}
	public int getEmiPerMonth() {
		return emiPerMonth;
	}
	public void setEmiPerMonth(int emiPerMonth) {
		this.emiPerMonth = emiPerMonth;
	}
	public int[] getEmiList() {
		return emiList;
	}
	public void setEmiList(int emiList[]) {
		this.emiList = emiList;
	}
	public int getLumsum(int emi) {
		return lumsum[emi];
	}
	public void setLumsum(int lumsum,int emi) {
		this.lumsum[emi] = lumsum;
	}
	public void setPrincipalAmount(long principalAmount) {
		this.principalAmount = principalAmount;
	}
	public void setPaidAmt(long paidAmt) {
		this.paidAmt = paidAmt;
	}
	private String username;
	private String bankName;
	private long accountNumber;
    private long total;
    private long principalAmount;
    private long interest;
    private int duration;
    private int emiCount;
    private long paidAmt;
    private int rate;
    private int emiPerMonth;
    private int emiList[];
    private int lumsum[];
	public UserImp(String bank, String username, int principal, int duration,int rate) {
		this.bankName = bank;
		this.username = username;
		this.setAccountNumber(nextAccountNumber());
		this.setPrincipalAmount(principal);
		this.duration = duration;
		this.rate = rate;
		paidAmt = 0;
		//Calculate interest on creation of account
		interest = BusinessLogic.caculateInterest(principal, duration,rate);
		//Calculate total amount
		setTotal(BusinessLogic.totalAmount(this.principalAmount,  interest));
		//calculate total emi count
		emiCount = duration*12;
		float total = getTotal();
		emiPerMonth = (int)Math.ceil(total/getEmiCount());
		emiList = new int[getEmiCount()+1];
		lumsum= new int[getEmiCount()+1];
		emiList[0] = 0;
		Arrays.fill(emiList,1,emiList.length, emiPerMonth);
		Arrays.fill(lumsum, 0);
	}
	@Override
	public String toString() {
		StringBuilder user = new StringBuilder();
		user.append(bankName+" | ");
		user.append(username+" | ");
		user.append("Rs. "+principalAmount+" | ");
		user.append(duration+" years | ");
		user.append(rate+"% | ");
		user.append("Rs. "+emiPerMonth+"/mon | ");
		user.append("Rs. "+total);
		return user.toString();
	}

}
