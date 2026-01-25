package com.kataBank.controller;

import com.kataBank.dto.TransactionRequest;
import com.kataBank.service.ITransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody TransactionRequest transactionRequest){
        transactionService.deposit(transactionRequest);
        return ResponseEntity.status(HttpStatus.OK).body("successful deposit");
    }

    @PostMapping("/withDraw")
    public ResponseEntity<String> withDraw(@RequestBody TransactionRequest transactionRequest){
        transactionService.withDraw(transactionRequest);
        return ResponseEntity.status(HttpStatus.OK).body("successful withdraw");
    }
}
