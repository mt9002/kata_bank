package com.kataBank.service;

import com.kataBank.dto.AccountRequest;
import com.kataBank.mapper.AccountMapper;
import com.kataBank.repository.account.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public void createAccount(AccountRequest accountRequest) {

        String branch = accountRequest.getBranch().toUpperCase();
        String typeAccount = accountRequest.getTypeAccount().toUpperCase();
        System.out.println("entrando a createAccount");
        Account account = accountRepository.save(
                accountMapper.toAccountModel(new Account(accountRequest.getAmount())));
        System.out.println(account.getId());
        System.out.println(generateAccountNumber(branch, typeAccount, account.getId()));
        account.setNumAccount(generateAccountNumber(branch, typeAccount, account.getId()));
        accountRepository.save(accountMapper.toAccountModel(account));
    }

    public Account findByAccount(String numAccount) {
        return accountRepository.findByNumAmount(numAccount);
    }
    public void updateAmount(){

    }

    public String generateAccountNumber(String branch, String typeAccount, long sequence) {
        String numericPart = String.format("%08d", sequence);
        int checkDigit = calculateLuhn(numericPart);
        return branch + typeAccount + numericPart + checkDigit;
    }

    private int calculateLuhn(String number) {
        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(number.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }
        return (10 - (sum % 10)) % 10;
    }
}
