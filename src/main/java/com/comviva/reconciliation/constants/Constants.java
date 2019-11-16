package com.comviva.reconciliation.constants;

public class Constants {
	
	/**
	 * Transaction status to be updated after processing 
	 * ambiguous transaction successfully, having status of 22 or 23
	 */
	public static final String SUCCESSFUL_TRANSACTION_STATUS_1 = "29";
	/**
	 * Transaction status to be updated after processing 
	 * ambiguous transaction successfully, having status of 25 or 26
	 */
	public static final String SUCCESSFUL_TRANSACTION_STATUS_2 = "31";
	/**
	 * Transaction status to be updated if the reversal fails while
	 * processing ambiguous transaction
	 */
	public static final String REVERSAL_FAILED_TRASANCTION_STATUS = "21";
}
