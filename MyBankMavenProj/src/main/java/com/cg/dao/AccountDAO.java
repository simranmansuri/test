package com.cg.dao;

import java.util.List;

import com.cg.model.Account;

public interface AccountDAO {
	public void insertAccount(Account ac);
	public void updateAccount(Account ac);
	public void removeAccount(int aid);
	public boolean transferMoney(Account fromAccount,Account toAccount);
	public Account getAccountById(int account_no);
	public List<Account> getAllAccounts();
}
