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
}
