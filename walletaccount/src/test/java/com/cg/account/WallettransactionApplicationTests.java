package com.cg.account;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.cg.account.dao.WallettransactiondaoImpl;
import com.cg.account.entity.Wallettransaction;
import com.cg.account.service.WallettransactionServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WallettransactionApplicationTests {
	
	 @Mock
	 WallettransactiondaoImpl wallettransactiondao;
	 
	 @InjectMocks
	 WallettransactionServiceImpl wallettransactionservice;
	 
	 @Before
     public void setup(){
	MockitoAnnotations.initMocks(this);
}

	       @Test
		public void testGetAllTransactions()
		{
			LocalDate localdate=LocalDate.now();
			List<Wallettransaction> transactionList = new ArrayList<Wallettransaction>();
			
			transactionList.add(new Wallettransaction(28L,2000,"withdrawn",15000,561,localdate));
	        transactionList.add(new Wallettransaction(30L,5000,"deposited",10000,562,localdate));
	        transactionList.add(new Wallettransaction(31L,1000,"withdrawn",20000,560,localdate));
	        
			
			when(wallettransactiondao.getAllTransaction()).thenReturn(transactionList);
			List<Wallettransaction> result = wallettransactionservice.getAllTransaction();
			
            assertEquals(3, result.size());
		}
		
	       
	       @Test
			public void test_GetAllTransactions()
			{
				LocalDate localdate=LocalDate.now();
				List<Wallettransaction> transactionList = new ArrayList<Wallettransaction>();
				
				transactionList.add(new Wallettransaction(28L,2000,"withdrawn",15000,561,localdate));
		        transactionList.add(new Wallettransaction(30L,5000,"deposited",10000,562,localdate));
		        transactionList.add(new Wallettransaction(31L,1000,"withdrawn",20000,560,localdate));
		        
				
				when(wallettransactiondao.getAllTransaction()).thenReturn(transactionList);
				List<Wallettransaction> result = wallettransactionservice.getAllTransaction();
				
	            assertEquals(4, result.size());
			}
	       
	       
		@Test
		public void testgetAllTransactionById(){
			List<Wallettransaction> transactionList = new ArrayList<Wallettransaction>();
			
			Wallettransaction wt=new Wallettransaction();
			wt.setAccountid(560);
			wt.setAccountbalance(5000);
			wt.setAmount(100);
			wt.setDescription("Deposited");
			LocalDate localdate=LocalDate.now();
			wt.setTransactionDate(localdate);
			wt.setTransactionID(27);
			transactionList.add(wt);
			
			when(wallettransactiondao.getAllTransactionById(wt.getAccountid())).thenReturn(transactionList);
			
			List<Wallettransaction> result = wallettransactionservice.getAllTransactionById(560);
			
			assertEquals(560,result.get(0).getAccountid());
			
		}
		
		
		@Test
		public void getAllTransactionById_test(){
			List<Wallettransaction> transactionList = new ArrayList<Wallettransaction>();
			
			Wallettransaction wt=new Wallettransaction();
			wt.setAccountid(560);
			wt.setAccountbalance(5000);
			wt.setAmount(100);
			wt.setDescription("Deposited");
			LocalDate localdate=LocalDate.now();
			wt.setTransactionDate(localdate);
			wt.setTransactionID(27);
			transactionList.add(wt);
			
			when(wallettransactiondao.getAllTransactionById(wt.getAccountid())).thenReturn(transactionList);
			
			List<Wallettransaction> result = wallettransactionservice.getAllTransactionById(560);
			
			assertEquals("Deposited",result.get(0).getAccountid());
			
		}
		
		
	

}
