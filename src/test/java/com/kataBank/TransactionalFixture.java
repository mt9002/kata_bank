package com.kataBank;

import com.kataBank.dto.TransactionRequest;

public class TransactionalFixture {

    public static TransactionRequest transactionReq(){
        return new TransactionRequest("BANCOLOMBIAAHORROS000000082", 30000);
    }
}
