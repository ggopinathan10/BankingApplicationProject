package com.business.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;

import com.business.BankEntity.BankCustomerDetailsEntity;
import com.business.BankEntity.BankDetailsEntity;
import com.business.BankEntity.TransactionEntity;
import com.business.BankEntity.UserEntity;
import com.business.ExceptionHandler.BankingException;
import com.business.ExceptionHandler.UserAuthorizationException;

@Service
public class BankingDAOImpl implements BankingDAO{
	
	private static List<String> banksList;
	private static List<BankDetailsEntity> bankDetailsEntityList;
	private static List<BankCustomerDetailsEntity> allAccountsList;
	
	static {
		
		banksList = new ArrayList<String>();
		banksList.add("SBI Bank");
		banksList.add("HDFC Bank");
		banksList.add("AXIS Bank");
		banksList.add("ICICI Bank");
		banksList.add("CITI Bank");
		
		bankDetailsEntityList = new ArrayList<BankDetailsEntity>();
		bankDetailsEntityList.add(new BankDetailsEntity("SBI Bank", "gokul", "gokul", 10));
		bankDetailsEntityList.add(new BankDetailsEntity("HDFC Bank", "gokul", "gokul", 20));
		bankDetailsEntityList.add(new BankDetailsEntity("AXIS Bank", "gokul", "gokul", 30));
		bankDetailsEntityList.add(new BankDetailsEntity("ICICI Bank", "gokul", "gokul", 40));
		bankDetailsEntityList.add(new BankDetailsEntity("CITI Bank", "gokul", "gokul", 50));
		bankDetailsEntityList.add(new BankDetailsEntity("SBI Bank", "techchefs", "techchefs", 11));
		bankDetailsEntityList.add(new BankDetailsEntity("HDFC Bank", "techchefs", "techchefs", 21));
		bankDetailsEntityList.add(new BankDetailsEntity("ICICI Bank", "techchefs", "techchefs", 41));
		
		allAccountsList = new ArrayList<BankCustomerDetailsEntity>();
		allAccountsList.add(new BankCustomerDetailsEntity("gokul", 1, "Saving", "SBI Bank", new TransactionEntity(1, 3434.0, "Debit", new Date())));
		allAccountsList.add(new BankCustomerDetailsEntity("gokul", 2, "Current", "HDFC Bank", new TransactionEntity(2, 897234.59, "Credit", new Date())));
		allAccountsList.add(new BankCustomerDetailsEntity("techcehfs", 3, "Saving", "AXIS Bank", new TransactionEntity(3, 123.0, "Debit", new Date())));
		allAccountsList.add(new BankCustomerDetailsEntity("gokul", 4, "Current", "SBI Bank", new TransactionEntity(4, 3428.0, "Debit", new Date())));
		allAccountsList.add(new BankCustomerDetailsEntity("techcehfs", 6, "Saving", "HDFC Bank", new TransactionEntity(5, 23409.0, "Credit", new Date())));
		
		
	}
	
	@Override
	public Map<String, Object> registerUser(String username, String password, List<UserEntity> existingUsersList, String loggedinUsername)
			throws UserAuthorizationException {
		Map<String, Object> registrationMap = new HashMap<String, Object>();
		String registrationStatus = "";
		if(username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
			if(existingUsersList == null) {
				existingUsersList = new ArrayList<UserEntity>();
				existingUsersList.add(new UserEntity(username, password));
				loggedinUsername = username;
				registrationStatus = "Registration successful, you are currently logged in as "+username;
			}else {
				boolean isMatched = existingUsersList.stream().anyMatch((existingUser)->(username.equalsIgnoreCase(existingUser.getUsername())));
				if(isMatched){
					throw new UserAuthorizationException("This user already exists, Please try with a different username");
				}else {
					existingUsersList.add(new UserEntity(username, password));
					loggedinUsername = username;
					registrationStatus = "Registration successful, you are currently logged in as "+username;
				}
			}
		}else {
			throw new UserAuthorizationException("username and password cannot be left blank");
		}
		registrationMap.put("existingUsersList", existingUsersList);
		registrationMap.put("registrationStatus", registrationStatus);
		registrationMap.put("loggedinUsername", loggedinUsername);
		return registrationMap;
	}

	@Override
	public String checkUserCredentials(String username, String password, List<UserEntity> existingUsersList, String loggedinUsername) throws UserAuthorizationException {
		String loginStatus = "";
		if(username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
			if(existingUsersList != null) {
				boolean isMatched = existingUsersList.stream().anyMatch((existingUser)->(
						username.equalsIgnoreCase(existingUser.getUsername()) && password.equalsIgnoreCase(existingUser.getPassword())));
				if(isMatched) {
					loginStatus = "logged in successfully";
					loggedinUsername = username;
				}else {
					throw new UserAuthorizationException("Invalid username and password");
				}
			}else {
				throw new UserAuthorizationException("Invalid username and password");
			}
		}else {
			throw new UserAuthorizationException("username and password cannot be left blank");
		}
		
		return loginStatus;
	}

	

	@Override
	public String getAccountDetails(String bankName, List<BankCustomerDetailsEntity> bankDetailsEntityList, String loggedinUsername) throws BankingException {
		Optional<BankCustomerDetailsEntity> matchedBankCustomerDetailsEntity = bankDetailsEntityList.stream().filter((bankdetails)->loggedinUsername.equals(bankdetails.getUsername())
				&& bankName.equals(bankdetails.getBankName())).findFirst();
		if(matchedBankCustomerDetailsEntity.isPresent()) {
			BankCustomerDetailsEntity bde = matchedBankCustomerDetailsEntity.get();
			
			return "Account number : "+bde.getAccountNumber()+" Account type : "+bde.getAccountType();//sending as string instead of account details object to hide transaction details
		}else {
			throw new BankingException("You have not logged into" + bankName);
		}
	}

	@Override
	public TransactionEntity getAllTransactions(List<BankCustomerDetailsEntity> bankDetailsEntityList, int accountNumber) throws BankingException {
		Optional<BankCustomerDetailsEntity> matchedBankCustomerDetailsEntity = bankDetailsEntityList.stream().filter((bankdetails)->
		accountNumber == bankdetails.getAccountNumber()).findFirst();
		if(matchedBankCustomerDetailsEntity.isPresent()) {
			BankCustomerDetailsEntity bde = matchedBankCustomerDetailsEntity.get();
			return bde.getTransaction();
		}else {
			throw new BankingException("No transactions done with this account number");
		}
	}
	
	
	

	@Override
	public void loginToBank(List<BankDetailsEntity> bankDetailsEntityList, String bankName, String username, String password, int corpid) throws BankingException {
		if(loggedinUsername != null && !loggedinUsername.isEmpty()) {
			boolean isMember = bankDetailsEntityList.stream().anyMatch((bankdetails)->username.equals(bankdetails.getUsername())
					&& bankName.equals(bankdetails.getBankName()) && password.equals(bankdetails.getPassword()) && corpid == bankdetails.getCorpid())));
			if(!isMember) {
				throw new BankingException("You are not authorized to log into" +bankName);
			}
		}
	}
	
	/**
	 * hardcoding Bank names
	 * */
	public List<String> getBanksList() {
		return banksList;
	}
	
	/**
	 * hardcoding Bank data
	 * */
	public List<BankDetailsEntity> getBankDetails(){
		return bankDetailsEntityList;
		
	}
	
	/**
	 * hardcoding account data
	 * */
	public List<BankCustomerDetailsEntity> getAllAccountsList(){
		return allAccountsList;
	}
	
}