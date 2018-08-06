package com.business.BankEntity;

public class BankCustomerDetailsEntity {

	private String username;
	private int accountNumber;
	private String accountType;
	private String bankName;
	private TransactionEntity transaction;
	

	public BankCustomerDetailsEntity(String username, int accountNumber, String accountType, String bankName, TransactionEntity transaction) {
		this.username = username;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.bankName = bankName;
		this.transaction = transaction;
	}

	public BankCustomerDetailsEntity() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public TransactionEntity getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionEntity transaction) {
		this.transaction = transaction;
	}
}
