package com.kataBank.service;

import java.time.LocalDateTime;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }
}
