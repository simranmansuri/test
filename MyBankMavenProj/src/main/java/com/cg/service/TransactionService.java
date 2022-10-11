package com.cg.service;

import java.util.List;

import com.cg.dao.AccountDAO;
import com.cg.exception.InsufficientFundException;
import com.cg.model.Account;

public interface TransactionService {
	public double withdraw(double amount,Account account) throws InsufficientFundException; 
	public double deposite(double amount,Account account);
	public boolean transferMoney(double amount,Account account,Account toaccount) throws InsufficientFundException;
	public Account getAccountById(int account_no);
	public List<Account> getAllAccounts();
	public void getDetails(String details);
	public String getMsg() ;
	public void setAccountDao(AccountDAO accountDao);

	
}