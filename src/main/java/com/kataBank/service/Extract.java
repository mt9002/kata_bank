package com.kataBank.service;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Extract {

    private double amount;
    private double balance;
    private Account account;
    private LocalDateTime registerDate;

    public Extract() {}
    public Extract(double amount, double balance, Account account) {
        this.amount = amount;
        this.balance = balance;
        this.account = account;
    }
    public Extract(double amount, double balance, LocalDateTime registerDate) {
        this.amount = amount;
        this.balance = balance;
        this.registerDate = registerDate;
    }
}
