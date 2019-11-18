package com.comviva.reconciliation.dao;

import java.util.ArrayList;
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
		List<Transaction> transactionList = new ArrayList<Transaction>();
		LOGGER.info("Fetching all failed transactions");
		Query query = entityManager.createQuery("from Transaction where transactionStatus in ('22','23','25','26')");
		try {
			transactionList = query.getResultList();
		} catch (Exception e) {
			LOGGER.error("Failed to get ammbiguous tranasactions");
		}
		return transactionList;
	}

	@Override
	@Transactional
	public void updateTransaction(Transaction transaction) {
		try {
			if (entityManager.find(Transaction.class, transaction.getPrimaryTransaction()) != null) {
				entityManager.merge(transaction);
				LOGGER.info("Transaction with transaction ID updated");
			} else {
				LOGGER.error("transaction not found in the database");
			}

		} catch (Exception e) {
			LOGGER.info("The changes in the transaction could not be saved");
		}
	}

}
