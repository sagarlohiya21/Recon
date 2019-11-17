package com.comviva.reconciliation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comviva.reconciliation.entity.Transaction;
import com.comviva.reconciliation.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	TransactionService ts;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/getAllFailedTransactions")
	public List<Transaction> getTransaction() {
		return ts.getFailedTransactions();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public void testWorking() {
		ts.processFailedTransaction();
	}

}
