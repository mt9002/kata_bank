package com.kataBank.dto;

import lombok.Data;

@Data
public class AccountRequest {
    private double amount;
    private String branch;
    private String typeAccount;
    private String userIdentity;
}
