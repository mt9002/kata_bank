package com.kataBank.repository.extract;

import com.kataBank.service.Extract;

import java.util.List;

public interface ExtractRepository {
    void save(Extract extract);
    List<Extract> findByAccountIdOrderByRegisterDateDesc(Long accountId);
}
