package com.comviva.cvs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comviva.cvs.entity.Transaction;
import com.comviva.cvs.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	TransactionService ts;
	
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

}
