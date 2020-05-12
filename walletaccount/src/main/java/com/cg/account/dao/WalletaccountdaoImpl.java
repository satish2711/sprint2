package com.cg.account.dao;

import java.time.LocalDate;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cg.account.entity.Userdata;
import com.cg.account.entity.Walletaccount;
import com.cg.account.entity.Wallettransaction;
import com.cg.account.exceptions.AccountWithUserIdExistsException;


@Repository
public class WalletaccountdaoImpl implements Walletaccountdao{
	
	@PersistenceContext	
	 EntityManager em;
	
	@Override
	public Walletaccount addAccount(int userid,Walletaccount wa) {
		
		
		Userdata userdata=em.find(Userdata.class, userid);
		if(userdata==null)
		{
			return null;
		}
		if(userdata.getWalletaccount()==null)
		{
			Walletaccount walletaccount=em.merge(wa);
			userdata.setWalletaccount(walletaccount);
			return walletaccount ;
		}
		else
			throw new AccountWithUserIdExistsException("account with this userid exists");
	}
		
	@Override
	public Walletaccount deposit(int id1, double amount) {
		Userdata userdata=em.find(Userdata.class,id1 );
		if(userdata==null)
		{
			return null;
		}
		
		 int accountid1=userdata.getWalletaccount().getAccountid();
		  Walletaccount walletaccount=em.find(Walletaccount.class,accountid1);
		  long transactionID=new Random().nextInt(100000);
			if((walletaccount!=null)&&(amount>0))
			{
				Wallettransaction wt=new Wallettransaction();
				wt.setAccountid(accountid1);
				wt.setAccountbalance(walletaccount.getAccountbalance()+amount);
				wt.setAmount(amount);
				wt.setDescription("Deposited");
				LocalDate localdate=LocalDate.now();
				wt.setTransactionDate(localdate);
				wt.setTransactionID(transactionID);
				em.merge(wt);
				walletaccount.setAccountbalance(walletaccount.getAccountbalance()+amount);
				walletaccount.setStatus("Deposited");
				
				return walletaccount;
			}
			else
			{
				return null;
			}
	}
	
	@Override
      public Walletaccount fundTransfer(int id, int toaccountid, double amount) {
		Userdata userdata=em.find(Userdata.class,id );
		if(userdata==null)
		{
			return null;
		}
		  int fromaccountid=userdata.getWalletaccount().getAccountid();
    	  Walletaccount walletaccount1=em.find(Walletaccount.class,fromaccountid);
    	  Walletaccount walletaccount2=em.find(Walletaccount.class,toaccountid);
    	  long transactionID=new Random().nextInt(100000);
    	  if((walletaccount1!=null)&&(walletaccount2!=null)&&(walletaccount1.getAccountbalance()>amount))
    	  {
    		  Wallettransaction wt=new Wallettransaction();
				wt.setAccountid(fromaccountid);
				wt.setAccountbalance(walletaccount1.getAccountbalance()-amount);
				wt.setAmount(amount);
				wt.setDescription("Transfered money");
				LocalDate localdate=LocalDate.now();
				wt.setTransactionDate(localdate);
				wt.setTransactionID(transactionID);
				em.merge(wt);
    		  
    		  walletaccount1.setAccountbalance(walletaccount1.getAccountbalance()-amount);
    		  walletaccount1.setStatus("withdrawn(transfered to other account)");
    		  walletaccount2.setAccountbalance(walletaccount2.getAccountbalance()+amount);
    		  walletaccount2.setStatus("Deposited from other account");
    		  return walletaccount1;
    	  }
    	  else{
    		  return null;  
    	  }
	 }
	


	@Override
	public double getbalance(int id) {
		double balance = 0;
		
		Userdata userdata=em.find(Userdata.class, id);
		if(userdata!=null)
		{
			int accountid=userdata.getWalletaccount().getAccountid();
		
		Walletaccount walletaccount=em.find(Walletaccount.class,accountid);
		if(walletaccount!=null)
		{
		      balance=walletaccount.getAccountbalance();
		}
		}
		return balance;
	}
	
}
