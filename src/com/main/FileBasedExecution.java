package com.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.account.UserImp;
import com.helper.SystemHelper;

public class FileBasedExecution {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Provide a valid file path as input");
		} else {
			File file = new File(args[0]);
			if (!file.isFile()) {
				System.err.println("Provide a valid file path as input");
			} else {
				try {
					SystemHelper helper = SystemHelper.getInstance();
					Scanner sc = new Scanner(file);
					while (sc.hasNext()) {

						String str = sc.nextLine();
						try {
							String input[] = str.split("\\s+");
							String bankname = input[1];
							String username = input[2];
							switch (input[0]) {
							case "LOAN": {
								int principal = Integer.parseInt(input[3]);
								if(principal <= 0) {
									System.out.println("please provide a valid loan amount for "+ username+" of bank: "+bankname);
									break;
								}
								int duration = Integer.parseInt(input[4]);
								int rate = Integer.parseInt(input[5]);
								if(duration <= 0) {
									System.out.println("please provide a valid duration for "+ username+" of bank: "+bankname);
									break;
								}
								if(rate <= 0) {
									System.out.println("please provide a valid rate of interest for "+ username+" of bank: "+bankname);
									break;
								}
								UserImp user = new UserImp(bankname, username, principal, duration, rate);

								helper.addActiveUserEntry(bankname, username, user);
								break;
							}
							case "PAYMENT": {
								int lumsum = Integer.parseInt(input[3]);
								if(lumsum < 0) {
									System.out.println("please provide a valid lumsum amount for "+ username+" of bank: "+bankname);
									break;
								}
								int emiNumber = Integer.parseInt(input[4]);
								if(emiNumber < 0) {
									System.out.println("please provide a valid EMI Number for "+ username+" of bank: "+bankname);
									break;
								}
								
								helper.repayment(bankname, username, lumsum, emiNumber);
								break;
							}
							case "BALANCE": {
								int emiNumber = Integer.parseInt(input[3]);
								if(emiNumber < 0) {
									System.out.println("please provide a valid EMI Number for "+ username+" of bank: "+bankname);
									break;
								}
								System.out.println(helper.showBalance(bankname, username, emiNumber));
								break;
							}
							default:
								System.err.println("Provide a valid input in file for "+ username+" of bank: "+bankname);
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							System.err.println("Please provide the valid arguements for " + str);

						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
