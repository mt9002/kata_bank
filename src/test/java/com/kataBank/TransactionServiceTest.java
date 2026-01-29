package com.kataBank;

import com.kataBank.dto.TransactionRequest;
import com.kataBank.repository.account.AccountRepository;
import com.kataBank.repository.extract.ExtractRepository;
import com.kataBank.service.Account;
import com.kataBank.service.Extract;
import com.kataBank.service.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ExtractRepository extractRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void depositTest(){
        // Arrange
        TransactionRequest transactionReq = TransactionalFixture.transactionReq();
        Account account = AccountFixture.account();
        when(accountRepository.findByNumAccount(transactionReq.numAccount())).thenReturn(account);
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        doNothing().when(extractRepository).save(any(Extract.class));

        // Act
        transactionService.deposit(transactionReq);

        // Assert
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(accountRepository).findByNumAccount(transactionReq.numAccount());
        verify(extractRepository, times(1)).save(any(Extract.class));
    }

    @Test
    void withDrawTest(){
        // Arrange
        TransactionRequest transactionReq = TransactionalFixture.transactionReq();
        Account account = AccountFixture.account();
        when(accountRepository.findByNumAccount(transactionReq.numAccount())).thenReturn(account);
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        doNothing().when(extractRepository).save(any(Extract.class));

        // Act
        transactionService.deposit(transactionReq);

        // Assert
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(accountRepository).findByNumAccount(transactionReq.numAccount());
        verify(extractRepository, times(1)).save(any(Extract.class));
    }
}
