package com.comviva.reconciliation.service;

import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.comviva.reconciliation.entity.ReversalRequest;
import com.comviva.reconciliation.entity.ReversalResponse;
import com.comviva.reconciliation.entity.StatusCheckRequest;
import com.comviva.reconciliation.entity.StatusCheckResponse;
import com.comviva.reconciliation.entity.Transaction;

@Service
public class RequestServiceImpl implements RequestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	String value = "dummy";

	@Autowired
	RestTemplate restTemplate;

	/**
	 * Pseudo created Status check and reversal requests for the program for testing
	 * purposes To be created with actual parameters after clarification
	 */

	/**
	 * Maps given transaction to StatusCheckRequest
	 * 
	 * @param transaction
	 * @return statusCheckRequest object
	 */
	private StatusCheckRequest getStatusCheckRequest(Transaction transaction) {
		LOGGER.info("Constructing statusCheck request for transaction :"
				+ transaction.getPrimaryTransaction().getTransactionId());

		return new StatusCheckRequest(value, value, value, value, value, value, value, value, value, value, value,
				value);
	}

	/**
	 * Maps given transaction to ReversalRequest
	 * 
	 * @param transaction
	 * @return ReversalRequest object
	 *
	 */
	private ReversalRequest getReversalRequest(Transaction transaction) {
		LOGGER.info("Constructing Reversal request for transaction :"
				+ transaction.getPrimaryTransaction().getTransactionId());
		return new ReversalRequest(value, value, value, value, value, value, value, value, value, value, value, value,
				value);
	}

	/**
	 * Calls the external Status check API for getting transaction status for the
	 * given transaction
	 * 
	 * @param transaction
	 * @return StatusCheckResponse object
	 * 
	 */
	@Override
	public StatusCheckResponse sendStatusCheckRequest(Transaction transaction) {
		LOGGER.info(
				"calling Status Check API for transaction :" + transaction.getPrimaryTransaction().getTransactionId());
		ResponseEntity<StatusCheckResponse> responseEntity = restTemplate.postForEntity(
				"http://localhost:8090/transactionStatus", getStatusCheckRequest(transaction),
				StatusCheckResponse.class);
		return responseEntity.getBody();
	}

	/**
	 * Calls the external Reversal API for transferring money back to the retailer
	 * account
	 * 
	 * @param transaction
	 * @return boolean
	 * 
	 */
	@Override
	public boolean sendReversalRequest(Transaction transaction) throws SocketTimeoutException {
		LOGGER.info("Calling Reversal API");
		ResponseEntity<ReversalResponse> responseEntity;
		boolean result = false;

		try {
			responseEntity = restTemplate.postForEntity("http://localhost:8090/reversal",
					getReversalRequest(transaction), ReversalResponse.class);
			ReversalResponse reversalResponse = responseEntity.getBody();
			if (reversalResponse != null) {
				result = reversalResponse.getTxnStatus().equals("200");

			} else {
				LOGGER.info("Reversal API didn't respond");
			}
		} catch (Exception e) {
			LOGGER.info("Request Timed Out");
		}

		return result;

	}

}
