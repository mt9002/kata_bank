package com.kataBank.service;

import com.kataBank.dto.TransactionRequest;

public interface TransactionService {
    void transaction(TransactionRequest transactionReq);
}
