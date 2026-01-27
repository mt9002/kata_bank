package com.kataBank.service;

import com.kataBank.dto.TransactionRequest;

public interface TransactionService {
    void deposit(TransactionRequest transactionRequest);
    void withDraw(TransactionRequest transactionRequest);
}
