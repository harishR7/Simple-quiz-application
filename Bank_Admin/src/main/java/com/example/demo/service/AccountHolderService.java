package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.ObjenesisStd;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AccountHolder;
import com.example.demo.entity.MiniScript;
import com.example.demo.entity.MobileRequest;
import com.example.demo.repo.AccountHolderRepository;
import com.example.demo.repo.MobileRequestRepo;

@Service
public class AccountHolderService {
	
	@Autowired
	private AccountHolderRepository repo;
	
	@Autowired
	private AccountHolder account;
	
	@Autowired
	private MobileRequestRepo mobileReq;
	
	Map<Long,List<MiniScript>> miniScript=new TreeMap<>();
	
// Creating account details by user	
public AccountHolder add(Map<String, String> entity) {
		
		AccountHolder accountHolder=new AccountHolder();
		accountHolder.setAccountHolderName(entity.get("accountHolderName"));
		accountHolder.setAddress(entity.get("address"));
		accountHolder.setMobileNumber(Long.parseLong(entity.get("mobileNumber")));
		accountHolder.setDateOfBirth(LocalDate.parse(entity.get("dateOfBirth")));
		accountHolder.setStatus("Client");
		accountHolder.setPassword(entity.get("password"));
		
		return this.repo.save(accountHolder);
		
	}
	
// view account details by admin
	public List<AccountHolder> findAll(){
		
		return this.repo.findAll();
	}
	
// find a specific account detail by admin	
	public AccountHolder findByAccountNumber(long accountNumber) {
		
		return this.repo.findByAccountNumber(accountNumber);
	}
	
	
// find a account balance by both admin and client	
	public double balanceAmount(long accountNumber) {
		
		AccountHolder account = findByAccountNumber(accountNumber);
		
		double balance = account.getBalance();
		
		return balance;
	}
	
	
	
	// mobile request by client to change mobile number
	public Object mobilerequest(MobileRequest entity ) {
		
	
		
	if(this.repo.existsById(entity.getAccountNumber())) {
		   
		
//		     if((this.repo.findByMobileNumber(entity.getOldMobileNumber())).getMobileNumber()==entity.getOldMobileNumber()){
//			     return this.mobileReq.save(entity); 
//		       }
//		     else {
//			 return "old number is not matched";
//		        }
		     return this.mobileReq.save(entity); 
			 }
	else {
		return "Account does not exist";
	}
	}
	
	
	
	
	

	
	
	// mobile req viewed by admin
	public List<MobileRequest> viewMobileRequests(){
		return this.mobileReq.findAll();
	}
	

	
	//verify and update the number and delete it from mobilereq table by admin
	public int verifyMobileNumber(long mobileNumber,long accountNumber) {
		
		int num= this.repo.verifyMobileNumber( mobileNumber,accountNumber);
		
		 int num2=this.mobileReq.deleteInRequest(accountNumber);
		
			return (num==num2)?1:0; 
			
	}
	
// transfer amount from one to another
	public Object balanceAfterTransfer(double amount,
			long accountNumber1,long accountNumber2) {
		
		AccountHolder account1 = findByAccountNumber(accountNumber1);
		AccountHolder account2 = findByAccountNumber(accountNumber2);
		
		double amt1 = 0.0;
		double amt2 = 0.0;
		
		if (account1.getBalance() >= amount) {
			
			amt2 = account2.getBalance()+ amount;
			account2.setBalance(amt2);
			this.repo.balanceAfterTransfer1(amt2, accountNumber2);
			//setMiniScript(accountNumber2, amount,"Credit");

			
			amt1 = account1.getBalance()-amount;
			account1.setBalance(amt1);
			this.repo.balanceAfterTransfer(amt1, accountNumber1);
			setMiniScript(accountNumber1, amount,"Debit",LocalDateTime.now());

			
			
		}
		else{
			return  " Not enough money to Transfer";
		}
		
		return account1.getBalance();
	}
	public void setMiniScript(long accountNumber, double amount, String type,LocalDateTime dateTime) {
		List<MiniScript> amountList=new ArrayList<MiniScript>();
		
		if(miniScript.containsKey(accountNumber)) {
			amountList=miniScript.get(accountNumber);
			MiniScript scriptValues=new MiniScript(dateTime, amount, type);
			amountList.add(scriptValues);
		}
		else {
			
			miniScript=new TreeMap<>();
			MiniScript scriptValues=new MiniScript(dateTime, amount, type);
			amountList.add(scriptValues);
		}

		miniScript.put(accountNumber, amountList);
	}

	
	public List<MiniScript> getMiniScript(long accountNumber) {
		return miniScript.get(accountNumber);
	}




// add amount to any account by admin
	
public  Object addAmount(double amount,long accountNumber1) {
	AccountHolder account1 = findByAccountNumber(accountNumber1);
	
	double amt = 0.0;
	
	amt = account1.getBalance()+ amount;
	
	account1.setBalance(amt);
	this.repo.balanceAfterTransfer(amt, accountNumber1);
	
	return account1.getBalance();
}


public Object changePassword(long accountNumber,String newpassword) {
	int number=1;
	if(number== this.repo.changePassword(newpassword, accountNumber)) {
		return "Password Successfully changed";
	}
	else {
		return "Password not changed";
	}
	
}

	}

	



	
	

