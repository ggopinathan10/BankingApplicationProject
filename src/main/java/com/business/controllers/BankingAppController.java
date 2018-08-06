package com.business.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.HttpMethod;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.BankEntity.BankCustomerDetailsEntity;
import com.business.BankEntity.BankDetailsEntity;
import com.business.BankEntity.TransactionEntity;
import com.business.BankEntity.UserEntity;
import com.business.ExceptionHandler.BankingException;
import com.business.ExceptionHandler.UserAuthorizationException;
import com.business.Service.BankingDAOImpl;

@RestController
@RequestMapping("/api")
public class BankingAppController {
	
	@Autowired
	private BankingDAOImpl bankingService;	
	private List<UserEntity> existingUsersList;
	private List<String> banksList;
	private List<BankDetailsEntity> bankDetailsEntityList;
	private List<BankCustomerDetailsEntity> allAccountsList;

	private String loggedinUsername;//created a global variable for logged in user(can be done using session scope as well)
	
	/**
	 * post method with request body
	 * */
	@PostMapping(value = "/register")
	public String register(@RequestBody UserEntity user) throws UserAuthorizationException {
		Map<String, Object> registrationMap = bankingService.registerUser(user.getUsername(), user.getPassword(), existingUsersList, loggedinUsername);
		setExistingUsersList((ArrayList<UserEntity>)registrationMap.get("existingUsersList")); 
		String registrationStatus = (String)registrationMap.get("registrationStatus");
		setLoggedinUsername((String)registrationMap.get("loggedinUsername"));
		return registrationStatus;
	}
	
	/**
	 * post method without request body
	 * */
	@PostMapping(value = "/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) throws UserAuthorizationException {
		loggedinUsername = "";
		return bankingService.checkUserCredentials(username, password, existingUsersList, loggedinUsername);
	}
	
	@GetMapping(value = "/getBankList")
	public List<String> getBankList() {
		if(banksList == null) {
			setBanksList(bankingService.getBanksList());
		}
		if(bankDetailsEntityList == null) {
			setBankDetailsEntityList(bankingService.getBankDetails());
		}
		if(allAccountsList == null) {
			setAllAccountsList(bankingService.getAllAccountsList());
		}
		return banksList;
	}
	
	@GetMapping(value = "/getBankData/{bankName}")
	public void getBankData(@PathVariable String bankName) {
		
	}
	
	/**
	 * passing username, password and corpid as json input from request body
	 * 
	 **/
	
	@PostMapping(value = "/logintoBank/{bankName}")
	public void loginToBank(@PathVariable String bankName, @RequestBody BankDetailsEntity bankDetailsEntity) throws BankingException {
		bankingService.loginToBank(bankDetailsEntityList, bankName, bankDetailsEntity.getUsername(), bankDetailsEntity.getPassword(), bankDetailsEntity.getCorpid());
	}
	
	@GetMapping(value = "/getAccounts/{bankName}")
	public String getAccounts(@PathVariable String bankName) throws BankingException {
		return bankingService.getAccountDetails(bankName, allAccountsList, loggedinUsername);
	}
	
	@GetMapping(value = "/getTransactionData/{accountNumber}")
	public TransactionEntity getTransactionData(@PathVariable int accountNumber) throws BankingException {
		return bankingService.getAllTransactions(allAccountsList, accountNumber);
	}
	
	@GetMapping(value = "/loggedinusername")
	public String getTransactionData() {
		return loggedinUsername;
	}
	
	public BankingDAOImpl getBankingService() {
		return bankingService;
	}

	public void setBankingService(BankingDAOImpl bankingService) {
		this.bankingService = bankingService;
	}

	public List<UserEntity> getExistingUsersList() {
		return existingUsersList;
	}

	public void setExistingUsersList(List<UserEntity> existingUsersList) {
		this.existingUsersList = existingUsersList;
	}

	public List<String> getBanksList() {
		return banksList;
	}

	public void setBanksList(List<String> banksList) {
		this.banksList = banksList;
	}

	public List<BankDetailsEntity> getBankDetailsEntityList() {
		return bankDetailsEntityList;
	}

	public void setBankDetailsEntityList(List<BankDetailsEntity> bankDetailsEntityList) {
		this.bankDetailsEntityList = bankDetailsEntityList;
	}

	public String getLoggedinUsername() {
		return loggedinUsername;
	}

	public void setLoggedinUsername(String loggedinUsername) {
		this.loggedinUsername = loggedinUsername;
	}
	
	public List<BankCustomerDetailsEntity> getAllAccountsList() {
		return allAccountsList;
	}

	public void setAllAccountsList(List<BankCustomerDetailsEntity> allAccountsList) {
		this.allAccountsList = allAccountsList;
	}

}
