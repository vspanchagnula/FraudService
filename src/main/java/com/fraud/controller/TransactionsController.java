package com.fraud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fraud.exception.FraudException;
import com.fraud.model.TransactionAnalysisResponse;
import com.fraud.model.incoming.Transaction;
import com.fraud.service.TransactionService;

@RestController
public class TransactionsController {

	@Autowired
	TransactionService transactionService ;
	
	Logger logger= LoggerFactory.getLogger(TransactionsController.class);
	
	
	
	@PostMapping(value = "/analyzeTransaction", consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<TransactionAnalysisResponse> analyzeTransaction(@RequestBody Transaction transaction) throws FraudException {
		
		
		TransactionAnalysisResponse transactionAnalysisResponse;
		try {
			transactionAnalysisResponse = transactionService.analyzeTransaction(transaction);
		} catch (FraudException e) {
			throw new FraudException(e.getMessage());
		}
		
		return new ResponseEntity<>(transactionAnalysisResponse, HttpStatus.OK);
		
		 
	}

}
