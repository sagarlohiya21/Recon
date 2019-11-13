package com.comviva.reconciliation.service;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPrimary {
	private Date TransactionDate;
	private String RetailerMsisdn;
	private String TransactionId;
}
