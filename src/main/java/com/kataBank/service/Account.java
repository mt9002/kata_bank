package com.kataBank.service;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Account {
    private Long id;
    private String numAccount;
    private double amount;
    private String userIdentity;
    private List<Extract> extracts;
    private LocalDateTime registerDate;

    public Account(Builder build){
        this.id = build.id;
        this.numAccount = build.numAccount;
        this.amount = build.amount;
        this.userIdentity = build.userIdentity;;
        this.extracts = build.extracts;
        this.registerDate = build.registerDate;
    }

    public static class Builder{
        private Long id;
        private String numAccount;
        private double amount;
        private String userIdentity;
        private List<Extract> extracts;
        private LocalDateTime registerDate;

        public Builder id(Long id){
            this.id = id;
            return this;}

        public Builder amount(double amount){
            this.amount = amount;
            return this;
        }
        public Builder numAccount(String numAccount){
            this.numAccount = numAccount;
            return this;
        }

        public Builder registerDate(LocalDateTime registerDate) {
            this.registerDate = registerDate;
            return this;
        }

        public Builder extracts(List<Extract> extracts) {
            this.extracts = extracts;
            return this;
        }

        public Builder userIdentity(String userIdentity) {
            this.userIdentity = userIdentity;
            return this;
        }

        public Account build(){
            return new Account(this);
        }
    }

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
