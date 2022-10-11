package com.cg.exception;

public class InsufficientFundException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double balance;
	public InsufficientFundException() {
		// TODO Auto-generated constructor stub
	}
	public InsufficientFundException(double balance,String message ) {
		super(message);
		this.balance=balance;
	}
	@Override
	public String toString() {
		return "InsufficientFundException [balance=" + balance + "] : "+getMessage();
	}
	
	
}
