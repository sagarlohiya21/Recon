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
	SimulatorRequest simReq;

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
		// iterating through all the transactions
		for (Transaction requestTransaction : transactionList) {

//			// send HTTP request for each transaction
//			URL url = null;
//			HttpURLConnection con = null;
//			DataOutputStream out = null;
//			BufferedReader in = null;
//			try {
//				con = (HttpURLConnection) url.openConnection();
//				con.setRequestMethod("POST");
//				con.setDoOutput(true);
//				con.setConnectTimeout(5000);
//				out = new DataOutputStream(con.getOutputStream());
//
//				out.writeBytes(requestTransaction.toString());
//
//				int responseStatus = con.getResponseCode();
//
//				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//				String inputLine;
//				StringBuffer responseString = new StringBuffer();
//				while ((inputLine = in.readLine()) != null) {
//					responseString.append(inputLine);
//				}
//				XmlMapper xmlMapper = new XmlMapper();
//				StatusCheckResponse response = xmlMapper.readValue(responseString.toString(),
//						StatusCheckResponse.class);
//				System.out.println("in status check" + responseString);
//				System.out.println("response obj" + response.toString());
//
//				boolean check = false;
//				// if transaction status in response is success, that is money has been debited,
//				// then call reversal
//				// if transaction status in response is failure, that is money is not debited,
//				// then update status code in the database
//				if (response.getTxnStatus().equals("200")) {
//					System.out.println("calling reversal");
//					check = sendReversalRequest(requestTransaction);
//				}
//
//				if (check) {
//					updateStatusCode(requestTransaction);
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				in.close();
//				out.flush();
//				out.close();
//				con.disconnect();
//			}
		}
	}

	public boolean sendReversalRequest(Transaction transaction) {
		return false;

//		int attemptCount = 0;
//		boolean simulatedStatus = false;
//		// send HTTP request for each transaction
//		URL url = null;
//		HttpURLConnection con = null;
//		DataOutputStream out = null;
//		BufferedReader in = null;
//		ReversalResponse response = null;
//		try {
//			url = new URL("http://localhost:8090/doReversal");
//			con = (HttpURLConnection) url.openConnection();
//			con.setRequestMethod("POST");
//			con.setDoOutput(true);
//			con.setConnectTimeout(5000);
//			out = new DataOutputStream(con.getOutputStream());
//
//			do {
//				out.writeBytes(transaction.toString());
//
//				simulatedStatus = false;
//				int responseStatus = con.getResponseCode();
//				// to be used in real scenario
//
//				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//				String inputLine;
//				StringBuffer responseString = new StringBuffer();
//				while ((inputLine = in.readLine()) != null) {
//					responseString.append(inputLine);
//				}
//				XmlMapper xmlMapper = new XmlMapper();
//				response = xmlMapper.readValue(responseString.toString(), ReversalResponse.class);
//
//				System.out.println("in reversal" + responseString);
//				System.out.println("response obj" + response.toString());
//
//				// only simulated scenario. not valid in real scenario where a failed response
//				// corresponds to no response object
//				if (response.getTxnStatus().equals("200"))
//					simulatedStatus = true;
//				else
//					simulatedStatus = false;
//
//			} while (attemptCount <= 7);
//
//			in.close();
//			out.flush();
//			out.close();
//			con.disconnect();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return simulatedStatus;

	}

}
