package com.cg.service;

import java.util.List;

import com.cg.dao.AccountDAO;
import com.cg.dao.AccountDAOImpl;
import com.cg.exception.InsufficientFundException;
import com.cg.model.Account;

public class TransactionServiceImpl implements TransactionService {
	private AccountDAO accountDao;
	private String msg;
	public TransactionServiceImpl() {
		// TODO Auto-generated constructor stub
		accountDao=new AccountDAOImpl();
	}
	public void setAccountDao(AccountDAO accountDao) {
		this.accountDao = accountDao;
	}
	



	@Override
	public  double withdraw(double amount,Account account) throws InsufficientFundException {
		
		
		double currentBalance=account.getBalance();
		if(amount>0 && currentBalance>amount && (currentBalance-amount>1000.00))
		{
			currentBalance=currentBalance-amount;
		}
		else
		{
			throw new InsufficientFundException(currentBalance," LESS balance");
		}
		account.setBalance(currentBalance);
		accountDao.updateAccount(account);
		msg="Withdraw Details \n"+
				"==============\n"+
				 "Withdrawn amount Rs."+amount +
				 " from Account No. "+account.getAccountNumber()+"\n"+
				 "Remaining balance Rs."  + currentBalance;
		return currentBalance;
	}

	@Override
	public double deposite(double amount,Account account) {
		// TODO Auto-generated method stub
		double currentBalance=account.getBalance();
		currentBalance=currentBalance+amount;
		account.setBalance(currentBalance);
		accountDao.updateAccount(account);
		msg="Deposite Details \n"+
				"==============\n"+
				 "Deposited amount Rs."+amount +
				 " to Account No. "+account.getAccountNumber()+"\n"+
				 "Remaining balance Rs."  + currentBalance;
		return currentBalance;
	}

	@Override
	public boolean transferMoney(double amount,Account account, Account toaccount) throws InsufficientFundException{
		// TODO Auto-generated method stub
		double currentBalance1=account.getBalance();
		if(amount>0 && currentBalance1>amount && (currentBalance1-amount)>1000.00)
		{
			currentBalance1=currentBalance1-amount;
		}
		else
		{
			throw new InsufficientFundException(currentBalance1," LESS balance");
		}
		account.setBalance(currentBalance1);
		
				msg="Transaction Details \n"+
				"=====================\n"+
				 "Withdrawn amount Rs."+amount +
				 " from Account No. "+account.getAccountNumber()+"\n"+
				 "Remaining balance Rs."  + currentBalance1;
				
		double currentBalance2=toaccount.getBalance();
		currentBalance2=currentBalance2+amount;
		toaccount.setBalance(currentBalance2);
				msg+="Deposite Details \n"+
				"==============\n"+
				 "Deposited amount Rs."+amount +
				 " to Account No. "+toaccount.getAccountNumber()+"\n"+
				 "Remaining balance Rs."  + currentBalance2;
				return accountDao.transferMoney(account, toaccount) ;
				//return true;
	}

	public String getMsg() {
		return msg;
	}
	
	@Override
	public void getDetails(String details) {
		// TODO Auto-generated method stub
		System.out.println("===============");
		System.out.println(details);
		System.out.println("===============");
	}


	@Override
	public Account getAccountById(int account_no) {
		// TODO Auto-generated method stub
		return accountDao.getAccountById(account_no);
	}


	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accountDao.getAllAccounts();
	}

}
