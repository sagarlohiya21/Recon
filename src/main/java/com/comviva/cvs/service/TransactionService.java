package com.comviva.cvs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comviva.cvs.dao.TransactionDao;
import com.comviva.cvs.entity.StatusCheckResponse;
import com.comviva.cvs.entity.Transaction;

@Service
public class TransactionService {
	@Autowired
	TransactionDao dao;

	@Autowired
	RequestService simReq;

	public List<Transaction> getAllTransactions() {
		return dao.findAllTransaction();
	}

	public List<Transaction> getFailedTransactions() {
		return dao.findAllFailedTransaction();
	}

	public List<Transaction> getFailedTransactions(Integer retailerMSISDN) {
		return dao.findAllFailedTransactionsByRetailerMSISDN(retailerMSISDN);
	}

	public List<Transaction> getFailedTransactions(String date) {
		return dao.findAllFailedTransactionsByDate(date);
	}

	public void updateStatusCode(Transaction transaction) {
		dao.updateDBStatusCode(transaction);
	}

	public void updateFaceValue(Transaction transaction, int count) {
		dao.updateDBFaceValue(transaction, count);
	}

	public void statusCheckSuccess(Transaction transaction) throws Exception {
		StatusCheckResponse response = simReq.sendStatusCheckRequestSuccess(transaction);
		System.out.println(response);
	}

	public void sendStatusCheckRequest(List<Transaction> transactionList) throws Exception {

	}

	public void sendReversalRequest(Transaction transaction) {
		
	}
}
