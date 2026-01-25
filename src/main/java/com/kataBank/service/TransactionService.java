package com.kataBank.service;

import com.kataBank.dto.TransactionRequest;
import com.kataBank.exception.InvalidDepositException;
import com.kataBank.exception.InvalidNumAccountException;
import com.kataBank.mapper.IAccountMapper;
import com.kataBank.repository.account.AccountRepository;
import com.kataBank.repository.extract.IExtractRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {

    private final AccountRepository accountRepository;
    private final IExtractRepository extractRepository;
    private final IAccountMapper mapper;

    public TransactionService(AccountRepository accountRepository,
                              IExtractRepository extractRepository,
                              IAccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.extractRepository = extractRepository;
        this.mapper = mapper;
    }

    public void deposit(TransactionRequest transactionRequest) {
        validAmount(transactionRequest);
        Account account = accountRepository.findByNumAmount(transactionRequest.numAccount());
        validAccount(account);
        double balanceAmount = transactionRequest.amount() + account.getAmount();

        extractRepository.save(
                mapper.toExtractModel(
                        new Extract(transactionRequest.amount(), balanceAmount, account)));

        account.setAmount(balanceAmount);
        accountRepository.save(mapper.toAccountModel(account));
    }

    public void withDraw(TransactionRequest transactionRequest) {
        validAmount(transactionRequest);
        Account account = accountRepository.findByNumAmount(transactionRequest.numAccount());
        validAccount(account);
        double balanceAmount = account.getAmount() - transactionRequest.amount();
        double withDraw = transactionRequest.amount()*(-1);
        extractRepository.save(
                mapper.toExtractModel(new Extract(withDraw, balanceAmount, account)));
        account.setAmount(balanceAmount);
        accountRepository.save(mapper.toAccountModel(account));
    }

    private void validAmount(TransactionRequest transactionRequest) {
        if (transactionRequest.amount() <= 5000) {
            throw new InvalidDepositException("amount less than 5000.");
        }
        if (transactionRequest.numAccount().isEmpty() || transactionRequest.numAccount().isBlank()) {
            throw new InvalidNumAccountException("empty account number.");
        }
    }

    private void validAccount(Account transaction){
        if (transaction == null){
            throw new InvalidDepositException("there is no account");
        }
    }
}
