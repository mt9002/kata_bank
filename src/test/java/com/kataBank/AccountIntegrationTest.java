package com.kataBank;

import com.kataBank.dto.AccountRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureMockMvc
public class AccountIntegrationTest extends IntegrationTestBase {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    public AccountIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void createAccountAndFindByAccountIntegrationTest() throws Exception {

        // Arrange
        AccountRequest request = AccountFixture.accountIntegrationReq();

        // -------- CREATE ACCOUNT --------
        String createResponseJson = mockMvc.perform(post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numAccount").isNotEmpty())
                .andExpect(jsonPath("$.amount").value(50000))
                .andExpect(jsonPath("$.userIdentity").value("1075"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String numAccount = objectMapper.readTree(createResponseJson).get("numAccount").asString();

        mockMvc.perform(get("/account/findByAccount")
                        .param("numAccount", numAccount))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numAccount").value(numAccount))
                .andExpect(jsonPath("$.amount").value(request.getAmount()))
                .andExpect(jsonPath("$.userIdentity").value(request.getUserIdentity()));
    }
}