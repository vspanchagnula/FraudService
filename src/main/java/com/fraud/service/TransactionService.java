package com.fraud.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fraud.exception.FraudException;
import com.fraud.model.TransactionAnalysisResponse;
import com.fraud.model.incoming.Transaction;
import com.fraud.model.rules.FraudTransactionRequest;
import com.fraud.model.rules.FraudTransactionResponse;

@Service
public class TransactionService {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	FraudRulesService fraudRulesService ;
	
	final String ROOT_URI = "https://www.random.org/integers/?num=7&min=0&max=12&col=1&base=10&format=plain&rnd=new";
	
	Logger logger= LoggerFactory.getLogger(TransactionService.class);
	
	
	public TransactionAnalysisResponse  analyzeTransaction(Transaction transaction) throws FraudException {
		int timesCardUsed = getCardUsage();
		FraudTransactionRequest fraudTransactionRequest = mapRequest(transaction, timesCardUsed);
		
		logger.info("amount: {},number of times card was used in last 7 days : {}", transaction.getAmount(),timesCardUsed);
		
		FraudTransactionResponse fraudTransactionResponse = fraudRulesService.processRules(fraudTransactionRequest);
		
		TransactionAnalysisResponse transactionAnalysisResponse = new TransactionAnalysisResponse();
		
		mapResponse(transaction, fraudTransactionResponse, transactionAnalysisResponse);
		
		return transactionAnalysisResponse;
		
	}
	
	public int getCardUsage() throws FraudException {
		int totalTransactionCount =0;
		try {
			ResponseEntity<String> response= restTemplate.getForEntity(ROOT_URI, String.class);
			System.out.println("Response is "+response.getBody());
			String responseBody = response.getBody();
			
			
			 totalTransactionCount = Arrays.stream(responseBody.split("\\r?\\n|\\r"))
				    .mapToInt(Integer::valueOf)
				    .sum();
			
			
		}catch(Exception e) {
			throw new FraudException("Error invoking downstream service");
		}
		return totalTransactionCount;

	}

	private void mapResponse(Transaction transaction, FraudTransactionResponse fraudTransactionResponse,
			TransactionAnalysisResponse transactionAnalysisResponse) {
		transactionAnalysisResponse.setCardNumber(transaction.getCardNum());
		transactionAnalysisResponse.setCardUsageInLastSevenDays(fraudTransactionResponse.getCardUsageInLastSevenDays());
		transactionAnalysisResponse.setTransactionAmount(fraudTransactionResponse.getTransactionAmount());
		transactionAnalysisResponse.setTransactionStatus(fraudTransactionResponse.getTransactionStatus());
	}

	private FraudTransactionRequest mapRequest(Transaction transaction, int timesCardUsed) {
		FraudTransactionRequest fraudTransactionRequest = new FraudTransactionRequest();
		fraudTransactionRequest.setTransactionAmount(transaction.getAmount());
		fraudTransactionRequest.setTimesCardUsed(timesCardUsed);
		fraudTransactionRequest.setCardNum(transaction.getCardNum());
		return fraudTransactionRequest;
	}
}
