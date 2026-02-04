package com.kataBank.persistence.repository.account;

import com.kataBank.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpa extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByNumAccount(String numAccount);

    AccountEntity findByUserIdentity(String userIdentity);
}
