package com.comviva.reconciliation.dao;

import java.util.List;

import com.comviva.reconciliation.entity.Transaction;

/**
 * @author Manjunath
 *
 */

public interface TransactionDao {

	List<Transaction> getFailedTransactions();

	void updateTransaction(Transaction transaction) throws Exception;
}
