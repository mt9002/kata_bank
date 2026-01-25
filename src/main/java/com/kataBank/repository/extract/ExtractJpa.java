package com.kataBank.repository.extract;


import com.kataBank.model.ExtractModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractJpa extends JpaRepository<ExtractModel, Long>{
}
