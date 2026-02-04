package com.kataBank.persistence.mapper;

import com.kataBank.persistence.entity.AccountEntity;
import com.kataBank.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    private final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountEntity toAccountModel(Account account) {
        return modelMapper.map(account, AccountEntity.class);
    }

    public Account toAccount(AccountEntity accountModel) {
        return Account.builder()
                .id(accountModel.getId())
                .amount(accountModel.getAmount())
                .numAccount(accountModel.getNumAccount())
                .userIdentity(accountModel.getUserIdentity())
                .registerDate(accountModel.getRegisterDate())
                .build();
    }

    public Account toAccountExisting(AccountEntity accountModel) {
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
