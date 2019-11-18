package com.comviva.reconciliation.constants;

public class Constants {

	private Constants() {

	}

	/**
	 * Transaction status to be updated after processing ambiguous transaction
	 * successfully, having status of 22 or 23
	 */
	public static final int SUCCESSFUL_TRANSACTION_STATUS_1 = 29;
	/**
	 * Transaction status to be updated after processing ambiguous transaction
	 * successfully, having status of 25 or 26
	 */
	public static final int SUCCESSFUL_TRANSACTION_STATUS_2 = 31;
	/**
	 * Transaction status to be updated if the reversal fails while processing
	 * ambiguous transaction
	 */
	public static final int REVERSAL_FAILED_TRASANCTION_STATUS = 21;
}
