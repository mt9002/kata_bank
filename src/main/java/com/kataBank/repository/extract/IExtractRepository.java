package com.kataBank.repository.extract;

import com.kataBank.model.ExtractModel;

public interface IExtractRepository {
    void save(ExtractModel extractModel);
    void update(ExtractModel extractModel);
}
