package com.comviva.reconciliation.service;

import com.comviva.reconciliation.entity.StatusCheckResponse;
import com.comviva.reconciliation.entity.Transaction;

public interface RequestService {

	StatusCheckResponse sendStatusCheckRequest(Transaction transaction);
	boolean sendReversalRequest(Transaction transaction) throws Exception;

}
