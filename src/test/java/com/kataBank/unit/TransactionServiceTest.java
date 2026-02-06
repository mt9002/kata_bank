package com.kataBank.unit;

import com.kataBank.dto.TransactionRequest;
import com.kataBank.exception.AccountNotFoundException;
import com.kataBank.exception.InvalidNumAccountException;
import com.kataBank.exception.InvalidTransactionAmountException;
import com.kataBank.fixture.AccountFixture;
import com.kataBank.fixture.TransactionalFixture;
import com.kataBank.repository.AccountRepository;
import com.kataBank.repository.ExtractRepository;
import com.kataBank.model.Account;
import com.kataBank.model.Extract;
import com.kataBank.service.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
        transactionService.transaction(transactionReq);

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
        transactionService.transaction(transactionReq);

        // Assert
        verify(accountRepository, times(1)).save(any(Account.class));
        verify(accountRepository).findByNumAccount(transactionReq.numAccount());
        verify(extractRepository, times(1)).save(any(Extract.class));
    }

    @Test
    void transactionWhenNotExistingAccountTest(){
        TransactionRequest transactionReq = TransactionalFixture.transactionReq();
        when(accountRepository.findByNumAccount(transactionReq.numAccount())).thenReturn(null);
        assertThrows(AccountNotFoundException.class, () -> transactionService.transaction(transactionReq));
    }

    @Test
    void transactionWhenBlankNumAccountTest(){
        TransactionRequest transactionReq = TransactionalFixture.transactionReqNotNumAccount();
        assertThrows(InvalidNumAccountException.class, () -> transactionService.transaction(transactionReq));
    }

    @Test
    void transactionWhenNullNumAccountTest(){
        TransactionRequest transactionReq = TransactionalFixture.transactionReqNullNumAccount();
        assertThrows(InvalidNumAccountException.class, () -> transactionService.transaction(transactionReq));
    }

    @Test
    void transactionWhenMinAmountTest(){
        TransactionRequest transactionReq = TransactionalFixture.transactionReqMinAmount();
        assertThrows(InvalidTransactionAmountException.class, () -> transactionService.transaction(transactionReq));
    }
}
