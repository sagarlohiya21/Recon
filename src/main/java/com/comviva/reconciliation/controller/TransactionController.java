package com.comviva.reconciliation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comviva.reconciliation.entity.Transaction;
import com.comviva.reconciliation.service.RequestServiceImpl;
import com.comviva.reconciliation.service.TransactionServiceImpl;

@RestController
public class TransactionController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	TransactionServiceImpl ts;

	@Autowired
	RequestServiceImpl rs;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/getAllFailedTransactions")
	public List<Transaction> getTransaction() throws Exception {
		 return ts.getFailedTransactions();
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/getAllTransactions")
	public List<Transaction> getAllTransactions() throws Exception {
		return ts.getAllTransactions();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAllFailedTransactionsByRetailerMSISDN")
	public List<Transaction> getTransaction(Integer retailerMSISDN) {
		return ts.getRetailerTransactions(""+retailerMSISDN);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAllFailedTransactionsByDate")
	public List<Transaction> getTransaction(String date) {
		return ts.getTransactionsByDate("", date);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public void testWorking() throws Exception {
		/*List<Transaction> transactions = ts.getAllTransactions();
		Transaction t = transactions.get(0);*/
		ts.processFailedTransaction();
	}

}
