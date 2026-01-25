package com.kataBank.service;

import com.kataBank.dto.TransactionRequest;

public interface ITransactionService {
    void deposit(TransactionRequest transactionRequest);
    void withDraw(TransactionRequest transactionRequest);
}
