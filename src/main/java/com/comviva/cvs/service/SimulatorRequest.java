package com.comviva.cvs.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
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
import com.fasterxml.jackson.xml.XmlMapper;

@Service
public class SimulatorRequest {

//	@Value("${url}")
	// private String url;

	/**
	 * Pseudo created Status check and reversal requests for the program for testing
	 * purposes To be created with actual parameters after clarification
	 */

	StatusCheckRequest statusCheckRequest = new StatusCheckRequest("dummy", "dummy", "dummy", "dummy", "dummy", "dummy",
			"dummy", "dummy", "dummy", "dummy", "dummy", "dummy");

	ReversalRequest reversalRequest = new ReversalRequest("dummy", "dummy", "dummy", "dummy", "dummy", "dummy", "dummy",
			"dummy", "dummy", "dummy", "dummy", "dummy", "dummy");

	private XmlMapper xmlMapper;

	public HttpURLConnection createConnection(URL url, Transaction transaction) throws IOException {
		HttpURLConnection con = null;

		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setConnectTimeout(5000);
		con.disconnect();
		return con;
	}

	public String receiveResponseString(HttpURLConnection con) throws IOException {

		BufferedReader in = null;
		StringBuffer responseString;
		String inputLine;

		in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		responseString = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			responseString.append(inputLine);
		}

		in.close();
		return responseString.toString();

	}

	public StatusCheckResponse receiveStatusCheckResponse(URL url, Transaction requestTransaction) throws IOException {
		StatusCheckResponse response = null;
		ReversalResponse reversalResponse = null;
		DataOutputStream out = null;

		HttpURLConnection con = createConnection(url, requestTransaction);

		out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(requestTransaction.toString());

		int responseStatus = con.getResponseCode();

		String responseString = receiveResponseString(con);

		xmlMapper = new XmlMapper();
		response = xmlMapper.readValue(responseString, StatusCheckResponse.class);

		System.out.println("in status check" + responseString);
		System.out.println("response obj" + response.toString());

		// if transaction status in response is success, that is money has been debited,
		// then call reversal
		// if transaction status in response is failure, that is money is not debited,
		// then update status code in the database
		if (response.getTxnStatus().equals("200")) {
			System.out.println("calling reversal");
			reversalResponse = sendReversalRequestSuccess(requestTransaction);
		}

		if (reversalResponse.getTxnStatus().contentEquals("200")) {
			sendReversalRequestSuccess(requestTransaction);
		}

		out.flush();
		out.close();

		return response;
	}

	public ReversalResponse receiveReversalResponse(URL url, Transaction transaction) throws IOException {
		int attemptCount = 0;
		boolean simulatedStatus = false;
		ReversalResponse response = null;
		DataOutputStream out = null;

		HttpURLConnection con = createConnection(url, transaction);
		out = new DataOutputStream(con.getOutputStream());

		do {
			out.writeBytes(transaction.toString());

			simulatedStatus = false;

			// to be used in real scenario
			int responseStatus = con.getResponseCode();

			String responseString = receiveResponseString(con);

			XmlMapper xmlMapper = new XmlMapper();
			response = xmlMapper.readValue(responseString.toString(), ReversalResponse.class);

			System.out.println("in reversal" + responseString);
			System.out.println("response obj" + response.toString());

			// only simulated scenario. not valid in real scenario where a failed response
			// corresponds to no response object
			if (response.getTxnStatus().equals("200"))
				simulatedStatus = true;
			else
				simulatedStatus = false;

		} while (attemptCount <= 7);

		out.flush();
		out.close();

		return response;
	}

	public StatusCheckResponse sendStatusCheckRequestSuccess(Transaction transaction) throws Exception {
		String urlString = "http://localhost:8090/successStatus";
		
		URL url = new URL(urlString);
		
		return receiveStatusCheckResponse(url, transaction);
	}

	public StatusCheckResponse sendStatusCheckRequestFail(Transaction transaction) {
		String url = "http://localhost:8090/failedStatus";

		return null;
	}

	public ReversalResponse sendReversalRequestSuccess(Transaction transaction) {
		String url = "http://localhost:8090/successReversal";

		return null;
	}

	public ReversalResponse sendReversalRequestFail(Transaction transaction) {
		String url = "http://localhost:8090/failedReversal";

		return null;
	}

}
