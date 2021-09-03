package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import account.UserImp;
import helper.SystemHelper;

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
						String input[] = str.split("\\s+");
						String bankname = input[1];
						String username = input[2];
						switch (input[0]) {
						case "LOAN": {
							int principal = Integer.parseInt(input[3]);
							int duration = Integer.parseInt(input[4]);
							int rate = Integer.parseInt(input[5]);
							UserImp user = new UserImp(bankname, username, principal, duration, rate);
//							System.out.println(user.toString());
							
							helper.addActiveUserEntry(bankname, username, user);
							break;
						}
						case "PAYMENT": {
							int lumsum = Integer.parseInt(input[3]);
							int emiNumber = Integer.parseInt(input[4]);
							helper.repayment(bankname, username, lumsum, emiNumber);
							break;
						}
						case "BALANCE": {
							int emiNumber = Integer.parseInt(input[3]);
							System.out.println(helper.showBalance(bankname, username, emiNumber));
							break;
						}
						default:
							System.err.println("Provide a valid input in file");
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
