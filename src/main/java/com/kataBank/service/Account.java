package com.kataBank.service;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class Account {
    private Long id;
    private String numAccount;
    private double amount;
    private String userIdentity;
    private List<Extract> extracts;
    private LocalDateTime registerDate;

    public void assignNumAccount(String numAccount){
        this.numAccount = numAccount;
    }

    public void deposit(double amount){
        this.amount += amount;
    }

    public void withDraw(double amount){
        this.amount -= amount;
    }
}
