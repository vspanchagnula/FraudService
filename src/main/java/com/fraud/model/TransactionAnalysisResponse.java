package com.fraud.model;

import com.fraud.model.rules.TransactionStatus;

public class TransactionAnalysisResponse {
	
	private TransactionStatus transactionStatus;
	private long  cardNumber;
	private double  transactionAmount;
	private int cardUsageInLastSevenDays;
	
	
	
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public int getCardUsageInLastSevenDays() {
		return cardUsageInLastSevenDays;
	}
	public void setCardUsageInLastSevenDays(int cardUsageInLastSevenDays) {
		this.cardUsageInLastSevenDays = cardUsageInLastSevenDays;
	}
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	

}
