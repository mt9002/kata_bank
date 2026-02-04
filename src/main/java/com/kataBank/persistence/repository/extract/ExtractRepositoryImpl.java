package com.kataBank.persistence.repository.extract;

import com.kataBank.persistence.mapper.ExtractMapper;
import com.kataBank.model.Extract;
import com.kataBank.repository.ExtractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExtractRepositoryImpl implements ExtractRepository {

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
