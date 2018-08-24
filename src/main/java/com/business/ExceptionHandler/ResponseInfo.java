package com.business.ExceptionHandler;

public class ExceptionInfo {
	
	private String info;
	private String errorMessage;

	

	public ExceptionInfo(String info, String errorMessage) {
		super();
		this.errorMessage = errorMessage;
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
