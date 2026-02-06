package com.kataBank.controller;

import com.kataBank.dto.TransactionRequest;
import com.kataBank.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> transaction(@RequestBody TransactionRequest transactionRequest){
        transactionService.transaction(transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
