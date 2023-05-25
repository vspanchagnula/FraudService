package com.fraud.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraud.model.rules.FraudTransactionRequest;
import com.fraud.model.rules.FraudTransactionResponse;
import com.fraud.model.rules.TransactionStatus;

@Service
public class FraudRulesService {
	
	 @Autowired
	    private KieContainer kieContainer;
	 
	    public FraudTransactionResponse processRules(FraudTransactionRequest fraudTransactionRequest) {
	    	FraudTransactionResponse fraudTransactionResponse = new FraudTransactionResponse();
	        KieSession kieSession = kieContainer.newKieSession();
	        kieSession.setGlobal("fraudTransactionResponse", fraudTransactionResponse);
	        kieSession.insert(fraudTransactionRequest);
	        kieSession.fireAllRules();
	        kieSession.dispose();
	        
	        populateResponse(fraudTransactionRequest, fraudTransactionResponse);
	        
	        return fraudTransactionResponse;
	    }

		private void populateResponse(FraudTransactionRequest fraudTransactionRequest,
				FraudTransactionResponse fraudTransactionResponse) {
			fraudTransactionResponse.setCardUsageInLastSevenDays(fraudTransactionRequest.getTimesCardUsed());
	        fraudTransactionResponse.setTransactionAmount(fraudTransactionRequest.getTransactionAmount());
	        if(null== fraudTransactionResponse.getTransactionStatus()) {
	    	   fraudTransactionResponse.setTransactionStatus (TransactionStatus.APPROVED);
	        }
		}

}
