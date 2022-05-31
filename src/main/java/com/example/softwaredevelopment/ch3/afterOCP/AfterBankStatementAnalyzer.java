package com.example.softwaredevelopment.ch3.afterOCP;

import com.example.softwaredevelopment.ch2.BankDTO;
import com.example.softwaredevelopment.ch3.BankStatementParser;
import com.example.softwaredevelopment.ch3.beforeOCP.BeforeBankStatementProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

/**
 *
 * 1 서비스
 *
 */
public class AfterBankStatementAnalyzer {

    private static final String RESOURCES = "src/main/resources/";

     public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {
         final Path path = Paths.get(RESOURCES + fileName);
         final List<String> lines = Files.readAllLines(path);

         final List<BankDTO> bankDTOList = bankStatementParser.parseLinesFrom(lines);
         final AfterBankStatementProcessor afterBankStatementProcessor = new AfterBankStatementProcessor(bankDTOList);

//         before Lambda
         final List<BankDTO> dtoListBeforeLambda = afterBankStatementProcessor.findTransactions(new BankDtoIsInFebruaryAndExpensive());

//         after Lambda
         final List<BankDTO> dtoListAfterLambda = afterBankStatementProcessor.findTransactions(
             bankDTO -> bankDTO.getDate().getMonth() == Month.FEBRUARY && bankDTO.getAmount() >= 1000
         );
     }
}
