package com.comviva.cvs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.comviva.cvs.entity.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> findAllTransaction() {
		String jpql = "select t from Transaction t";
		Query query = em.createQuery(jpql, Transaction.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> findAllFailedTransaction() {
		String jpql = "from Transaction where transactionStatus in ('22','23','25','26')";
		Query query = em.createQuery(jpql, Transaction.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> findAllFailedTransactionsByRetailerMSISDN(Integer retailerMSISDN) {
		String jpql = "from Transaction where  retailer_msisdn = :retailerMSISDN";
		Query query = em.createQuery(jpql, Transaction.class);
		query.setParameter("retailerMSISDN", retailerMSISDN);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> findAllFailedTransactionsByDate(String date) {
		String jpql = "from Transaction where  transaction_date = :date";
		Query query = em.createQuery(jpql, Transaction.class);
		query.setParameter("transaction_date", date);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateDBStatusCode(Transaction transaction) {
//		String jpql = null;
//		if (transaction.getTransactionStatus() == "22" || transaction.getTransactionStatus() == "23")
//			jpql = "update Transaction set status_code = '29' where transaction_id = :transactionId";
//		else
//			jpql = "update Transaction set status_code = '30' where transaction_id = :transactionId";
//		Query query = em.createQuery(jpql);
//		String transactionId = transaction.getTransactionId();
//		query.setParameter("transaction_id", transactionId);
//		return query.executeUpdate();
		Transaction oldTxn = em.find(Transaction.class, transaction.getTransactionId());
		System.out.println(
				"****************************************************************************************************");
		System.out.println(oldTxn.getTransactionId());
		if (oldTxn.getTransactionStatus().compareTo("22") == 0 || oldTxn.getTransactionStatus().compareTo("23") == 0) {
			oldTxn.setTransactionStatus("29");
			System.out.println("Transaction status changed " + oldTxn.getTransactionStatus());
		} else if (oldTxn.getTransactionStatus().compareTo("25") == 0
				|| oldTxn.getTransactionStatus().compareTo("26") == 0) {
			oldTxn.setTransactionStatus("30");
			System.out.println("Transaction status changed " + oldTxn.getTransactionStatus());
		}

		em.merge(oldTxn);
	}

	@Override
	@Transactional
	public void updateDBFaceValue(Transaction transaction, int count) {
		Transaction oldTxn = em.find(Transaction.class, transaction.getTransactionId());
		System.out.println(
				"****************************************************************************************************");
		System.out.println(oldTxn.getTransactionId());

		oldTxn.setFaceValue(count + "");

		em.merge(oldTxn);

	}

}
