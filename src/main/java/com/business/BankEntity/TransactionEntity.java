package com.business.BankEntity;

import java.util.Date;

public class TransactionEntity {

	
	private int accountNumber;
	private double transactionAmount;
	private String transactionType;
	private Date transactionDate;
	

	public TransactionEntity(int accountNumber, double transactionAmount, String transactionType, Date transactionDate) {
		this.accountNumber = accountNumber;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
	}
	
	public TransactionEntity() {
		super();
	}
	
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
