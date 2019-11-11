package com.comviva.reconciliation.dao;

import java.util.List;

import com.comviva.reconciliation.entity.Transaction;

/**
 * @author Manjunath
 *
 */

public interface TransactionDao{
	
	List<Transaction> getRetailerTransactions(String retailerMsisdn);	
	List<Transaction> getSubscriberTransactions(String subscriberMsisdn);	
	List<Transaction> getFailedTransactionsByStatus(String transactionStatus);	
	List<Transaction> getFailedTransactions();	
	List<Transaction> getAllTransactions();
	void updateTransaction(Transaction transaction) throws Exception;
	List<Transaction> getTransactionsByDate(String fromDate, String toDate);

}
