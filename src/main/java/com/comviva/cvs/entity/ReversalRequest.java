package com.comviva.cvs.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReversalRequest {
	private String type;
	private String date;
	private String extnwCode;
	private String msisdn;
	private String pin;
	private String loginId;
	private String password;
	private String extCode;
	private String extrefNum;
	private String msisdn2;
	private String txnId;
	private String language1;
	private String langugae2;
}
