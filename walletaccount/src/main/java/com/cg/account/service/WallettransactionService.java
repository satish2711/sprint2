package com.cg.account.service;

import java.util.List;

import com.cg.account.entity.Wallettransaction;

public interface WallettransactionService {
	
	List<Wallettransaction> getAllTransaction();
	
	List<Wallettransaction> getAllTransactionById(int accountid);

}
