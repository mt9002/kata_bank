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
    public ResponseEntity<byte[]> findStatement(@RequestParam(value = "numAccount") String numAccount) {

        byte[] pdf = printStatementService.printStatementExtract(numAccount);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=statement_" + numAccount + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
