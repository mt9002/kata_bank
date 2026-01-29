package com.kataBank;

import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
class TestConfig {

    @Bean
    TestRestTemplate testRestTemplate() {
        return new TestRestTemplate();
    }
}
