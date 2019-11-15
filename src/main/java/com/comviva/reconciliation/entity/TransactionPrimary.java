package com.comviva.reconciliation.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author Prateek 
* The Embeddable class for the primary key for the database
*/

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPrimary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate TransactionDate;
	private String RetailerMsisdn;
	private String TransactionId;
}
