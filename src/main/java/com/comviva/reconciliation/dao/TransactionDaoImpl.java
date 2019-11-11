package com.comviva.reconciliation.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.comviva.reconciliation.entity.Transaction;

/**
 * @author Manjuanth
 *
 */
@Component
public class TransactionDaoImpl implements TransactionDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDaoImpl.class);

	@Override
	public List<Transaction> getRetailerTransactions(String retailerMsisdn) {
		LOGGER.info("Fetching all transactions of retailer with MSISDN "+ retailerMsisdn +"");
		Query query = entityManager.createQuery("from Transaction where retailerMsisdn=:retailerMsisdn");
		query.setParameter("retailerMsisdn", retailerMsisdn);
		return query.getResultList();
	}

	@Override
	public List<Transaction> getSubscriberTransactions(String subscriberMsisdn) {
		LOGGER.info("Fetching all transactions of retailer with MSISDN "+ subscriberMsisdn +"");
		Query query = entityManager.createQuery("from Transaction where subscriberMsisdn=:subscriberMsisdn");
		query.setParameter("subscriberMsisdn", subscriberMsisdn);
		return query.getResultList();
	}

	@Override
	public List<Transaction> getFailedTransactionsByStatus(String transactionStatus) {
		LOGGER.info("Fetching all failed transactions with transaction status "+ transactionStatus +"");
		Query query = entityManager.createQuery("from Transaction where transactionStatus=:transactionStatus");
		query.setParameter("transactionStatus", transactionStatus);
		return query.getResultList();
	}

	@Override
	public List<Transaction> getFailedTransactions() {
		LOGGER.info("Fetching all failed transactions");
		Query query = entityManager.createQuery("from Transaction where transactionStatus in ('22','23','25','26')");
		return query.getResultList();
	}

	@Override
	public List<Transaction> getAllTransactions() {
		LOGGER.info("Fetching all transactions");
		return entityManager.createQuery("from Transaction").getResultList();
	}

	@Override
	@Transactional
	public void updateTransaction(Transaction transaction) throws Exception {
		if(entityManager.find(Transaction.class, transaction.getTransactionId()) != null) {
			entityManager.merge(transaction);
			LOGGER.info("Transaction with transaction ID " + transaction.getTransactionId() + " is updated successfully");
		}
		else 
			{
				LOGGER.debug("Transaction with transaction ID " + transaction.getTransactionId() + " is not found in Database");
				throw new Exception("Transaction with transaction ID " + transaction.getTransactionId() + " is not found");
			}
	}

	@Override
	public List<Transaction> getTransactionsByDate(String fromDate, String toDate) {
		LOGGER.info("Fetching all transactions between " + fromDate + " and " + toDate + "");
		Query query = entityManager.createQuery("from Transaction where transactionDate between :fromDate and :toDate order by transactionDate");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.getResultList();
	}

}
