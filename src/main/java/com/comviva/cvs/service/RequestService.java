package com.comviva.cvs.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.comviva.cvs.entity.ReversalRequest;
import com.comviva.cvs.entity.ReversalResponse;
import com.comviva.cvs.entity.StatusCheckRequest;
import com.comviva.cvs.entity.StatusCheckResponse;
import com.comviva.cvs.entity.Transaction;

@Service
public class RequestService {

	/**
	 * Pseudo created Status check and reversal requests for the program for testing
	 * purposes To be created with actual parameters after clarification
	 */

	StatusCheckRequest statusCheckRequest = new StatusCheckRequest("dummy", "dummy", "dummy", "dummy", "dummy", "dummy",
			"dummy", "dummy", "dummy", "dummy", "dummy", "dummy");

	ReversalRequest reversalRequest = new ReversalRequest("dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy",
			"dummy", "dummy", "dummy", "dummy", "dummy", "dummy");

	RestTemplate restTemplate = new RestTemplate();

	public HttpHeaders getHeader() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_XML);
		return header;
	}

	public StatusCheckResponse sendStatusCheckRequestSuccess(Transaction transaction) throws Exception {
		String urlString = "http://localhost:8090/successStatus";
		ResponseEntity<StatusCheckResponse> response = restTemplate.postForEntity(urlString, this.statusCheckRequest,
				StatusCheckResponse.class);
		System.out.println(response.getBody());
		return response.getBody();
	}

	public StatusCheckResponse sendStatusCheckRequestFail(Transaction transaction) {
		String urlString = "http://localhost:8090/failedStatus";
		ResponseEntity<StatusCheckResponse> response = restTemplate.postForEntity(urlString, this.statusCheckRequest,
				StatusCheckResponse.class);
		System.out.println(response.getBody());
		return response.getBody();
	}

	public ReversalResponse sendReversalRequestSuccess(Transaction transaction) {
		String urlString = "http://localhost:8090/successReversal";
		ResponseEntity<ReversalResponse> response = restTemplate.postForEntity(urlString, this.reversalRequest,
				ReversalResponse.class);
		System.out.println(response.getBody());
		return response.getBody();
	}

	public ReversalResponse sendReversalRequestFail(Transaction transaction) {
		String urlString = "http://localhost:8090/failedReversal";
		ResponseEntity<ReversalResponse> response = restTemplate.postForEntity(urlString, this.reversalRequest,
				ReversalResponse.class);
		System.out.println(response.getBody());
		return response.getBody();
	}

}
