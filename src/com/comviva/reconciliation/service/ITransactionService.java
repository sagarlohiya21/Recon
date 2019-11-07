package com.comviva.reconciliation.service;

import java.util.List;

import com.comviva.reconciliation.utils.Transaction;

/**
 * @author manjunath
 *
 */
public interface ITransactionService {
	
	String getTransactionDate(Transaction transaction);
	String getRetailerMsisdn(Transaction transaction);
	String getSubscriberMsisdn(Transaction transaction);
	String getTransactionStatus(Transaction transaction);
	List<Transaction> getFailedTransactions();
	void processFailedTransaction(List<Transaction> failedTransactions);
	List<Transaction> getRetailerTransactions(String retailerMsisdn);
	List<com.comviva.reconciliation.entity.Transaction> getRetailerTransactionsFromDB(String retailerMsisdn);
	Transaction mapTransactionFromDBTransaction(com.comviva.reconciliation.entity.Transaction dbTransaction);
	List<com.comviva.reconciliation.entity.Transaction> getFailedTransactionsFromDB();
	String display();
	//List<com.comviva.reconciliation.entity.Transaction> getFailedTransactions();

}
