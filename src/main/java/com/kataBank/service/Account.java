package com.kataBank.service;

import java.time.LocalDateTime;
import java.util.List;

public class Account {
    private Long id;
    private String numAccount;
    private double amount;
    private List<Extract> extracts;
    private LocalDateTime registerDate;

    public Account(double amount) {
        this.amount = amount;
    }
    public Account() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumAccount() {
        return numAccount;
    }

    public void setNumAccount(String numAccount) {
        this.numAccount = numAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Extract> getExtracts() {
        return extracts;
    }

    public void setExtracts(List<Extract> extracts) {
        this.extracts = extracts;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }
}
