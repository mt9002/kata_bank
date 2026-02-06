package com.kataBank.controller;

import com.kataBank.dto.AccountModelAssembler;
import com.kataBank.dto.AccountRequest;
import com.kataBank.model.Account;
import com.kataBank.service.AccountService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final AccountModelAssembler assembler;

    public AccountController(AccountService accountService, AccountModelAssembler assembler) {
        this.accountService = accountService;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Account>> createAccount(@RequestBody AccountRequest accountReq){
        Account account = accountService.createAccount(accountReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(account));
    }

    @GetMapping("/{numAccount}")
    public ResponseEntity<EntityModel<Account>> findByAccount(
            @PathVariable String numAccount){
        Account account = accountService.findByAccount(numAccount);
        return ResponseEntity.status(HttpStatus.OK).body(assembler.toModel(account));
    }
}
