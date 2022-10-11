package com.cg.dao;

import java.util.ArrayList;
import java.util.List;

import com.cg.model.Account;
import com.cg.util.DbUtils;

import java.sql.*;
public class AccountDAOImpl implements AccountDAO{

	private Connection con;
	private PreparedStatement pst;
	
	private void makeConnection()
	{
		try{
			//con=   DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
			//con=   DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XEPDB1","hr","hr");
			con=DbUtils.getConnection();
			 System.out.println("Connected");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void releaseConnection()
	{
		try{
			con.close();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}

	@Override
	public void insertAccount(Account ac) {
		try{
			makeConnection();
			pst=con.prepareStatement("insert into Account(aid,holder,balance) values(?,?,?)");
			pst.setInt(1, ac.getAccountNumber());
			pst.setString(2, ac.getAccountHolder());
			pst.setDouble(3, ac.getBalance());
			int i=pst.executeUpdate();
			System.out.println("Inserted Account records "+i);
			releaseConnection();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}

	@Override
	public void updateAccount(Account ac) {
		try{
			makeConnection();
			pst=con.prepareStatement("update Account  set holder=?,balance=? where aid=?");
			pst.setInt(3, ac.getAccountNumber());
			pst.setString(1, ac.getAccountHolder());
			pst.setDouble(2, ac.getBalance());
			int i=pst.executeUpdate();
			System.out.println("updated Account records "+i);
			releaseConnection();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		
	}

	@Override
	public void removeAccount(int aid) {
		try{
			Account ac=getAccountById(aid);
			makeConnection();
			
			pst=con.prepareStatement("delete Account  where aid=?)");
			pst.setInt(1, ac.getAccountNumber());
			int i=pst.executeUpdate();
			System.out.println("deleted Account records "+i);
			releaseConnection();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		
	}

	@Override
	public boolean transferMoney(Account fromAccount, Account toAccount) {
		boolean success=false;
		try
		{
			makeConnection();
			con.setAutoCommit(false);
			pst=con.prepareStatement("update Account  set holder=?,balance=? where aid=?)");
			pst.setInt(3, fromAccount.getAccountNumber());
			pst.setString(1, fromAccount.getAccountHolder());
			pst.setDouble(2, fromAccount.getBalance());
			int i=pst.executeUpdate();
			System.out.println("updated Account records "+i);
			pst=con.prepareStatement("update Account  set holder=?,balance=? where aid=?)");
			pst.setInt(3, toAccount.getAccountNumber());
			pst.setString(1, toAccount.getAccountHolder());
			pst.setDouble(2, toAccount.getBalance());
			i=pst.executeUpdate();
			System.out.println("updated Account records "+i);
			con.commit();
			success=true;
		}
		catch(SQLException e)
		{
			success=false;
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		finally{
			releaseConnection();
		}
		return success;
	}

	@Override
	public Account getAccountById(int account_no) {
		Account ob=new Account();
		try{
			makeConnection();
			pst=con.prepareStatement("select * from Account where aid=?");
			pst.setInt(1, account_no);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				ob.setAccountNumber(rs.getInt("aid"));
				ob.setAccountHolder(rs.getString("holder"));
				ob.setBalance(rs.getDouble("balance"));
			}
			
			
			releaseConnection();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		return ob;
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> alist=new ArrayList<Account>();
		try{
			makeConnection();
			pst=con.prepareStatement("select * from Account");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Account ob=new Account();
				ob.setAccountNumber(rs.getInt("aid"));
				ob.setAccountHolder(rs.getString("holder"));
				ob.setBalance(rs.getDouble("balance"));
				alist.add(ob);
			}
			
			
			releaseConnection();
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		return alist;
	}

	
}
