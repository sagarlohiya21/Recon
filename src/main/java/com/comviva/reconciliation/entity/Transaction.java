package com.comviva.reconciliation.entity;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.comviva.reconciliation.service.TransactionPrimary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Manjunath The persistent class for the recharge_details database
 *         table.
 * 
 */
@Entity
@Table(name = "recharge_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {

	@Id
	@Embedded
	private TransactionPrimary primaryTransaction;
	
	private String campaignName;
	private int ceTimeTaken;
	private String debugTransactionId;
	private int e2eTimeTaken;
	private String faceValue;
	private String offerCategory;
	private String offerName;
	private int offerType;
	private int pretupsTimeTaken;
	private double rechargeAmount;
	private double retailerCommission;
	private int smsTimeTaken;
	private Date startTime;
	private Date subscriberBonus;
	private String subscriberMsisdn;
	private int transactionStatus;
	private int vasTimeTaken;
}
