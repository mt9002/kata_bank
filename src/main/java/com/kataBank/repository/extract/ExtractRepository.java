package com.kataBank.repository.extract;

import com.kataBank.model.ExtractModel;
import org.springframework.stereotype.Repository;

@Repository
public class ExtractRepository implements IExtractRepository{

    private final ExtractJpa extractJpa;

    public ExtractRepository(ExtractJpa extractJpa) {
        this.extractJpa = extractJpa;
    }

    @Override
    public void save(ExtractModel extractModel) {
        System.out.println("entrando a repo");
        System.out.println(extractModel.getAmount());
        extractJpa.save(extractModel);
    }

    @Override
    public void update(ExtractModel extractModel) {
        extractJpa.save(extractModel);
    }
}
