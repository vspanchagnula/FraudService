package com.fraud.model.rules;


public class FraudTransactionResponse {
	private TransactionStatus transactionStatus;
	private double  transactionAmount;
	private int cardUsageInLastSevenDays;
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
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

}
