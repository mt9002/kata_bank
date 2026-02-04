package com.kataBank.unit;

import com.kataBank.fixture.AccountFixture;
import com.kataBank.fixture.TransactionalFixture;
import com.kataBank.repository.AccountRepository;
import com.kataBank.repository.ExtractRepository;
import com.kataBank.model.Account;
import com.kataBank.service.PrintStatementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrintStatementTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ExtractRepository extractRepository;

    @InjectMocks
    private PrintStatementService printStatementService;

    @Test
    void printStatementExtractTest(){
        // Arrange
        Account account = AccountFixture.account();
        when(accountRepository.findByNumAccount(account.getNumAccount())).thenReturn(account);
        when(extractRepository.findByAccountIdOrderByRegisterDateDesc(account.getId())).thenReturn(TransactionalFixture.extractList());

        // Act
        byte[] pdfBytes = printStatementService.printStatementExtract(account.getNumAccount());

        //assert
        assertThat(pdfBytes).isNotNull();
        assertThat(pdfBytes.length).isGreaterThan(0);
    }
}