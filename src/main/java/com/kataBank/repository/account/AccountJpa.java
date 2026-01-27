package com.kataBank.repository.account;

import com.kataBank.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpa extends JpaRepository<AccountModel, Long> {
    AccountModel findByNumAccount(String numAccount);

    AccountModel findByUserIdentity(String userIdentity);
}
