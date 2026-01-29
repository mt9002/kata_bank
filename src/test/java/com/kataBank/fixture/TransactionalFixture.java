package com.kataBank.fixture;

import com.kataBank.dto.TransactionRequest;
import com.kataBank.service.Account;
import com.kataBank.service.Extract;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.kataBank.fixture.AccountFixture.account;

public class TransactionalFixture {

    public static TransactionRequest transactionReq(){
        return new TransactionRequest("BANCOLOMBIAAHORROS000000082", 30000);
    }

    public static List<Extract> extractList(){

        return Arrays.asList(
                new Extract(20000, 50000, LocalDateTime.of(2026, 1, 27, 10, 0)),
                new Extract(-15000, 35000,  LocalDateTime.of(2026, 1, 27, 12, 30)),
                new Extract(10000, 45000, LocalDateTime.of(2026, 1, 27, 15, 45)),
                new Extract(-5000, 40000, LocalDateTime.of(2026, 1, 28, 9, 15))
        );
    }
}
