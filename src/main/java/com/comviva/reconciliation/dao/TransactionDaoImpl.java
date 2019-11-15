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
	public List<Transaction> getFailedTransactions() {
		LOGGER.info("Fetching all failed transactions");
		Query query = entityManager.createQuery("from Transaction where transactionStatus in ('22','23','25','26')");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateTransaction(Transaction transaction) throws Exception {
		if (entityManager.find(Transaction.class, transaction.getPrimaryTransaction().getTransactionId()) != null) {
			entityManager.merge(transaction);
			LOGGER.info("Transaction with transaction ID " + transaction.getPrimaryTransaction().getTransactionId()
					+ " is updated successfully");
		} else {
			LOGGER.debug("Transaction with transaction ID " + transaction.getPrimaryTransaction().getTransactionId()
					+ " is not found in Database");
			throw new Exception("Transaction with transaction ID "
					+ transaction.getPrimaryTransaction().getTransactionId() + " is not found");
		}
	}

}
