package com.kataBank.unit;

import com.kataBank.dto.AccountRequest;
import com.kataBank.exception.AccountAlreadyExistsException;
import com.kataBank.exception.IllegalArgumentAccountException;
import com.kataBank.exception.InvalidInitialAmountException;
import com.kataBank.fixture.AccountFixture;
import com.kataBank.repository.AccountRepository;
import com.kataBank.model.Account;
import com.kataBank.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    void createAccountTest(){
        // Arrange
        AccountRequest request = AccountFixture.accountRequest();

        when(accountRepository.findByUserIdentity(request.getUserIdentity())).thenReturn(null);
        when(accountRepository.save(any(Account.class)))
                .thenReturn(AccountFixture.accountSavedWithoutNumber())
                .thenReturn(AccountFixture.accountSavedWithNumber());

        // Act
        Account result = accountService.createAccount(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getNumAccount()).isNotBlank();
        assertThat(result.getNumAccount()).startsWith("BANCOLOMBIAAHORROS");
        assertThat(result.getAmount()).isEqualTo(50000);

        verify(accountRepository, times(2)).save(any(Account.class));
        verify(accountRepository).findByUserIdentity("1075");
    }

    @Test
    void findByAccountTest(){
        // Arrange
        String numAccount = "";
        Account account = AccountFixture.account();
        when(accountRepository.findByNumAccount(numAccount)).thenReturn(account);
        // Act
        Account result = accountService.findByAccount(numAccount);
        //assert

        assertThat(result.getAmount()).isEqualTo(account.getAmount());
        assertThat(result.getNumAccount()).isEqualTo(account.getNumAccount());

        verify(accountRepository).findByNumAccount(numAccount);
    }

    @Test
    void createWhenRequestIsInvalidBlankTest(){
        AccountRequest request = AccountFixture.accountRequestInvalid();
        assertThrows(IllegalArgumentAccountException.class, () -> accountService.createAccount(request));
    }

    @Test
    void createWhenRequestIsInvalidNullTest(){
        AccountRequest request = AccountFixture.accountRequestInvalidNull();
        assertThrows(IllegalArgumentAccountException.class, () -> accountService.createAccount(request));
    }

    @Test
    void createWhenExistingAccountTest(){
        AccountRequest request = AccountFixture.accountRequest();
        when(accountRepository.findByUserIdentity(request.getUserIdentity())).thenReturn(AccountFixture.account());
        assertThrows(AccountAlreadyExistsException.class, () -> accountService.createAccount(request));
    }

    @Test
    void createWhenNotExistingAccountTest(){
        String numAccount = "0000089";
        when(accountRepository.findByNumAccount(any())).thenReturn(null);
        assertThrows(AccountAlreadyExistsException.class, () -> accountService.findByAccount(numAccount));
    }

    @Test
    void createWhenMinInitialAmount(){
        // Arrange
        AccountRequest request = AccountFixture.accountMinInitialValue();
        // Act and assert
        assertThrows(InvalidInitialAmountException.class, () -> accountService.createAccount(request));
    }

    @Test
    void createWhenMaxInitialAmount(){
// Arrange
        AccountRequest request = AccountFixture.accountMaxInitialValue();
        // Act and assert
        assertThrows(InvalidInitialAmountException.class, () -> accountService.createAccount(request));
    }

}
