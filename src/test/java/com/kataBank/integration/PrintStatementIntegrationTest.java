package com.kataBank.integration;

import com.kataBank.fixture.AccountFixture;
import com.kataBank.integration.config.IntegrationTestBase;
import com.kataBank.repository.AccountRepository;
import com.kataBank.repository.ExtractRepository;
import com.kataBank.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PrintStatementIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ExtractRepository extractRepository;

    @Test
    void printStatementTest() throws Exception {

        // Arrange
        Account account = AccountFixture.accountCreate();
        account= accountRepository.save(account);
        extractRepository.findByAccountIdOrderByRegisterDateDesc(account.getId());
        // Act and assert
        mockMvc.perform(get("/printStatement/findStatement")
                        .param("numAccount", account.getNumAccount()))
                .andExpect(content().contentType(MediaType.APPLICATION_PDF))
                .andExpect(header().string(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=statement_" + account.getNumAccount() + ".pdf"
                ))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_PDF));

    }

}
