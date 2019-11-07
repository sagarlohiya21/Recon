package com.comviva.cvs.entity;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatusCheckResponse {

	private String type;
	private String txnStatus;
	private String date;
	private String extRefNum;
	private String txnId;
	private String reqStatus;
	private String message;
}
