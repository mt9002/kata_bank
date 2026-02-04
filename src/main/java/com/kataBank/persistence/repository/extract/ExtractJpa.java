package com.kataBank.persistence.repository.extract;


import com.kataBank.persistence.entity.ExtractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtractJpa extends JpaRepository<ExtractEntity, Long>{
    List<ExtractEntity> findByAccountIdOrderByRegisterDateDesc(Long accountId);
}
