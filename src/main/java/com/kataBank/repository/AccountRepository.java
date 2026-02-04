package com.kataBank.repository;

import com.kataBank.model.Account;

public interface AccountRepository {
    Account findByNumAccount(String numAccount);
    Account findByUserIdentity(String userIdentity);
    Account save(Account account);
}
