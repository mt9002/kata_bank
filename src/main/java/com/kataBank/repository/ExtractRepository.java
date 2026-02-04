package com.kataBank.repository;

import com.kataBank.model.Extract;

import java.util.List;

public interface ExtractRepository {
    void save(Extract extract);
    List<Extract> findByAccountIdOrderByRegisterDateDesc(Long accountId);
}
