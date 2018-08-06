package com.business.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BankingAppGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserAuthorizationException.class)
	public ResponseEntity<ExceptionInfo> handleUserLoginException(UserAuthorizationException userAuthorizationException){
		System.out.println("inside excception handler logic");
		ExceptionInfo errorInfo = new ExceptionInfo(userAuthorizationException.getMessage(), "Authentication Failed");
		return new ResponseEntity<ExceptionInfo>(errorInfo, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(BankingException.class)
	public ResponseEntity<ExceptionInfo> handleUserLoginException(BankingException bankingException){
		System.out.println("inside excception handler logic");
		ExceptionInfo errorInfo = new ExceptionInfo(bankingException.getMessage(), "Authentication Failed");
		return new ResponseEntity<ExceptionInfo>(errorInfo, HttpStatus.UNAUTHORIZED);
	}

}
