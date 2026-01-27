package com.kataBank.controller;

import com.kataBank.service.PrintStatementService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/printStatement")
public class PrintStatementController {

    private final PrintStatementService printStatementService;

    public PrintStatementController(PrintStatementService printStatementService) {
        this.printStatementService = printStatementService;
    }

    @GetMapping("/findStatement")
    public ResponseEntity<byte[]> findStatement(@RequestParam(value = "numAccount") String numAccount){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline")
                .filename("statement_" + numAccount + ".pdf").build());
        return ResponseEntity.status(HttpStatus.OK).headers(headers)
                .body(printStatementService.printStatementExtract(numAccount));
    }
}
