package com.cg.pl;

import com.cg.exception.InsufficientFundException;
import com.cg.model.Account;
import com.cg.service.TransactionService;
import com.cg.service.TransactionServiceImpl;

public class ATMDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Account ob1=new Account(100,"Ram",50000.00);
		Account ob2=new Account(101,"Sham",20000.00);
		*/



		TransactionService trservice=new TransactionServiceImpl();
		Account ob1=trservice.getAccountById(100);
		System.out.println(ob1);
	

		Account ob2=trservice.getAccountById(102);
		System.out.println(ob2);

		try {
			double bal1=trservice.withdraw(10000.00,ob1);
		} catch (InsufficientFundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trservice.getDetails(trservice.getMsg());
		double bal2=trservice.deposite(20000.00,ob1);
		trservice.getDetails(trservice.getMsg());
		
		try {
			trservice.transferMoney(25000.00,ob1, ob2);
		} catch (InsufficientFundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trservice.getDetails(trservice.getMsg());
	}

}
