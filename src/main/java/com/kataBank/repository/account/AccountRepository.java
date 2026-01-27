package com.kataBank.repository.account;

import com.kataBank.service.Account;

public interface AccountRepository {
    Account findByNumAccount(String numAccount);

    Account findByUserIdentity(String userIdentity);

    Account save(Account account);
}
