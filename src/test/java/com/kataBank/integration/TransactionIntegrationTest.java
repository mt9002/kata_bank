package com.kataBank.integration;

import com.kataBank.fixture.AccountFixture;
import com.kataBank.dto.TransactionRequest;
import com.kataBank.fixture.TransactionalFixture;
import com.kataBank.integration.config.IntegrationTestBase;
import com.kataBank.repository.AccountRepository;
import com.kataBank.model.Account;
import com.kataBank.rules.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionIntegrationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void deposit() throws Exception {
        Account account = AccountFixture.accountWithoutId();
        account.assignNumAccount("BANCOLOMBIAAHORROS000000082");
        accountRepository.save(account);

        TransactionRequest transactionReq = new TransactionRequest(account.getNumAccount(), 10000, TransactionType.DEPOSIT);

        mockMvc.perform(post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionReq)))
                .andExpect(status().isCreated());
    }

    @Test
    void withDraw() throws Exception {
        Account account = AccountFixture.accountWithoutId();
        account.assignNumAccount("BANCOLOMBIAAHORROS000000082");
        accountRepository.save(account);

        TransactionRequest transactionReq = new TransactionRequest(account.getNumAccount(), 5000, TransactionType.WITHDRAW);

        mockMvc.perform(post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionReq)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnProblemDetailWhenBusinessException() throws Exception {
        TransactionalFixture.transactionReqMinAmount();
        mockMvc.perform(post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                  "numAccount": "BANCOLOMBIAAHORROS000000082",
                  "amount": 100,
                  "type": "DEPOSIT"
                }
            """))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.title").value("business mistake "))
                .andExpect(jsonPath("$.detail").value("amount less than 1000.0"));
    }

    @Test
    void shouldReturnProblemDetailWhenException() throws Exception {

        mockMvc.perform(post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.title").value("unexpected error "))
                .andExpect(jsonPath("$.detail")
                        .value("An unexpected error occurred. Please contact support."));
    }

}
