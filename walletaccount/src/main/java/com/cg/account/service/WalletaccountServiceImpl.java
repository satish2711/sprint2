package com.cg.account.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.account.dao.WalletaccountdaoImpl;
import com.cg.account.entity.Walletaccount;



@Service
@Transactional
public class WalletaccountServiceImpl implements WalletaccountService{
	
	@Autowired
	WalletaccountdaoImpl dao;


	@Override
	public Walletaccount addAccount(int userid,Walletaccount wa) {
		
		return dao.addAccount(userid,wa);
	}
	
	@Override
	public Walletaccount deposit(int accountid1,double amount)
	{
		return dao.deposit(accountid1, amount);
	}
	@Override
	public double getbalance(int id)
	{
		return dao.getbalance(id);
	}
	@Override
	public Walletaccount fundTransfer(int fromaccountid,int toaccountid,double amount)
	{
		return dao.fundTransfer(fromaccountid,toaccountid,amount);
	}


}
