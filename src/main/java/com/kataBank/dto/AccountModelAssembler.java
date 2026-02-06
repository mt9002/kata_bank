package com.kataBank.dto;

import com.kataBank.controller.AccountController;
import com.kataBank.controller.PrintStatementController;
import com.kataBank.controller.TransactionController;
import com.kataBank.model.Account;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>> {
    @Override
    public EntityModel<Account> toModel(Account account) {
        String numAccount = account.getNumAccount();
        return EntityModel.of(account,
                // self
                linkTo(methodOn(AccountController.class)
                        .findByAccount(numAccount))
                        .withSelfRel(),

                // print statement
                linkTo(methodOn(PrintStatementController.class)
                        .findStatement(numAccount))
                        .withRel("statement"),

                // create account
                linkTo(methodOn(AccountController.class)
                        .createAccount(null))
                        .withRel("create-account")
                        .withType("POST"),

                // transaction (deposit / withdraw)
                linkTo(methodOn(TransactionController.class)
                        .transaction(null))
                        .withRel("transaction")
                        .withType("POST")
        );
    }
}
