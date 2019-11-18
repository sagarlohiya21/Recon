package com.comviva.reconciliation.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.comviva.reconciliation.dao.TransactionDaoImpl;
import com.comviva.reconciliation.entity.Transaction;
import com.comviva.reconciliation.entity.TransactionPrimary;

@RunWith(MockitoJUnitRunner.class)
public class TransactionDaoTest {
	
	@InjectMocks
	private TransactionDaoImpl transactionDao;
	
	@Mock
	private EntityManager entityManagerMock;
	
	@Mock
	private Query query;
	
	TransactionPrimary transactionPrimary = new TransactionPrimary();
	Transaction transaction = new Transaction();
	Transaction transaction1 = new Transaction();
	Transaction transaction2 = new Transaction();
	Transaction transaction3 = new Transaction();
	Transaction transaction4 = new Transaction();
	Transaction transaction5 = new Transaction();
	Transaction transaction6 = new Transaction();
	Transaction transaction7 = new Transaction();
	Transaction transaction8 = new Transaction();
	
	List<Transaction> failedTransactions = new ArrayList<>();
	List<Transaction> successfulTransactions = new ArrayList<>();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(entityManagerMock);
		MockitoAnnotations.initMocks(query);
	
		LocalDate transactionDate =  LocalDate.parse( "2019-01-04" );
		transactionPrimary.setTransactionId("789455");
		transactionPrimary.setRetailerMsisdn("789454");
		transactionPrimary.setTransactionDate(transactionDate);		
		transaction.setFaceValue("2");
		transaction.setTransactionStatus(22);
		transaction.setPrimaryTransaction(transactionPrimary);
		
		transactionPrimary.setTransactionId("789455");
		transactionPrimary.setRetailerMsisdn("789454");
		transactionPrimary.setTransactionDate(transactionDate);		
		transaction1.setFaceValue("2");
		transaction1.setTransactionStatus(22);
		transaction1.setPrimaryTransaction(transactionPrimary);
		
		transactionPrimary.setTransactionId("789454");
		transactionPrimary.setRetailerMsisdn("789453");
		transactionPrimary.setTransactionDate(transactionDate);		
		transaction2.setFaceValue("2");
		transaction2.setTransactionStatus(23);
		transaction2.setPrimaryTransaction(transactionPrimary);
		
		transactionPrimary.setTransactionId("789453");
		transactionPrimary.setRetailerMsisdn("789452");
		transactionPrimary.setTransactionDate(transactionDate);		
		transaction3.setFaceValue("2");
		transaction3.setTransactionStatus(25);
		transaction3.setPrimaryTransaction(transactionPrimary);
		
		transactionPrimary.setTransactionId("789452");
		transactionPrimary.setRetailerMsisdn("789451");
		transactionPrimary.setTransactionDate(transactionDate);		
		transaction4.setFaceValue("2");
		transaction4.setTransactionStatus(26);
		transaction4.setPrimaryTransaction(transactionPrimary);
		
		transactionPrimary.setTransactionId("789451");
		transactionPrimary.setRetailerMsisdn("789450");
		transactionPrimary.setTransactionDate(transactionDate);		
		transaction5.setFaceValue("2");
		transaction5.setTransactionStatus(20);
		transaction5.setPrimaryTransaction(transactionPrimary);
		
		transactionPrimary.setTransactionId("789451");
		transactionPrimary.setRetailerMsisdn("789450");
		transactionPrimary.setTransactionDate(transactionDate);		
		transaction6.setFaceValue("2");
		transaction6.setTransactionStatus(21);
		transaction6.setPrimaryTransaction(transactionPrimary);
		
		transactionPrimary.setTransactionId("789455");
		transactionPrimary.setRetailerMsisdn("789454");
		transactionPrimary.setTransactionDate(transactionDate);		
		transaction7.setFaceValue("2");
		transaction7.setTransactionStatus(30);
		transaction7.setPrimaryTransaction(transactionPrimary);
		
		transactionPrimary.setTransactionId("789454");
		transactionPrimary.setRetailerMsisdn("789453");
		transactionPrimary.setTransactionDate(transactionDate);		
		transaction8.setFaceValue("2");
		transaction8.setTransactionStatus(29);
		transaction8.setPrimaryTransaction(transactionPrimary);
		
		failedTransactions.add(transaction1);
		failedTransactions.add(transaction2);
		failedTransactions.add(transaction3);
		failedTransactions.add(transaction4);
		
		successfulTransactions.add(transaction5);
		successfulTransactions.add(transaction6);
		successfulTransactions.add(transaction8);
		successfulTransactions.add(transaction7);
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void getFailedTransaction_IsEqual_ToExpected_Transactions() throws Exception {
		Mockito.when(entityManagerMock.createQuery("from Transaction where transactionStatus in ('22','23','25','26')")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(failedTransactions);
		// when
        List<Transaction> actualTransactions = transactionDao.getFailedTransactions();
        // then
        assertThat(actualTransactions).isEqualTo(failedTransactions);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void getAllFailedTransaction_IsNotEqualTo_Successfull_Transaction() throws Exception {
		Mockito.when(entityManagerMock.createQuery("from Transaction where transactionStatus in ('22','23','25','26')")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(failedTransactions);
		// when
        List<Transaction> actualTransactions = transactionDao.getFailedTransactions();
        verify(entityManagerMock, times(1)).createQuery(ArgumentMatchers.anyString());
        verify(query,times(1)).getResultList();
        // then
        assertThat(actualTransactions).isNotEqualTo(successfulTransactions);
        }
	
	@Test
	@Transactional
	@Rollback(true)
	public void getAllFailedTransaction_Verify_MethodCall() throws Exception {
		Mockito.when(entityManagerMock.createQuery("from Transaction where transactionStatus in ('22','23','25','26')")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(failedTransactions);
		// when
        List<Transaction> actualTransactions = transactionDao.getFailedTransactions();
        // Verifying method calls of entity Manager and query class 
        verify(entityManagerMock, times(1)).createQuery(ArgumentMatchers.anyString());
        verify(query,times(1)).getResultList();
        assertThat(actualTransactions).isEqualTo(failedTransactions);
       	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void updateTransaction_Success_Test(){
		Mockito.when(entityManagerMock.find(Transaction.class, transaction.getPrimaryTransaction())).thenReturn(transaction);
		Mockito.when(entityManagerMock.merge(transaction)).thenReturn(transaction);
			transactionDao.updateTransaction(transaction);
			verify(entityManagerMock, times(1)).find(Transaction.class, transaction.getPrimaryTransaction());
			verify(entityManagerMock, times(1)).merge(transaction);

       	}

	@Test
	@Transactional
	@Rollback(true)
	public void updateTransaction_Failed_To_Find_Test(){
		Mockito.when(entityManagerMock.find(Transaction.class, transaction.getPrimaryTransaction())).thenReturn(null);
		
		transactionDao.updateTransaction(transaction);
		verify(entityManagerMock, times(1)).find(Transaction.class, transaction.getPrimaryTransaction());
		verify(entityManagerMock, never()).merge(transaction);

       	}
}
