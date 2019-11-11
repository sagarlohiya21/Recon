package com.comviva.reconciliation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comviva.reconciliation.service.RequestService;
import com.comviva.reconciliation.service.TransactionServiceImpl;

@RestController
public class TransactionController {

	@Autowired
	TransactionServiceImpl ts;

	@Autowired
	RequestService rs;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/getAllFailedTransactions")
	public void getTransaction() throws Exception {
		ts.processFailedTransaction();
	}

	/*@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/getAllTransactions")
	public List<Transaction> getAllTransactions() throws Exception {

		return ts.getAllTransactions();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAllFailedTransactionsByRetailerMSISDN")
	public List<Transaction> getTransaction(Integer retailerMSISDN) {
		return ts.getFailedTransactions(retailerMSISDN);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAllFailedTransactionsByDate")
	public List<Transaction> getTransaction(String date) {
		return ts.getFailedTransactions(date);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public void testWorking() throws Exception {
		List<Transaction> transactions = ts.getAllTransactions();
		Transaction t = transactions.get(0);
	}
*/
}
