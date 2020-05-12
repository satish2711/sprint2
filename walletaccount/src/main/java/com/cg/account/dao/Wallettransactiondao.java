package com.cg.account.dao;

import java.util.List;

import com.cg.account.entity.Wallettransaction;

public interface Wallettransactiondao {
	
List<Wallettransaction> getAllTransaction();
	
	List<Wallettransaction> getAllTransactionById(int accountid);

}
