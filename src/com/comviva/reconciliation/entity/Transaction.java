package com.comviva.reconciliation.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;


/**
 * The persistent class for the recharge_details database table.
 * 
 */
@Entity
@Table(name="recharge_details")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TRANSACTION_ID")
	private String transactionId;

	@Column(name="CAMPAIGN_NAME")
	private String campaignName;

	@Column(name="CE_TIME_TAKEN")
	private String ceTimeTaken;

	@Column(name="DEBUG_TRANSACTION_ID")
	private String debugTransactionId;

	@Column(name="E2E_TIME_TAKEN")
	private String e2eTimeTaken;

	@Column(name="FACE_VALUE")
	private String faceValue;

	@Column(name="OFFER_CATEGORY")
	private String offerCategory;

	@Column(name="OFFER_NAME")
	private String offerName;

	@Column(name="OFFER_TYPE")
	private String offerType;

	@Column(name="PRETUPS_TIME_TAKEN")
	private String pretupsTimeTaken;

	@Column(name="RECHARGE_AMOUNT")
	private String rechargeAmount;

	@Column(name="RETAILER_COMMISSION")
	private String retailerCommission;

	@Column(name="RETAILER_MSISDN")
	private String retailerMsisdn;

	@Column(name="SMS_TIME_TAKEN")
	private String smsTimeTaken;

	@Column(name="START_TIME")
	private String startTime;

	@Column(name="SUBSCRIBER_BONUS")
	private String subscriberBonus;

	@Column(name="SUBSCRIBER_MSISDN")
	private String subscriberMsisdn;

	@Column(name="TRANSACTION_DATE")
	private String transactionDate;

	@Column(name="TRANSACTION_STATUS")
	private String transactionStatus;

	@Column(name="VAS_TIME_TAKEN")
	private String vasTimeTaken;

	public Transaction() {
	}

	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getCampaignName() {
		return this.campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getCeTimeTaken() {
		return this.ceTimeTaken;
	}

	public void setCeTimeTaken(String ceTimeTaken) {
		this.ceTimeTaken = ceTimeTaken;
	}

	public String getDebugTransactionId() {
		return this.debugTransactionId;
	}

	public void setDebugTransactionId(String debugTransactionId) {
		this.debugTransactionId = debugTransactionId;
	}

	public String getE2eTimeTaken() {
		return this.e2eTimeTaken;
	}

	public void setE2eTimeTaken(String e2eTimeTaken) {
		this.e2eTimeTaken = e2eTimeTaken;
	}

	public String getFaceValue() {
		return this.faceValue;
	}

	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}

	public String getOfferCategory() {
		return this.offerCategory;
	}

	public void setOfferCategory(String offerCategory) {
		this.offerCategory = offerCategory;
	}

	public String getOfferName() {
		return this.offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferType() {
		return this.offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getPretupsTimeTaken() {
		return this.pretupsTimeTaken;
	}

	public void setPretupsTimeTaken(String pretupsTimeTaken) {
		this.pretupsTimeTaken = pretupsTimeTaken;
	}

	public String getRechargeAmount() {
		return this.rechargeAmount;
	}

	public void setRechargeAmount(String rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public String getRetailerCommission() {
		return this.retailerCommission;
	}

	public void setRetailerCommission(String retailerCommission) {
		this.retailerCommission = retailerCommission;
	}

	public String getRetailerMsisdn() {
		return this.retailerMsisdn;
	}

	public void setRetailerMsisdn(String retailerMsisdn) {
		this.retailerMsisdn = retailerMsisdn;
	}

	public String getSmsTimeTaken() {
		return this.smsTimeTaken;
	}

	public void setSmsTimeTaken(String smsTimeTaken) {
		this.smsTimeTaken = smsTimeTaken;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getSubscriberBonus() {
		return this.subscriberBonus;
	}

	public void setSubscriberBonus(String subscriberBonus) {
		this.subscriberBonus = subscriberBonus;
	}

	public String getSubscriberMsisdn() {
		return this.subscriberMsisdn;
	}

	public void setSubscriberMsisdn(String subscriberMsisdn) {
		this.subscriberMsisdn = subscriberMsisdn;
	}

	public String getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionStatus() {
		return this.transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getVasTimeTaken() {
		return this.vasTimeTaken;
	}

	public void setVasTimeTaken(String vasTimeTaken) {
		this.vasTimeTaken = vasTimeTaken;
	}

}