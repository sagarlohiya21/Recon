package com.comviva.reconciliation.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comviva.reconciliation.dao.TransactionDao;
import com.comviva.reconciliation.entity.Transaction;

/**
 * @author Hithesh A Bandodkar, Sagar Lohiya
 * 
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;

	@Autowired
	private RequestService requestService;

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Override
	public List<Transaction> getRetailerTransactions(String retailerMsisdn) {
		LOGGER.info("Fetching all transactions of retailer with MSISDN " + retailerMsisdn + "");
		return transactionDao.getRetailerTransactions(retailerMsisdn);
	}

	@Override
	public List<Transaction> getFailedTransactionsByStatus(String transactionStatus) {
		LOGGER.info("Fetching all failed transactions with transaction status " + transactionStatus + "");
		return transactionDao.getFailedTransactionsByStatus(transactionStatus);
	}

	@Override
	public List<Transaction> getFailedTransactions() {
		LOGGER.info("Fetching all failed transactions");
		return transactionDao.getFailedTransactions();
	}

	@Override
	public List<Transaction> getAllTransactions() {
		LOGGER.info("Fetching all transactions");
		return transactionDao.getAllTransactions();
	}

	@Override
	@Transactional
	public List<Transaction> getTransactionsByDate(String fromDate, String toDate) {
		LOGGER.info("Fetching transactions between " + fromDate + " and " + toDate + "");
		return transactionDao.getTransactionsByDate(fromDate, toDate);
	}

	@Override
	@Transactional
	public void updateTransaction(Transaction transaction, String faceValue, String statusCode) {
		transaction.setTransactionStatus(statusCode);
		transaction.setFaceValue(faceValue);
		LOGGER.info("Updating transaction status and faceValue");
		try {
			transactionDao.updateTransaction(transaction);
		} catch (Exception e) {
			LOGGER.error("Failed to update the Transaction");
			e.printStackTrace();
		}

	}

	@Transactional
	private void updateTransaction(Transaction transaction, String faceValue) {
		if (transaction.getTransactionStatus() == 22 || transaction.getTransactionStatus() == 23)
			transaction.setTransactionStatus(29);
		else if (transaction.getTransactionStatus() == 25 || transaction.getTransactionStatus() == 26)
			transaction.setTransactionStatus(30);
		transaction.setFaceValue(faceValue);
		LOGGER.info("Updating transaction status and faceValue");
		try {
			transactionDao.updateTransaction(transaction);
		} catch (Exception e) {
			LOGGER.error("Failed to update the Transaction ");
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void updateTransactionStatus(Transaction transaction) {
		if (transaction.getTransactionStatus().equals("22") || transaction.getTransactionStatus().equals("23"))
			transaction.setTransactionStatus("29");
		else if (transaction.getTransactionStatus().equals("25") || transaction.getTransactionStatus().equals("26"))
			transaction.setTransactionStatus("30");
		LOGGER.info("Updating transaction Status ");
		try {
			transactionDao.updateTransaction(transaction);
		} catch (Exception e) {
			LOGGER.error("Failed to update the Transaction Status");
			e.printStackTrace();
		}
	}

	/**
	 * Processes all failed transactions : The money will be transferred back to the
	 * retailer if money is debited in retailer account but the package is not
	 * activated for subscriber
	 */
	@Override
	public void processFailedTransaction() {
		List<Transaction> failedTransactions = getFailedTransactions(); // Fetching all failed transactions
		try {
			// iterating through all the transactions
			for (Transaction failedTransaction : failedTransactions) {
				LOGGER.info("processing failed transaction :" + failedTransaction.getTransactionId());
				if (requestService.sendStatusCheckRequest(failedTransaction).getTxnStatus().equals("200")) { // calling
																												// status
																												// check
																												// API
																												// for
																												// each
																												// failed
																												// transaction
					LOGGER.info("Money is debited for transaction :" + failedTransaction.getTransactionId());
					int attempts = 0;
					boolean flag;
					do {
						flag = false;
						++attempts;
						if (requestService.sendReversalRequest(failedTransaction)) { // calling Reversal API to transfer
																						// back the money to retailer
							LOGGER.info("Money is transfered back to retailer account through Reversal API");
							flag = true;
							updateTransaction(failedTransaction, "" + attempts + "");
							break;
						}
					} while (attempts < 7);
					if (!flag) {
						System.out.println("maximum reached");
						updateTransaction(failedTransaction, "" + attempts + "", "21");
					}
				} else {
					LOGGER.info("Money is not debited for transaction :" + failedTransaction.getTransactionId());
					updateTransactionStatus(failedTransaction);
				}
			}
		} catch (Exception e) {
			LOGGER.error(" Error occured while processing failed transactions ");
			e.printStackTrace();
		}
	}

}
