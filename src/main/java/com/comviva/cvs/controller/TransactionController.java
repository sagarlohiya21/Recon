package com.comviva.cvs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comviva.cvs.entity.Transaction;
import com.comviva.cvs.service.RequestService;
import com.comviva.cvs.service.Test;
import com.comviva.cvs.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	TransactionService ts;
	
	@Autowired
	RequestService rs;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/getAllFailedTransactions")
	public List<Transaction> getTransaction() throws Exception {
		List<Transaction> transactionList = ts.getFailedTransactions();

		ts.sendStatusCheckRequest(transactionList);

		return ts.getFailedTransactions();
	}

	@CrossOrigin
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
		
		
		rs.sendStatusCheckRequestSuccess(t);
		rs.sendStatusCheckRequestFail(t);
		rs.sendReversalRequestSuccess(t);
		rs.sendReversalRequestFail(t);
		System.out.println("FUCK ");
	}

}
