package com.comviva.reconciliation.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.comviva.reconciliation.dao.ITransactionDao;
import com.comviva.reconciliation.dao.TransactionDaoImpl;
import com.comviva.reconciliation.utils.Transaction;

/**
 * @author manjunath
 *
 */
public class TrasactionServiceImpl implements ITransactionService {

	private ITransactionDao transactionDao;
	
	public ITransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(ITransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	@Override
	public String getTransactionDate(Transaction transaction) {
		return transactionDao.getTransactionDate(transaction);
	}

	@Override
	public String getRetailerMsisdn(Transaction transaction) {
		return transactionDao.getRetailerMsisdn(transaction);
	}

	@Override
	public String getSubscriberMsisdn(Transaction transaction) {
		return transactionDao.getSubscriberMsisdn(transaction);
	}

	@Override
	public String getTransactionStatus(Transaction transaction) {
		return transactionDao.getTransactionStatus(transaction);
	}

	@Override
	public void processFailedTransaction(List<Transaction> failedTransactions) {
	
	}

	@Override
	public List<Transaction> getRetailerTransactions(String retailerMsisdn) {
		List<Transaction> transactions = new ArrayList<>();
		List<com.comviva.reconciliation.entity.Transaction> dbTransactions = getRetailerTransactionsFromDB(retailerMsisdn);
		for(com.comviva.reconciliation.entity.Transaction dbTransaction : dbTransactions) {
			transactions.add(mapTransactionFromDBTransaction(dbTransaction));
		}
		return transactions;
	}
	
	@Override
	public List<com.comviva.reconciliation.entity.Transaction> getRetailerTransactionsFromDB(String retailerMsisdn) {
		return transactionDao.getRetailerTransactionsFromDB(retailerMsisdn);
	}
	
	@Override
	public Transaction mapTransactionFromDBTransaction(com.comviva.reconciliation.entity.Transaction dbTransaction) {
		Transaction transaction = new Transaction();
		transaction.setCampaignName(dbTransaction.getCampaignName());
		transaction.setCeTimeTaken(dbTransaction.getCeTimeTaken());
		transaction.setDebugTransactionId(dbTransaction.getDebugTransactionId());
		transaction.setE2eTimeTaken(dbTransaction.getE2eTimeTaken());
		transaction.setFaceValue(dbTransaction.getFaceValue());
		transaction.setOfferCategory(dbTransaction.getOfferCategory());
		transaction.setOfferName(dbTransaction.getOfferName());
		transaction.setOfferType(dbTransaction.getOfferType());
		transaction.setPretupsTimeTaken(dbTransaction.getPretupsTimeTaken());
		transaction.setRechargeAmount(dbTransaction.getRechargeAmount());
		transaction.setRetailerCommission(dbTransaction.getRetailerCommission());
		transaction.setRetailerMsisdn(dbTransaction.getRetailerMsisdn());
		transaction.setSmsTimeTaken(dbTransaction.getSmsTimeTaken());
		transaction.setStartTime(dbTransaction.getStartTime());
		transaction.setSubscriberBonus(dbTransaction.getSubscriberBonus());
		transaction.setSubscriberMsisdn(dbTransaction.getSubscriberMsisdn());
		transaction.setTransactionDate(dbTransaction.getTransactionDate());
		transaction.setTransactionId(dbTransaction.getTransactionId());
		transaction.setTransactionStatus(dbTransaction.getTransactionStatus());
		transaction.setVasTimeTaken(dbTransaction.getVasTimeTaken());
		return transaction;
	}
	
	@Override
	public List<Transaction> getFailedTransactions() {
		System.out.println("getFailedTransactions()");
		List<Transaction> transactions = new ArrayList<>();
		List<com.comviva.reconciliation.entity.Transaction> dbTransactions = getFailedTransactionsFromDB();
		for(com.comviva.reconciliation.entity.Transaction dbTransaction : dbTransactions) {
			transactions.add(mapTransactionFromDBTransaction(dbTransaction));
		}
		return transactions;
	}

	@Override
	public List<com.comviva.reconciliation.entity.Transaction> getFailedTransactionsFromDB() {
		return transactionDao.getFailedTransactionsFromDB();
	}
	
	@Override
	public String display() {
		return "Service layer";
	}
	

}
