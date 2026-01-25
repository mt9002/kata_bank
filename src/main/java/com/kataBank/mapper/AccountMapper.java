package com.kataBank.mapper;

import com.kataBank.model.AccountModel;
import com.kataBank.model.ExtractModel;
import com.kataBank.service.Account;
import com.kataBank.service.Extract;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper implements IAccountMapper{

    private final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountModel toAccountModel(Account account) {
        return modelMapper.map(account, AccountModel.class);
    }

    @Override
    public ExtractModel toExtractModel(Extract extract) {
        System.out.println("entrando mapper "+ extract.getAmount());
        return modelMapper.map(extract, ExtractModel.class);
    }

    @Override
    public Account toAccount(AccountModel transactionModel) {
        return modelMapper.map(transactionModel, Account.class);
    }

    @Override
    public Account toAccount2(AccountModel accountModel) {
        Account account = new Account();
        List<Extract> extractModelList = accountModel.getExtracts()
                .stream().map(e -> new Extract(
                        e.getAmount(),
                        e.getBalance(),
                        e.getRegisterDate()
                        )).toList();

        account.setNumAccount(accountModel.getNumAccount());
        account.setAmount(accountModel.getAmount());
        account.setExtracts(extractModelList);
        account.setId(accountModel.getId());
        account.setRegisterDate(accountModel.getRegisterDate());
        return account;
    }
}
