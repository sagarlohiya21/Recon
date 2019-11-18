package com.comviva.reconciliation.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.comviva.reconciliation.dao.TransactionDao;
import com.comviva.reconciliation.entity.ReversalResponse;
import com.comviva.reconciliation.entity.StatusCheckResponse;
import com.comviva.reconciliation.entity.Transaction;
import com.comviva.reconciliation.entity.TransactionPrimary;
import com.comviva.reconciliation.service.RequestService;
import com.comviva.reconciliation.service.TransactionServiceImpl;;

@RunWith(MockitoJUnitRunner.class)
public class TransactonServiceUnitTest {

	@InjectMocks
	private TransactionServiceImpl transactionService;

	@Mock
	private TransactionDao transactionDao;

	@Mock
	private RequestService requestService;

	TransactionPrimary transactionPrimary = new TransactionPrimary();
	Transaction transaction1 = new Transaction();
	Transaction transaction2 = new Transaction();
	Transaction transaction3 = new Transaction();
	Transaction transaction4 = new Transaction();
	Transaction transaction5 = new Transaction();
	Transaction transaction6 = new Transaction();
	Transaction transaction7 = new Transaction();
	Transaction transaction8 = new Transaction();
	List<Transaction> failedTransactions = new ArrayList<>();
	List<Transaction> successfulTransactions = new ArrayList<>();

	final static StatusCheckResponse failedStatus = new StatusCheckResponse("EXRCSTATRESP", "500",
			"01/08/2016 15:49:24", "01557919", "V160729.1550.110001", "200",
			"Last transfer status of transaction ID:V160729.1550.110001, transfer date "
					+ "time:29/07/16 15:50:46, MSISDN:6583666677, transfer status:FAILED, service type:Promo "
					+ "VAS Recharge, product:eTopUP, value:200");

	final static StatusCheckResponse successStatus = new StatusCheckResponse("EXRCSTATRESP", "200",
			"01/08/2016 15:49:24", "01557919", "V160729.1550.110001", "200",
			"Last transfer status of transaction ID:V160729.1550.110001, transfer date "
					+ "time:29/07/16 15:50:46, MSISDN:6583666677, transfer status:SUCCESS, service type:Promo "
					+ "VAS Recharge, product:eTopUP, value:200");

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(requestService);
		MockitoAnnotations.initMocks(transactionDao);

		LocalDate transactionDate = LocalDate.parse("2019-01-04");
		transactionPrimary.setTransactionId("789455");
		transactionPrimary.setRetailerMsisdn("789454");
		transactionPrimary.setTransactionDate(transactionDate);
		transaction1.setFaceValue("2");
		transaction1.setTransactionStatus(22);
		transaction1.setPrimaryTransaction(transactionPrimary);

		transactionPrimary.setTransactionId("789454");
		transactionPrimary.setRetailerMsisdn("789453");
		transactionPrimary.setTransactionDate(transactionDate);
		transaction2.setFaceValue("2");
		transaction2.setTransactionStatus(23);
		transaction2.setPrimaryTransaction(transactionPrimary);

		transactionPrimary.setTransactionId("789453");
		transactionPrimary.setRetailerMsisdn("789452");
		transactionPrimary.setTransactionDate(transactionDate);
		transaction3.setFaceValue("2");
		transaction3.setTransactionStatus(25);
		transaction3.setPrimaryTransaction(transactionPrimary);

		transactionPrimary.setTransactionId("789452");
		transactionPrimary.setRetailerMsisdn("789451");
		transactionPrimary.setTransactionDate(transactionDate);
		transaction4.setFaceValue("2");
		transaction4.setTransactionStatus(26);
		transaction4.setPrimaryTransaction(transactionPrimary);

		transactionPrimary.setTransactionId("789451");
		transactionPrimary.setRetailerMsisdn("789450");
		transactionPrimary.setTransactionDate(transactionDate);
		transaction5.setFaceValue("2");
		transaction5.setTransactionStatus(29);
		transaction5.setPrimaryTransaction(transactionPrimary);

		transactionPrimary.setTransactionId("789451");
		transactionPrimary.setRetailerMsisdn("789450");
		transactionPrimary.setTransactionDate(transactionDate);
		transaction6.setFaceValue("2");
		transaction6.setTransactionStatus(30);
		transaction6.setPrimaryTransaction(transactionPrimary);

		transactionPrimary.setTransactionId("789455");
		transactionPrimary.setRetailerMsisdn("789454");
		transactionPrimary.setTransactionDate(transactionDate);
		transaction7.setFaceValue("2");
		transaction7.setTransactionStatus(21);
		transaction7.setPrimaryTransaction(transactionPrimary);

		transactionPrimary.setTransactionId("789454");
		transactionPrimary.setRetailerMsisdn("789453");
		transactionPrimary.setTransactionDate(transactionDate);
		transaction8.setFaceValue("2");
		transaction8.setTransactionStatus(20);
		transaction8.setPrimaryTransaction(transactionPrimary);

		failedTransactions.add(transaction1);
		failedTransactions.add(transaction2);
		failedTransactions.add(transaction3);
		failedTransactions.add(transaction4);

		successfulTransactions.add(transaction5);
		successfulTransactions.add(transaction6);
		successfulTransactions.add(transaction8);
		successfulTransactions.add(transaction7);
	}

	@Test
	public void getFailedTransaction_thenReturnAllFailedTransaction() {

		Mockito.when(transactionDao.getFailedTransactions()).thenReturn(failedTransactions);
		// when
		List<Transaction> actualTransactions = transactionService.getFailedTransactions();
		// then
		assertThat(actualTransactions).isEqualTo(failedTransactions);
	}

	@Test
	public void processFailedTransaction_getFailedTransaction_Success_Test() {
		Mockito.when(transactionDao.getFailedTransactions()).thenReturn(failedTransactions);
		// when
		List<Transaction> actualTransactions = transactionService.getFailedTransactions();
		// then
		assertThat(actualTransactions).isEqualTo(failedTransactions);
	}

	@Test
	public void getFailedTransaction_ReturnNull() {

		Mockito.when(transactionDao.getFailedTransactions()).thenReturn(null);
		// when
		assertThrows(NullPointerException.class, () -> {
			List<Transaction> actualTransactions = transactionService.getFailedTransactions();
		});
		;
	}

	@Test
	public void processFailedTransaction_getFailedTransaction_Failure_Test() {
		Mockito.when(transactionDao.getFailedTransactions()).thenReturn(failedTransactions);
		// when
		List<Transaction> actualTransactions = transactionService.getFailedTransactions();
		// then
		assertThat(actualTransactions).isNotEqualTo(successfulTransactions);
	}

	@Test
	public void processFailedTransaction_SendStatusCheck_Success_Reversal_Success_Test() {
		Mockito.when(transactionDao.getFailedTransactions()).thenReturn(failedTransactions);
		Mockito.when(requestService.sendStatusCheckRequest(ArgumentMatchers.any(Transaction.class)))
				.thenReturn(successStatus);
		Mockito.when(requestService.sendReversalRequest(ArgumentMatchers.any(Transaction.class))).thenReturn(true);

		transactionService.processFailedTransaction();

		for (Transaction trans : failedTransactions) {
			verify(requestService, times(2)).sendStatusCheckRequest(trans);
			verify(requestService, times(2)).sendReversalRequest(trans);
			verify(transactionDao, times(2)).updateTransaction(trans);
		}
	}

}
