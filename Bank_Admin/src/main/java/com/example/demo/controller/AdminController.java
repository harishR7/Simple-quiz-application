package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AccountHolder;
import com.example.demo.entity.MobileRequest;
import com.example.demo.service.AccountHolderService;

@RestController
@RequestMapping (path ="/api/v1/admin")
public class AdminController {

	@Autowired
	private AccountHolderService service;
	
	
	//find all details by Admin
	@GetMapping
	public List<AccountHolder> findAll(){
		
		return this.service.findAll();
	}
 	
	
	// find a specific AccountDetails by Admin
	@GetMapping(path = "/{accountNumber}")
  public AccountHolder findByAccountNumber(@PathVariable("accountNumber") long accountNumber) {
		
		return this.service.findByAccountNumber(accountNumber);
	}
	
	@GetMapping(path = "/balance/{accountNumber}")
	public double balanceAmount(@PathVariable("accountNumber") long accountNumber) {
		
		return this.service.balanceAmount(accountNumber);
	}
	
	
	
	@GetMapping(path = "/mobilereq/view")
	public List<MobileRequest> viewMobileReq() {
		return this.service.viewMobileRequests();
	}
	
	@PutMapping(path = "/mobileupdate/{mobileNumber}/{accountNumber}")
	public int verifyMobileNo(@PathVariable("accountNumber")long accountNumber,@PathVariable("mobileNumber") long mobileNumber) {
		return this.service.verifyMobileNumber(mobileNumber , accountNumber );
}
	
	
	
	@PutMapping(path = "/transfer/{accountNumber1}/{accountNumber2}/{amount}")
	public Object transferAmount(@PathVariable("accountNumber1")long accountNumber1,@PathVariable("accountNumber2")long accountNumber2,@PathVariable("amount")double amount) {
	
		return this.service.balanceAfterTransfer(amount, accountNumber1, accountNumber2);
	}
	

	
	@PutMapping(path = "/addamount/{accountNumber1}/{amount}")
	public Object addAmountToAccount(@PathVariable("accountNumber1")long accountNumber1,@PathVariable("amount")double amount) {
	
		return this.service.addAmount(amount, accountNumber1);
	}
	
	
}
