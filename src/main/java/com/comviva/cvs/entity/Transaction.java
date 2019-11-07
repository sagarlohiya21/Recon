package com.comviva.cvs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.xml.txw2.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the recharge_details database table.
 * 
 */
@Entity
@Table(name = "recharge_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {

	@Id
	private String transactionId;

	private String campaignName;

	private String ceTimeTaken;

	private String debugTransactionId;

	private String e2eTimeTaken;

	private String faceValue;

	private String offerCategory;

	private String offerName;

	private String offerType;

	private String pretupsTimeTaken;

	private String rechargeAmount;

	private String retailerCommission;

	private String retailerMsisdn;

	private String smsTimeTaken;

	private String startTime;

	private String subscriberBonus;

	private String subscriberMsisdn;

	private String transactionDate;

	private String transactionStatus;

	private String vasTimeTaken;

}
