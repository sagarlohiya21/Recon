package com.comviva.cvs.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.comviva.cvs.entity.Transaction;

public interface TransactionDao {

	/**
	 * Returns the failed transactions based on status code (No Arguments),
	 * retailedMSISDN and date
	 * 
	 */
	List<Transaction> findAllTransaction();
	
	List<Transaction> findAllFailedTransaction();

	List<Transaction> findAllFailedTransactionsByRetailerMSISDN(Integer retailerMSISDN);

	List<Transaction> findAllFailedTransactionsByDate(String date);


	@Transactional
	void updateDBStatusCode(Transaction transaction);
	
	@Transactional
	void updateDBFaceValue(Transaction transaction, int count);

}
