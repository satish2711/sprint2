package com.cg.account;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.cg.account.dao.WalletaccountdaoImpl;
import com.cg.account.entity.Userdata;
import com.cg.account.entity.Walletaccount;
import com.cg.account.service.WalletaccountServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WalletaccountApplicationTests {
	
	@Mock
	WalletaccountdaoImpl walletaccountdao;
	
	@InjectMocks
	WalletaccountServiceImpl service;
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}
	
		@Test
		public void testAddAccount()
		{
			Walletaccount walletaccount = new Walletaccount(520,10000,"adding account");
			Userdata userdata = new Userdata(120,"Charan","Charan123",9845633112L,"charan@gmail.com");
			service.addAccount(userdata.getUserId(), walletaccount);
			Mockito.verify(walletaccountdao,Mockito.times(1)).addAccount(userdata.getUserId(), walletaccount);
			
			}
		
		@Test
		public void testdeposit()
		{
			Walletaccount walletaccount = new Walletaccount(520,10000,"adding amount");
			Walletaccount walaccount = new Walletaccount(520,9000,"adding amount");	
			
			when(walletaccountdao.deposit(walletaccount.getAccountid(), 1000)).thenReturn(walaccount);
			
			assertEquals(9000.0,service.deposit(walletaccount.getAccountid(), 1000).getAccountbalance(),0);		
		}
		
		
		@Test
		public void test_deposit()
		{
			Walletaccount walletaccount = new Walletaccount(520,10000,"adding amount");
			Walletaccount walaccount = new Walletaccount(520,9000,"adding amount");	
			
			when(walletaccountdao.deposit(walletaccount.getAccountid(), 1000)).thenReturn(walaccount);
			
			assertEquals(5000.0,service.deposit(520, 1000).getAccountbalance(),0);	
			
		}
		

		@Test
		public void testfundtransfer(){
			
			when(walletaccountdao.fundTransfer(520,
					                       521,1000)).thenReturn(new Walletaccount(520,24000,"transfered"));
			
			assertEquals(24000,service.fundTransfer(520,521, 1000).getAccountbalance(),0);		
		}
		
		
		
		@Test
		public void test_fundtransfer(){
			
			when(walletaccountdao.fundTransfer(520,
					                       521,1000)).thenReturn(new Walletaccount(520,24000,"transfered"));
			
			assertEquals(24000,service.fundTransfer(520,1, 1000).getAccountbalance(),0);		
		}
		
		
		@Test
		public void getbalance(){
			
			Walletaccount walletaccount = new Walletaccount(520,10000,"adding account");
			Userdata userdata = new Userdata(120,"Charan","Charan123",9845633112L,"charan@gmail.com",walletaccount);
			
			   when(walletaccountdao.getbalance(userdata.getUserId())).thenReturn(walletaccount.getAccountbalance());
			   double balance=service.getbalance(120);
			   assertEquals(10000.0,balance,0);			
		}
		
		@Test
		public void get_balance(){
			
			Walletaccount walletaccount = new Walletaccount(520,10000,"adding account");
			Userdata userdata = new Userdata(120,"Charan","Charan123",9845633112L,"charan@gmail.com",walletaccount);
			
			   when(walletaccountdao.getbalance(userdata.getUserId())).thenReturn(walletaccount.getAccountbalance());
			   double balance=service.getbalance(000);
			   assertEquals(10000.0,balance,0);			
		}
}
