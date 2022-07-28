package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AccountHolder;
import com.example.demo.entity.MiniScript;
import com.example.demo.entity.MobileRequest;
import com.example.demo.service.AccountHolderService;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping (path ="/api/v1/details")
@CrossOrigin(origins ="*")
public class AccountHolderController {

	@Autowired
	private AccountHolderService service;
	
	@Autowired
	private AdminService admin;
	
	
	@PostMapping(path="/accountHolder/add")
	public AccountHolder ClientDetailsAdd( @RequestBody Map<String,String>entity) {
		
		return this.service.add(entity);
	}
	
	
	
	@GetMapping(path = "/balance/{accountNumber}")
	public double balanceAmount(@PathVariable("accountNumber") long accountNumber) {
		
		return this.service.balanceAmount(accountNumber);
	}
	
	
	@PostMapping(path = "/mobilereq")
	public Object mobileReq(@RequestBody MobileRequest entity) {
		return this.service.mobilerequest(entity);
	}
	


	
	
	@PutMapping(path = "/transfer/{accountNumber1}/{accountNumber2}/{amount}")
	public Object transferAmount(@PathVariable("accountNumber1")long accountNumber1,@PathVariable("accountNumber2")long accountNumber2,@PathVariable("amount")double amount) {
	
		return this.service.balanceAfterTransfer(amount, accountNumber1, accountNumber2);
	}
	
	@PutMapping(path = "/changepassword/{accountNumber}/{newpassword}")
	public Object changePassword(@PathVariable("accountNumber")long accountNumber,@PathVariable("newpassword") String newpassword) {
		return this.service.changePassword(accountNumber, newpassword);
	}

	@GetMapping(path="/view/miniscript/{accountnumber}")
	public List<MiniScript> getMiniStatement(@PathVariable("accountnumber")long accountNumber){
		return this.service.getMiniScript(accountNumber);
	}
	
}
