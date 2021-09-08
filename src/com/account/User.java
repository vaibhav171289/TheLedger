package com.account;


import java.util.concurrent.atomic.AtomicLong;

public abstract class User{
	private  AtomicLong accountNumber = new AtomicLong(1);

    protected  long nextAccountNumber() {
		return accountNumber.getAndIncrement();
	}
}
