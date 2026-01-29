package com.kataBank.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountRequest {
    private double amount;
    private String branch;
    private String typeAccount;
    private String userIdentity;

    public AccountRequest() {}

    public AccountRequest(double amount, String branch, String typeAccount, String userIdentity) {
        this.amount = amount;
        this.branch = branch;
        this.typeAccount = typeAccount;
        this.userIdentity = userIdentity;
    }
}
