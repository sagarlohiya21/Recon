package com.comviva.cvs.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.comviva.cvs.entity.ReversalRequest;
import com.comviva.cvs.entity.ReversalResponse;
import com.comviva.cvs.entity.StatusCheckRequest;
import com.comviva.cvs.entity.StatusCheckResponse;
import com.comviva.cvs.entity.Transaction;

@Service
public class SimulatorRequest {

	/**
	 * Pseudo created Status check and reversal requests for the program for testing
	 * purposes To be created with actual parameters after clarification
	 */

	StatusCheckRequest statusCheckRequest = new StatusCheckRequest("dummy", "dummy", "dummy", "dummy", "dummy", "dummy",
			"dummy", "dummy", "dummy", "dummy", "dummy", "dummy");

	ReversalRequest reversalRequest = new ReversalRequest("dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy",
			"dummy", "dummy", "dummy", "dummy", "dummy", "dummy");
	
	RestTemplate restTemplate = new RestTemplate();
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_XML);

	public StatusCheckResponse sendStatusCheckRequestSuccess(Transaction transaction) {
		String url = "http://localhost:8090/successStatus";
		
		ResponseEntity<StatusCheckResponse> response = restTemplate.postForEntity(url, statusCheckRequest, StatusCheckResponse.class);
		return response.getBody();
	}
	
	public StatusCheckResponse sendStatusCheckRequestFail(Transaction transaction) {
		String url = "http://localhost:8090/failedStatus";
		
		ResponseEntity<StatusCheckResponse> response = restTemplate.postForEntity(url, statusCheckRequest, StatusCheckResponse.class);
		return response.getBody();
	}
	
	public ReversalResponse sendReversalRequestSuccess(Transaction transaction) {
		String url = "http://localhost:8090/successReversal";
	
		ResponseEntity<ReversalResponse> response = restTemplate.postForEntity(url, reversalRequest, ReversalResponse.class);
		return response.getBody();
	}
	
	public ReversalResponse sendReversalRequestFail(Transaction transaction) {
		String url = "http://localhost:8090/failedReversal";
	
		ResponseEntity<ReversalResponse> response = restTemplate.postForEntity(url, reversalRequest, ReversalResponse.class);
		return response.getBody();
	}
	
}
