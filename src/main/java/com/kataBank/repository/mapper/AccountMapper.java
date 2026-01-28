package com.kataBank.repository.mapper;

import com.kataBank.model.AccountModel;
import com.kataBank.service.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    private final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountModel toAccountModel(Account account) {
        return modelMapper.map(account, AccountModel.class);
    }

    public Account toAccount(AccountModel accountModel) {
        return Account.builder()
                .id(accountModel.getId())
                .amount(accountModel.getAmount())
                .numAccount(accountModel.getNumAccount())
                .userIdentity(accountModel.getUserIdentity())
                .registerDate(accountModel.getRegisterDate())
                .build();
    }

    public Account toAccountExisting(AccountModel accountModel) {
        if (accountModel == null) {
            return null;
        }
        return Account.builder()
                .id(accountModel.getId())
                .amount(accountModel.getAmount())
                .numAccount(accountModel.getNumAccount())
                .userIdentity(accountModel.getUserIdentity())
                .registerDate(accountModel.getRegisterDate())
                .build();
    }
}
