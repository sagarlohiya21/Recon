package com.comviva.reconciliation.entity;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Prateek 
 * The entity class for the recharge_details database
 *         table.
 */
@Entity
@Table(name = "recharge_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {

	@EmbeddedId
	private TransactionPrimary primaryTransaction;

	private String campaignName;
	private Integer ceTimeTaken;
	private String debugTransactionId;
	private Integer e2eTimeTaken;
	private String faceValue;
	private String offerCategory;
	private String offerName;
	private Integer offerType;
	private Integer pretupsTimeTaken;
	private Double rechargeAmount;
	private Double retailerCommission;
	private Integer smsTimeTaken;
	private LocalDate startTime;
	private LocalDate subscriberBonus;
	private String subscriberMsisdn;
	private Integer transactionStatus;
	private Integer vasTimeTaken;
}
