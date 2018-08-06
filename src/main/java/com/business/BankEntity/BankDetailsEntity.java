package com.business.BankEntity;


public class BankDetailsEntity {
	
	private String bankName;
	private String username;
	private String password;
	private int corpid;
	
	public BankDetailsEntity(String bankName, String username, String password, int corpid) {
		this.bankName = bankName;
		this.username = username;
		this.password = password;
		this.corpid = corpid;
	}
	
	public BankDetailsEntity() {
		super();
	}
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCorpid() {
		return corpid;
	}

	public void setCorpid(int corpid) {
		this.corpid = corpid;
	}

}
