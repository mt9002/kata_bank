package com.kataBank.mapper;

import com.kataBank.model.AccountModel;
import com.kataBank.model.ExtractModel;
import com.kataBank.service.Account;
import com.kataBank.service.Extract;

public interface IAccountMapper {
    Account toAccount(AccountModel transactionModel);
    Account toAccount2(AccountModel transactionModel);
    AccountModel toAccountModel(Account transaction);
    ExtractModel toExtractModel(Extract extract);
}
