package com.kataBank.service;

import com.kataBank.repository.account.AccountRepository;
import com.kataBank.repository.extract.ExtractRepository;
import org.openpdf.text.Chunk;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.*;
import org.openpdf.text.Document;
import org.openpdf.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PrintStatementService {
    private final AccountRepository accountRepository;
    private final ExtractRepository extractRepository;

    public PrintStatementService(AccountRepository accountRepository, ExtractRepository extractRepository) {
        this.accountRepository = accountRepository;
        this.extractRepository = extractRepository;
    }

    public byte[] printStatementExtract(String numAccount) {
        Account account = accountRepository.findByNumAccount(numAccount);
        List<Extract> extractList = extractRepository.findByAccountIdOrderByRegisterDateDesc(account.getId());
        return generatePDF(account, extractList);
    }

    private byte[] generatePDF(Account account , List<Extract> extractList){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();

        LineSeparator line = new LineSeparator();
        line.setLineWidth(1);
        line.setPercentage(100);
        // Encabezado
        document.add(new Paragraph("Estado de cuenta"));
        document.add(new Paragraph("Número de cuenta: " + account.getNumAccount()));
        document.add(new Paragraph("Saldo actual: " + account.getAmount()));
        document.add(new Paragraph("Fecha apertura: " + account.getRegisterDate()));
        document.add(new Chunk(line));
        document.add(new Paragraph("         "));

        PdfPTable table = new PdfPTable(3);
        table.addCell("Fecha");
        table.addCell("Monto");
        table.addCell("Balance");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Extract e: extractList){
            table.addCell(e.getRegisterDate().format(formatter));
            table.addCell(String.valueOf(e.getAmount()));
            table.addCell(String.valueOf(e.getBalance()));
        }
        document.add(table);
        document.close();
        return baos.toByteArray();
    }
}
