package com.kataBank.dto;

import com.kataBank.controller.AccountController;
import com.kataBank.controller.PrintStatementController;
import com.kataBank.controller.TransactionController;
import com.kataBank.service.Account;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>> {
    @Override
    public EntityModel<Account> toModel(Account account) {
        return EntityModel.of(account,

                // self
                linkTo(methodOn(AccountController.class)
                        .findByAccount("bbva"))
                        .withSelfRel(),

                // self
                linkTo(methodOn(PrintStatementController.class)
                        .findStatement("bbva"))
                        .withSelfRel(),

                // create account
                linkTo(AccountController.class)
                        .slash("create")
                        .withRel("create"),

                // deposit
                linkTo(TransactionController.class)
                        .slash("deposit")
                        .withRel("deposit")
                        .withType("POST"),

                // withdraw
                linkTo(TransactionController.class)
                        .slash("withDraw")
                        .withRel("withdraw")
                        .withType("POST")
        );
    }
}
