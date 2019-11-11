package com.comviva.reconciliation.service;

import java.util.List;

import com.comviva.reconciliation.entity.Transaction;

/**
 * @author Sagar Lohiya
 *
 */
public interface TransactionService {
	
	List<Transaction> getFailedTransactionsByStatus(String transactionStatus);
	List<Transaction> getFailedTransactions();
	List<Transaction> getRetailerTransactions(String retailerMsisdn);
	List<Transaction> getAllTransactions();
	List<Transaction> getTransactionsByDate(String fromDate, String toDate);
	void updateTransactionStatus(Transaction transaction,String statusCode);
	void updateFaceValue(Transaction transaction, String faceValue);
	void processFailedTransaction();

}
