package com.kataBank.repository.extract;


import com.kataBank.model.ExtractModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtractJpa extends JpaRepository<ExtractModel, Long>{
    List<ExtractModel> findByAccountIdOrderByRegisterDateDesc(Long accountId);
}
