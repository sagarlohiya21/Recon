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
public class StatusCheckRequest {
	
	private String type;
	private String date;
	private String extnwCode;
	private String msisdn;
	private String pin;
	private String loginId;
	private String password;
	private String extCode;
	private String extrefNum;
	private String txnId;
	private String language;
	private String interfaceId;
}
