package com.comviva.reconciliation.dao;

import java.util.List;

import com.comviva.reconciliation.utils.Transaction;

/**
 * @author manjunath
 *
 */
public interface ITransactionDao {
	
	String getTransactionId(Transaction transaction);
	String getTransactionDate(Transaction transaction);
	String getTransactionStatus(Transaction transaction);
	String getRetailerMsisdn(Transaction transaction);
	String getSubscriberMsisdn(Transaction transaction);
	String getRechargeAmount(Transaction transaction);
	List<com.comviva.reconciliation.entity.Transaction> getFailedTransactionsFromDB();
	List<com.comviva.reconciliation.entity.Transaction> getRetailerTransactionsFromDB(String retailerMsisdn);
	
}
