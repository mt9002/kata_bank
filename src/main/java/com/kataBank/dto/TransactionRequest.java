package com.kataBank.dto;


import com.kataBank.rules.TransactionType;

public record TransactionRequest(String numAccount, double amount, TransactionType type) {}
