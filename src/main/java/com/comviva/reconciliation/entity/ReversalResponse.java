package com.comviva.reconciliation.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hithesh A Bandodkar
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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

