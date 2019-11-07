package com.comviva.cvs.entity;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReversalResponse {

	private String type;
	private String txnStatus;
	private String date;
	private String extRefNum;
	private String amount;
	private String txnDate;
	private String serviceType;
	private String txnId;
	private String message;

}
