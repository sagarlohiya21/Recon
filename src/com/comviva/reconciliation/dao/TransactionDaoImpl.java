package com.comviva.reconciliation.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.comviva.reconciliation.utils.Transaction;

/**
 * @author manjunath
 *
 */

@Transactional
public class TransactionDaoImpl implements ITransactionDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public String getTransactionId(Transaction transaction) {
		return transaction.getRechargeAmount();
	}

	@Override
	public String getRechargeAmount(Transaction transaction) {
		return transaction.getRechargeAmount();
	}

	@Override
	public String getTransactionDate(Transaction transaction) {
		return transaction.getTransactionDate();
	}

	@Override
	public String getRetailerMsisdn(Transaction transaction) {
		return transaction.getRetailerMsisdn();
	}

	@Override
	public String getSubscriberMsisdn(Transaction transaction) {
		return transaction.getSubscriberMsisdn();
	}

	@Override
	public String getTransactionStatus(Transaction transaction) {
		return transaction.getTransactionStatus();
	}


	@Override
	public List<com.comviva.reconciliation.entity.Transaction> getFailedTransactionsFromDB() {
		System.out.println("getFailedTransactionsFromDB()");
		String jpql = "from recharge_table where transactionStatus in ('22','23','25','26')";
		Query query = entityManager.createQuery(jpql);
		System.out.println(query.getResultList());
		return query.getResultList();
	}

	@Override
	public List<com.comviva.reconciliation.entity.Transaction> getRetailerTransactionsFromDB(String retailerMsisdn) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	/*@Override
	public List<com.comviva.reconciliation.entity.Transaction> getFailedTransactionsFromDB() {
		System.out.println("getFailedTransactionsFromDB()");
		String jpql = "select TRANSACTION_DATE from recharge_table";
		System.out.println("jpql");
		Query query = entityManager.createQuery(jpql);
		System.out.println("create Query");
		System.out.println(query.getResultList());
		
		return query.getResultList();
	}*/
	
}
