package com.kataBank;

import com.kataBank.dto.TransactionRequest;

public class TransactioFixture {

    public static TransactionRequest transactionReq(){
        return new TransactionRequest("BANCOLOMBIAAHORROS000000082", 10000);
    }
}
