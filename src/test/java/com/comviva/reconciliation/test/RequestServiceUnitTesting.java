package com.comviva.reconciliation.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.comviva.reconciliation.entity.ReversalRequest;
import com.comviva.reconciliation.entity.ReversalResponse;
import com.comviva.reconciliation.entity.StatusCheckRequest;
import com.comviva.reconciliation.entity.StatusCheckResponse;
import com.comviva.reconciliation.entity.Transaction;
import com.comviva.reconciliation.entity.TransactionPrimary;
import com.comviva.reconciliation.service.RequestServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RequestServiceUnitTesting {

	@InjectMocks
	private RequestServiceImpl requestService;

	@Mock
	RestTemplate restTemplate;

	@Mock
	ResponseEntity<ReversalResponse> responseEntityMock;

	@Mock
	ResponseEntity<StatusCheckResponse> statusResponseMock;

	private StatusCheckRequest getStatusCheckRequest(Transaction transaction) {
		return new StatusCheckRequest("dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy",
				"dummy", "dummy", "dummy");
	}

	private ReversalRequest getReversalRequest(Transaction transaction) {
		return new ReversalRequest("dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy",
				"dummy", "dummy", "dummy", "dummy");
	}

	final static ReversalResponse successfulReversal = new ReversalResponse("RCREVRESP", "200", "24-10-2019 07:43:40",
			"1U381DDB370DABE", "500", "24-10-2019 07:43:40", "RCREV", "X191024.0743.250001",
			"Transaction ID X191024.0743.250001 for Prepaid Reversal request of "
					+ "658693943  for 500 is accepted for processing, your new balance is FCFA 27608.");
	final static ReversalResponse failedReversal = new ReversalResponse();

	final static StatusCheckResponse successStatus = new StatusCheckResponse("EXRCSTATRESP", "200",
			"01/08/2016 15:49:24", "01557919", "V160729.1550.110001", "200",
			"Last transfer status of transaction ID:V160729.1550.110001, transfer date "
					+ "time:29/07/16 15:50:46, MSISDN:6583666677, transfer status:SUCCESS, service type:Promo "
					+ "VAS Recharge, product:eTopUP, value:200");

	final static StatusCheckResponse failedStatus = new StatusCheckResponse("EXRCSTATRESP", "500",
			"01/08/2016 15:49:24", "01557919", "V160729.1550.110001", "200",
			"Last transfer status of transaction ID:V160729.1550.110001, transfer date "
					+ "time:29/07/16 15:50:46, MSISDN:6583666677, transfer status:FAILED, service type:Promo "
					+ "VAS Recharge, product:eTopUP, value:200");

	ResponseEntity<StatusCheckResponse> successStatusResponseEntity = new ResponseEntity<StatusCheckResponse>(
			successStatus, HttpStatus.valueOf(200));
	ResponseEntity<StatusCheckResponse> failureStatusResponseEntity = new ResponseEntity<StatusCheckResponse>(
			failedStatus, HttpStatus.valueOf(200));

	ResponseEntity<ReversalResponse> successResponseEntity = new ResponseEntity<ReversalResponse>(successfulReversal,
			HttpStatus.valueOf(200));
	ResponseEntity<ReversalResponse> failureResponseEntity = new ResponseEntity<ReversalResponse>(failedReversal,
			HttpStatus.valueOf(404));

	TransactionPrimary transactionPrimary = new TransactionPrimary();
	Transaction transaction = new Transaction();

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(responseEntityMock);
		MockitoAnnotations.initMocks(restTemplate);
		MockitoAnnotations.initMocks(statusResponseMock);

		LocalDate transactionDate = LocalDate.parse("2019-01-04");
		transactionPrimary.setTransactionId("789455");
		transactionPrimary.setRetailerMsisdn("789454");
		transactionPrimary.setTransactionDate(transactionDate);
		transaction.setFaceValue("2");
		transaction.setTransactionStatus(23);
		transaction.setPrimaryTransaction(transactionPrimary);
	}

	@Test
	public void sendReversalRequest_Failure() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8090/reversal", getReversalRequest(transaction),
				ReversalResponse.class)).thenReturn(failureResponseEntity);
		Mockito.when(responseEntityMock.getBody()).thenReturn(failedReversal);
		// when
		boolean actualResponse = requestService.sendReversalRequest(transaction);
		// then
		assertThat(actualResponse).isEqualTo(false);
	}

	@Test
	public void sendStatusCheckRequest_Success() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8090/transactionStatus",
				getStatusCheckRequest(transaction), StatusCheckResponse.class)).thenReturn(successStatusResponseEntity);
		Mockito.when(statusResponseMock.getBody()).thenReturn(successStatus);
		// when
		StatusCheckResponse actualResponse = requestService.sendStatusCheckRequest(transaction);
		// then
		assertThat(actualResponse).isEqualTo(successStatus);

	}

	@Test
	public void sendStatusCheckRequest_failure() {
		Mockito.when(restTemplate.postForEntity("http://localhost:8090/transactionStatus",
				getStatusCheckRequest(transaction), StatusCheckResponse.class)).thenReturn(failureStatusResponseEntity);
		Mockito.when(statusResponseMock.getBody()).thenReturn(failedStatus);
		// when
		StatusCheckResponse actualResponse = requestService.sendStatusCheckRequest(transaction);
		// then
		assertThat(actualResponse).isEqualTo(failedStatus);
	}

}
