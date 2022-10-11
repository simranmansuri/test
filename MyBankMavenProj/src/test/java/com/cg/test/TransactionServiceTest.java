package com.cg.test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.dao.AccountDAO;
import com.cg.exception.InsufficientFundException;
import com.cg.model.Account;
import com.cg.service.TransactionService;
import com.cg.service.TransactionServiceImpl;

public class TransactionServiceTest {
	private AccountDAO mockdao;
	private TransactionService tservice;
	@Before
	public void tinit() {
		mockdao=mock(AccountDAO.class);
		tservice=new TransactionServiceImpl();
		tservice.setAccountDao(mockdao);
	}
	@After
	public void destroy() {
		mockdao=null;
		tservice=null;
	}
	
	@Test
	public void testgetAccountbyid() {
		Account ob=new Account(200,"Ram",25000.00);
		when(mockdao.getAccountById(200)).thenReturn(ob);
		
		assertNotNull(tservice.getAccountById(200));
		
		assertEquals(ob,tservice.getAccountById(200) );
		
		verify(mockdao,times(2)).getAccountById(200);
		
	}
	
	@Test
	public void withdrawtest() {
		Account ob=new Account(200,"Ram",25000.00);
		ob.setBalance(15000);
		doNothing().when(mockdao).updateAccount(ob);
		ob.setBalance(25000);
		try {
			assertEquals(15000.00, tservice.withdraw(10000.00, ob),0.05);
		} catch (InsufficientFundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(mockdao,times(1)).updateAccount(ob);
	}

}
