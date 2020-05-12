package com.cg.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.account.entity.Walletaccount;
import com.cg.account.exceptions.AmountLessThanZeroException;
import com.cg.account.exceptions.IdNotFoundException;
import com.cg.account.service.WalletaccountService;


@RestController
@RequestMapping("/account")
@CrossOrigin("http://localhost:4200")
public class WalletaccountController {
	
		@Autowired
		WalletaccountService serviceobj;

		
		@PostMapping("/addAccount/{userid}")
		public ResponseEntity<String> addAccount(@PathVariable("userid") int userid,@RequestBody Walletaccount wa) {
			Walletaccount walletaccount = serviceobj.addAccount(userid,wa);
			if (walletaccount == null) {
				throw new IdNotFoundException("Enter Valid Id");
			} else {
				return new ResponseEntity<>("Account created successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		
		@PutMapping("/Deposit/{accountid1}/{amount}")
		public ResponseEntity<String> deposit(@PathVariable("accountid1") int accountid1,@PathVariable("amount") double amount) {
			Walletaccount walletaccount = serviceobj.deposit(accountid1, amount);
		     if(amount<0&&walletaccount==null)
			{
				throw new AmountLessThanZeroException("Amount cannot be less than zero");	
			} 
		     else if (walletaccount == null&&amount>0) {
					throw new IdNotFoundException(" Operation Unsuccessful, id not found");
				}
			else {
				return new ResponseEntity<>("Amount deposited successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		@GetMapping("/GetBalance/{accountid}")
		public ResponseEntity<Double> getbalance(@PathVariable("accountid") int accountid) {
			double balance = serviceobj.getbalance(accountid);
			if (balance==0) {
				throw new IdNotFoundException("Id does not exist,so we couldn't fetch details");
			} else {
				return new ResponseEntity<>(balance, new HttpHeaders(), HttpStatus.OK);
			}
		}
		@PutMapping("/fundtransfer/{fromaccountid}/{toaccountid}/{amount}")
		public ResponseEntity<String> fundTransfer(@PathVariable("fromaccountid") int fromaccountid,@PathVariable("toaccountid") int toaccountid,@PathVariable("amount") double amount) {
			Walletaccount walletaccount = serviceobj.fundTransfer(fromaccountid, toaccountid, amount);
		     if(amount<0&&walletaccount==null)
			{
				throw new AmountLessThanZeroException("Amount cannot be less than zero");	
			} 
		     else if (walletaccount == null&&amount>0) {
					throw new IdNotFoundException(" Operation Unsuccessful, id not found");
				}
			else {
				return new ResponseEntity<>("Amount transfered successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}

}
