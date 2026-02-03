package com.kataBank.fixture;

import com.kataBank.dto.AccountRequest;
import com.kataBank.service.Account;

import java.time.LocalDateTime;

public class AccountFixture {

    public static AccountRequest accountRequest(){
        return AccountRequest.builder()
                .branch("bancolombia")
                .typeAccount("ahorros")
                .userIdentity("1075")
                .amount(50000)
                .build();
    }

    public static AccountRequest accountMinInitialValue(){
        return AccountRequest.builder()
                .branch("bancolombia")
                .typeAccount("ahorros")
                .userIdentity("1075")
                .amount(500)
                .build();
    }

    public static AccountRequest accountMaxInitialValue(){
        return AccountRequest.builder()
                .branch("bancolombia")
                .typeAccount("ahorros")
                .userIdentity("1075")
                .amount(500000000)
                .build();
    }


    public static AccountRequest accountIntegrationReq(){
        return new AccountRequest(
                50000,
                "bancolombia",
                "ahorros",
                "1075"
                );
    }

    public static Account accountSavedWithoutNumber() {
        return Account.builder()
                .id(1L)
                .amount(50000)
                .numAccount(null)
                .userIdentity("1075")
                .registerDate(LocalDateTime.of(2026, 1, 27, 3, 0))
                .build();
    }

    public static Account accountSavedWithNumber() {
        return Account.builder()
                .id(1L)
                .amount(50000)
                .numAccount("BANCOLOMBIAAHORROS000000082")
                .userIdentity("1075")
                .registerDate(LocalDateTime.of(2026, 1, 27, 3, 0))
                .build();
    }
    public static Account account() {
        return Account.builder()
                .id(1L)
                .amount(50000)
                .numAccount("BANCOLOMBIAAHORROS000000082")
                .userIdentity("1075")
                .registerDate(LocalDateTime.of(2026, 1, 27, 3, 0))
                .build();
    }

    public static Account accountWithoutId() {
        return Account.builder()
                .amount(50000)
                .numAccount("BANCOLOMBIAAHORROS000000082")
                .userIdentity("1075")
                .registerDate(LocalDateTime.of(2026, 1, 27, 3, 0))
                .build();
    }
    public static Account accountWithBalance() {
        return Account.builder()
                .id(1L)
                .amount(80000)
                .numAccount("BANCOLOMBIAAHORROS000000082")
                .userIdentity("1075")
                .registerDate(LocalDateTime.of(2026, 1, 27, 3, 0))
                .build();
    }

    public static AccountRequest accountRequestInvalid(){
        return AccountRequest.builder()
                .branch(" ")
                .typeAccount(" ")
                .amount(50000)
                .build();
    }

    public static AccountRequest accountRequestInvalidNull(){
        return AccountRequest.builder()
                .build();
    }

    public static AccountRequest accountMinInitialAmount(){
        return AccountRequest.builder()
                .branch("bancolombia")
                .typeAccount("ahorros")
                .userIdentity("1075")
                .amount(30000)
                .build();
    }

    public static Account accountCreate(){
        return Account.builder()
                .numAccount("BANCOLOMBIAAHORROS000000082")
                .userIdentity("1075")
                .amount(90000)
                .build();
    }
}
