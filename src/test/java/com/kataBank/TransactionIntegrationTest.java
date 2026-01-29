package com.kataBank;

import com.kataBank.dto.TransactionRequest;
import com.kataBank.repository.account.AccountRepository;
import com.kataBank.service.Account;
import org.junit.jupiter.api.Test;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureMockMvc
public class TransactionIntegrationTest extends IntegrationTestBase {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final AccountRepository accountRepository;

    public TransactionIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, AccountRepository accountRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.accountRepository = accountRepository;
    }

    @Test
    void deposit() throws Exception {
        Account account = AccountFixture.accountWithoutId();
        account.assignNumAccount("BANCOLOMBIAAHORROS000000082");
        accountRepository.save(account);

        TransactionRequest transactionReq = new TransactionRequest(account.getNumAccount(), 10000);

        mockMvc.perform(post("/transaction/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionReq)))
                .andExpect(status().isNoContent());
    }
}
