package com.kataBank.service;

import com.kataBank.dto.TransactionRequest;
import com.kataBank.exception.AccountNotFoundException;
import com.kataBank.exception.InvalidNumAccountException;
import com.kataBank.exception.InvalidTransactionAmountException;
import com.kataBank.model.Account;
import com.kataBank.model.Extract;
import com.kataBank.repository.AccountRepository;
import com.kataBank.repository.ExtractRepository;
import org.springframework.stereotype.Service;

import static com.kataBank.rules.TransactionRules.MIN_TRANSACTION_AMOUNT;


@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final ExtractRepository extractRepository;

    public TransactionServiceImpl(AccountRepository accountRepository,
                                  ExtractRepository extractRepository) {
        this.accountRepository = accountRepository;
        this.extractRepository = extractRepository;
    }

    @Override
    public void transaction(TransactionRequest transactionReq) {
        switch (transactionReq.type()) {
            case DEPOSIT -> deposit(transactionReq);
            case WITHDRAW -> withDraw(transactionReq);
            default -> throw new IllegalArgumentException("Invalid transaction type");
        }
    }

    private void deposit(TransactionRequest transactionReq) {
        validateTransactionRequest(transactionReq);

        Account account = accountRepository.findByNumAccount(transactionReq.numAccount());
        validateAccount(account);

        account.deposit(transactionReq.amount());
        accountRepository.save(account);

        extractRepository.save(
                new Extract(
                        transactionReq.amount(),
                        account.getAmount(),
                        account
                ));
    }

    private void withDraw(TransactionRequest transactionReq) {
        validateTransactionRequest(transactionReq);

        Account account = accountRepository.findByNumAccount(transactionReq.numAccount());
        validateAccount(account);

        account.withDraw(transactionReq.amount());
        accountRepository.save(account);

        extractRepository.save(
                new Extract(
                        transactionReq.amount() * (-1),
                        account.getAmount(),
                        account));
    }

    private void validateTransactionRequest(TransactionRequest transactionRequest) {
        if (transactionRequest.amount() <= MIN_TRANSACTION_AMOUNT) {
            throw new InvalidTransactionAmountException("amount less than " + MIN_TRANSACTION_AMOUNT);
        }
        if (transactionRequest.numAccount() == null || transactionRequest.numAccount().isBlank()) {
            throw new InvalidNumAccountException("empty account number.");
        }
    }

    private void validateAccount(Account transaction) {
        if (transaction == null) {
            throw new AccountNotFoundException("there is no account");
        }
    }
}
