package com.fraud.model.rules;


public class FraudTransactionRequest {
	 private double transactionAmount;
	 private int timesCardUsed;
	 private long  cardNum;
	 
	public double getTransactionAmount() {
		return transactionAmount;
	}
	
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	public int getTimesCardUsed() {
		return timesCardUsed;
	}
	
	public void setTimesCardUsed(int timesCardUsed) {
		this.timesCardUsed = timesCardUsed;
	}
	public long getCardNum() {
		return cardNum;
	}

	public void setCardNum(long cardNum) {
		this.cardNum = cardNum;
	}

}
