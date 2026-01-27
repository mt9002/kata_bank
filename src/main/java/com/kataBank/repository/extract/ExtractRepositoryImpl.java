package com.kataBank.repository.extract;

import com.kataBank.repository.mapper.ExtractMapper;
import com.kataBank.service.Extract;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExtractRepositoryImpl implements ExtractRepository{

    private final ExtractJpa extractJpa;
    private final ExtractMapper extractMapper;

    public ExtractRepositoryImpl(ExtractJpa extractJpa, ExtractMapper extractMapper) {
        this.extractJpa = extractJpa;
        this.extractMapper = extractMapper;
    }

    @Override
    public void save(Extract extract) {
        extractJpa.save(extractMapper.toExtractModel(extract));
    }

    public List<Extract> findByAccountIdOrderByRegisterDateDesc(Long accountId){
        return extractMapper.toListExtract(extractJpa.findByAccountIdOrderByRegisterDateDesc(accountId));
    }
}
