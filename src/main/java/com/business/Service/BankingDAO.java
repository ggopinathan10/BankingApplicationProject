package com.business.Service;

import java.util.List;
import java.util.Map;

import com.business.BankEntity.BankCustomerDetailsEntity;
import com.business.BankEntity.BankDetailsEntity;
import com.business.BankEntity.TransactionEntity;
import com.business.BankEntity.UserEntity;
import com.business.ExceptionHandler.BankingException;
import com.business.ExceptionHandler.UserAuthorizationException;

public interface BankingDAO {
	
	public Map<String, Object> registerUser(String username, String password, List<UserEntity> existingUsersList, String loggedinUsername) throws UserAuthorizationException;
	
	public String checkUserCredentials(String username, String password, List<UserEntity> existingUsersList, String loggedinUsername) throws UserAuthorizationException;
	
	public String getAccountDetails(String bankName, List<BankCustomerDetailsEntity> bankDetailsEntityList, String loggedinUsername) throws BankingException;
	
	public TransactionEntity getAllTransactions(List<BankCustomerDetailsEntity> bankDetailsEntityList, int accountNumber) throws BankingException;
	
	public void loginToBank(List<BankDetailsEntity> bankDetailsEntityList, String bankName, String username, String password, int corpid) throws BankingException;

}
