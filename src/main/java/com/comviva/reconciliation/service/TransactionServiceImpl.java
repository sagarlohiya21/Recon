package com.comviva.reconciliation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.comviva.reconciliation.dao.TransactionDao;
import com.comviva.reconciliation.entity.ReversalRequest;
import com.comviva.reconciliation.entity.ReversalResponse;
import com.comviva.reconciliation.entity.StatusCheckRequest;
import com.comviva.reconciliation.entity.StatusCheckResponse;
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
	private RestTemplate restTemplate;
	private static final Logger LOGGER=LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Override
	public List<Transaction> getRetailerTransactions(String retailerMsisdn) {
		LOGGER.info("Fetching all transactions of retailer with MSISDN "+ retailerMsisdn +"");
		return transactionDao.getRetailerTransactions(retailerMsisdn);
	}
	
	@Override
	public List<Transaction> getFailedTransactionsByStatus(String transactionStatus) {
		LOGGER.info("Fetching all failed transactions with transaction status "+ transactionStatus +"");
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
	public List<Transaction> getTransactionsByDate(String fromDate, String toDate) {
		LOGGER.info("Fetching transactions between " + fromDate + " and " + toDate + "");
		return transactionDao.getTransactionsByDate(fromDate, toDate);
	}
	
	@Override
	public void updateFaceValue(Transaction transaction, String faceValue) {
		transaction.setFaceValue(faceValue);
		LOGGER.info("Updating transaction face value");
		try {
			transactionDao.updateTransaction(transaction);
		} catch (Exception e) {
			LOGGER.error("Failed to update the Transaction Status");
			e.printStackTrace();
		}		
	}

	private void updateTransactionStatus(Transaction transaction) {
		if(transaction.getTransactionStatus().equals("22") || transaction.getTransactionStatus().equals("23") )
			transaction.setTransactionStatus("29");
		else if(transaction.getTransactionStatus().equals("25") || transaction.getTransactionStatus().equals("26") )
			transaction.setTransactionStatus("30");
		LOGGER.info("Updating transaction status");
		try {
			transactionDao.updateTransaction(transaction);
		} catch (Exception e) {
			LOGGER.error("Failed to update the Transaction Status");
			e.printStackTrace();
		}		
	}
	
	@Override
	public void updateTransactionStatus(Transaction transaction, String statusCode) {
		transaction.setTransactionStatus(statusCode);
		LOGGER.info("Updating transaction Status ");
		try {
			transactionDao.updateTransaction(transaction);
		} catch (Exception e) {
			LOGGER.error("Failed to update the Transaction Status");
			e.printStackTrace();
		}		
	}
	
	/**
	 * Processes all failed transactions :
	 * The money will be transferred back to the retailer
	 * if money is debited in retailer account but the package 
	 * is not activated for subscriber  
	 */
	@Override
	public void processFailedTransaction() {
		List<Transaction> failedTransactions = getFailedTransactions();												//Fetching all failed transactions
		try {
			// iterating through all the transactions																									
			for (Transaction failedTransaction: failedTransactions) { 
				LOGGER.info("processing failed transaction :" + failedTransaction.getTransactionId());
				if(sendStatusCheckRequest(failedTransaction).getTxnStatus().equals("200")) {						//calling status check API for each failed transaction
				LOGGER.info("Money is debited for transaction :" + failedTransaction.getTransactionId());
				int attempts = 0 ;
				boolean flag = false;
				do{
					++attempts;
					if(sendReversalRequest(failedTransaction)) {													// calling Reversal API to transfer back the money to retailer 
						LOGGER.info("Money is transfered back to retailer account through Reversal API");
						flag = true;
						updateTransactionStatus(failedTransaction);
						updateFaceValue(failedTransaction, ""+attempts);
						break;
					}
				}while(attempts<=7);
				if(!flag)
					updateTransactionStatus(failedTransaction,"");
					
				}
				else {
					LOGGER.info("Money is not debited for transaction :" + failedTransaction.getTransactionId());
					updateTransactionStatus(failedTransaction);
				}
			}
		} catch (Exception e) {
			LOGGER.error(" Error occured while processing failed transactions ");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Calls the external Status check API for getting 
	 * transaction status for the given transaction
	 * @param transaction
	 * @return StatusCheckResponse object
	 * 
	 */
	private StatusCheckResponse sendStatusCheckRequest(Transaction transaction) {
		LOGGER.info("calling Status Check API for transaction :" + transaction.getTransactionId());
		ResponseEntity<StatusCheckResponse> responseEntity = restTemplate.postForEntity("http://localhost:8090/successStatus", getStatusCheckRequest(transaction), StatusCheckResponse.class);
		StatusCheckResponse StatusCheckResponse = responseEntity.getBody();
		return StatusCheckResponse;
	}

	/**
	 * Calls the external Reversal API for transferring 
	 * money back to the retailer account  
	 * @param transaction
	 * @return boolean
	 * 
	 */
	private boolean sendReversalRequest(Transaction transaction) {
		LOGGER.info("Calling Reversal API");
		ResponseEntity<ReversalResponse> responseEntity = restTemplate.postForEntity("http://localhost:8090/reversal", getReversalRequest(transaction), ReversalResponse.class);
		ReversalResponse reversalResponse = responseEntity.getBody();
			if (reversalResponse != null) {
				System.out.println(reversalResponse.getTxnStatus());
				return reversalResponse.getTxnStatus().equals("200");
				
			}
			else {
				LOGGER.info("Reversal API didn't respond");
				return false;
			}
		
	}
	
	/**
	 * Maps given transaction to StatusCheckRequest
	 * @param transaction
	 * @return statusCheckRequest object
	 */
	private StatusCheckRequest getStatusCheckRequest(Transaction transaction) {
		LOGGER.info("Constructing statusCheck request for transaction :" + transaction.getTransactionId());
		return new StatusCheckRequest("dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy");
	}

	/**
	 * Maps given transaction to ReversalRequest
	 * @param transaction
	 * @return ReversalRequest object
	 *
	 */
	private ReversalRequest getReversalRequest(Transaction transaction) {
		LOGGER.info("Constructing Reversal request for transaction :" + transaction.getTransactionId());
		return new ReversalRequest("dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy");
	}
}
