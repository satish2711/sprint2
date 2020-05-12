package com.cg.account.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cg.account.entity.Userdata;
import com.cg.account.entity.Walletaccount;
import com.cg.account.entity.Wallettransaction;


@Repository
public class WallettransactiondaoImpl implements Wallettransactiondao{
	
	@PersistenceContext	
	 EntityManager em;
	
	@Override
	public List<Wallettransaction> getAllTransaction() {
		Query q=em.createQuery("select m from Wallettransaction m");
		
		return q.getResultList();
		
	}

	@Override
	public List<Wallettransaction> getAllTransactionById(int userid) {
		Userdata userdata=em.find(Userdata.class, userid);
		if(userdata==null)
		{
			return null;
		}
	    int accountid=userdata.getWalletaccount().getAccountid();
		Walletaccount walletaccount=em.find(Walletaccount.class, accountid);
		if(walletaccount!=null){
		Query q=em.createQuery("select m from Wallettransaction m where m.accountid=?1");
		q.setParameter(1,accountid);
		
		return q.getResultList();
		}
		else
		{
			return Collections.emptyList();
		}
	}

}
