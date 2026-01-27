package com.kataBank.controller;

import com.kataBank.dto.AccountRequest;
import com.kataBank.service.Account;
import com.kataBank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest){

        return ResponseEntity.status(HttpStatus.OK).body(accountService.createAccount(accountRequest));
    }

    @GetMapping("/findByAccount")
    public ResponseEntity<Account> findByAccount(
            @RequestParam(value = "numAccount") String numAccount){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.findByAccount(numAccount));
    }
}
