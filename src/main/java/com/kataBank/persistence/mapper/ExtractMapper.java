package com.kataBank.persistence.mapper;

import com.kataBank.persistence.entity.AccountEntity;
import com.kataBank.persistence.entity.ExtractEntity;
import com.kataBank.model.Extract;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExtractMapper {

    public List<Extract> toListExtract(List<ExtractEntity> extractModelList){
        return extractModelList.stream().map(this::toExtract).toList();
    }

    private Extract toExtract(ExtractEntity model) {
        Extract extract = new Extract();
        extract.setAmount(model.getAmount());
        extract.setBalance(model.getBalance());
        extract.setRegisterDate(model.getRegisterDate());
        return extract;
    }

    public ExtractEntity toExtractModel(Extract extract) {

        ExtractEntity extractModel = new ExtractEntity();
        AccountEntity accountModel = new AccountEntity();

        accountModel.setId(extract.getAccount().getId());

        extractModel.setAccount(accountModel);
        extractModel.setAmount(extract.getAmount());
        extractModel.setBalance(extract.getBalance());
        extractModel.setRegisterDate(extract.getRegisterDate());
        return extractModel;
    }
}
