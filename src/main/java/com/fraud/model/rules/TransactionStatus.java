package com.fraud.model.rules;

public enum TransactionStatus {
	APPROVED,DECLINED;
	
	public String getValue() {
        return this.toString();
    }
}
