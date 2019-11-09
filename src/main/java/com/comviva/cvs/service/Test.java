package com.comviva.cvs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.comviva.cvs.entity.ReversalRequest;
import com.comviva.cvs.entity.StatusCheckRequest;
import com.comviva.cvs.entity.StatusCheckResponse;

@Service
public class Test {
	@Autowired
	TransactionService ts;

	StatusCheckRequest statusCheckRequest = new StatusCheckRequest("dummy", "dummy", "dummy", "dummy", "dummy", "dummy",
			"dummy", "dummy", "dummy", "dummy", "dummy", "dummy");
	
	ReversalRequest reversalRequest = new ReversalRequest("dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy",
			"dummy", "dummy", "dummy", "dummy", "dummy", "dummy");

	public HttpHeaders getHeader() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_XML);
		return header;
	}

	public StatusCheckResponse testSuccessStatus() {
		String urlString = "http://localhost:8090/successStatus";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<StatusCheckResponse> response = restTemplate.postForEntity(urlString, this.statusCheckRequest,
				StatusCheckResponse.class);
		System.out.println("* ***********************************");
		System.out.println(response);
		return response.getBody();
	}

}
