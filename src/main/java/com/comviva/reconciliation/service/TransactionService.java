package com.comviva.reconciliation.service;

import java.util.List;

import com.comviva.reconciliation.entity.Transaction;

/**
 * @author Sagar Lohiya
 *
 */
public interface TransactionService {

	List<Transaction> getFailedTransactions();

	void updateTransactionStatus(Transaction transaction);

	void processFailedTransaction();

	void updateTransaction(Transaction transaction, String faceValue, int statusCode);

}
