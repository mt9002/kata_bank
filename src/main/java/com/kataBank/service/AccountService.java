package com.kataBank.service;

import com.kataBank.dto.AccountRequest;
import com.kataBank.exception.*;
import com.kataBank.repository.account.AccountRepository;
import org.springframework.stereotype.Service;

import static com.kataBank.rules.AccountRules.*;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(AccountRequest accountReq) {

        validateAccountRequest(accountReq);
        validateExistingAccount(accountRepository.findByUserIdentity(accountReq.getUserIdentity()));

        Account account = Account.builder()
                .amount(accountReq.getAmount())
                .userIdentity(accountReq.getUserIdentity())
                .build();

        account = accountRepository.save(account);

        String numAccount = generateAccountNumber(
                accountReq.getBranch(),
                accountReq.getTypeAccount(), account.getId());

        account.assignNumAccount(numAccount);

        return accountRepository.save(account);
    }

    public Account findByAccount(String numAccount) {
        Account account = accountRepository.findByNumAccount(numAccount);
        validateNotExistingAccount(account);
        return account;
    }

    private void validateAccountRequest(AccountRequest accountRequest) {
        illegalArgument(accountRequest.getTypeAccount(), "Type account ");
        illegalArgument(accountRequest.getBranch(), "Branch");
        illegalArgument(accountRequest.getUserIdentity(), "User identity");

        validateAmount(accountRequest);
    }

    private void illegalArgument(String argument, String message) {
        if (argument == null || argument.isBlank()) {
            throw new IllegalArgumentAccountException(message + "is required");
        }
    }

    private void validateAmount(AccountRequest accountRequest) {
        if (accountRequest.getAmount() < MIN_INITIAL_AMOUNT) {
            throw new InvalidInitialAmountException(
                    "initial amount must be at least " + MIN_INITIAL_AMOUNT);
        }
        if (accountRequest.getAmount() > MAX_INITIAL_AMOUNT) {
            throw new InvalidInitialAmountException("The initial import must be less than" + MAX_INITIAL_AMOUNT);
        }
    }

    private void validateExistingAccount(Account account) {
        if (account != null) {
            throw new AccountAlreadyExistsException("Account existing");
        }
    }

    private void validateNotExistingAccount(Account account) {
        if (account == null) {
            throw new AccountAlreadyExistsException("Account existing");
        }
    }

    private String generateAccountNumber(String branch, String typeAccount, long sequence) {
        String numericPart = String.format("%08d", sequence);
        int checkDigit = calculateLuhn(numericPart);
        return branch + typeAccount + numericPart + checkDigit;
    }

    private int calculateLuhn(String numericPart) {
        int sum = 0;
        boolean alternate = false;
        for (int i = numericPart.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(numericPart.substring(i, i + 1));
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
