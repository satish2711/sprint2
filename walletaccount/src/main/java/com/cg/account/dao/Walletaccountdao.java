package com.cg.account.dao;

import com.cg.account.entity.Walletaccount;


public interface Walletaccountdao {
	
	public Walletaccount addAccount(int userid,Walletaccount wa) ;
	
	public Walletaccount deposit(int accountid1,double amount);
	
	
	public Walletaccount fundTransfer(int fromaccountid,int toaccountid,double amount);
	
	public double getbalance(int id);


}
