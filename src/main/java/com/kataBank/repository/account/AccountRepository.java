package com.kataBank.repository.account;

import com.kataBank.model.AccountModel;
import com.kataBank.service.Account;

public interface AccountRepository {
    Account findByNumAmount(String numAccount);
    Account save(AccountModel transactionModel);
}
