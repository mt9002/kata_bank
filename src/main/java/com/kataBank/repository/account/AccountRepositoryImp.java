package com.kataBank.repository.account;

import com.kataBank.mapper.IAccountMapper;
import com.kataBank.model.AccountModel;
import com.kataBank.service.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImp implements AccountRepository {

    private final IAccountMapper accountMapper;
    private final AccountJpa accountJpa;

    public AccountRepositoryImp(IAccountMapper accountMapper, AccountJpa accountJpa) {
        this.accountMapper = accountMapper;
        this.accountJpa = accountJpa;
    }

    @Override
    public Account findByNumAmount(String numAccount) {
        return accountMapper.toAccount2(accountJpa.findByNumAccount(numAccount));
    }

    @Override
    public Account save(AccountModel accountModel) {
        return accountMapper.toAccount(accountJpa.save(accountModel));
    }
}
