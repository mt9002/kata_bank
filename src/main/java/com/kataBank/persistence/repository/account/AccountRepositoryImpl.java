package com.kataBank.persistence.repository.account;

import com.kataBank.persistence.entity.AccountEntity;
import com.kataBank.persistence.mapper.AccountMapper;
import com.kataBank.model.Account;
import com.kataBank.repository.AccountRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountMapper accountMapper;
    private final AccountJpa accountJpa;

    public AccountRepositoryImpl(AccountMapper accountMapper, AccountJpa accountJpa) {
        this.accountMapper = accountMapper;
        this.accountJpa = accountJpa;
    }

    @Override
    public Account findByNumAccount(String numAccount) {
        return accountMapper.toAccountExisting(accountJpa.findByNumAccount(numAccount));
    }

    @Override
    public Account findByUserIdentity(String userIdentity) {
        return accountMapper.toAccountExisting(accountJpa.findByUserIdentity(userIdentity));
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountModel = accountMapper.toAccountModel(account);
        return accountMapper.toAccount(accountJpa.save(accountModel));
    }
}
