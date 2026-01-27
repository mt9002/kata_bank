package com.kataBank.repository.mapper;

import com.kataBank.model.AccountModel;
import com.kataBank.model.ExtractModel;
import com.kataBank.service.Extract;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExtractMapper {

    public List<Extract> toListExtract(List<ExtractModel> extractModelList){
        return extractModelList.stream().map(this::toExtract).toList();
    }

    private Extract toExtract(ExtractModel model) {
        Extract extract = new Extract();
        extract.setAmount(model.getAmount());
        extract.setBalance(model.getBalance());
        extract.setRegisterDate(model.getRegisterDate());
        return extract;
    }

    public ExtractModel toExtractModel(Extract extract) {

        ExtractModel extractModel = new ExtractModel();
        AccountModel accountModel = new AccountModel();

        accountModel.setId(extract.getAccount().getId());

        extractModel.setAccount(accountModel);
        extractModel.setAmount(extract.getAmount());
        extractModel.setBalance(extract.getBalance());
        extractModel.setRegisterDate(extract.getRegisterDate());
        return extractModel;
    }
}
