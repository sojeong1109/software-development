package com.example.softwaredevelopment.ch3.afterIn;

import com.example.softwaredevelopment.ch2.BankDTO;
import com.example.softwaredevelopment.ch3.BankStatementParser;

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
public class AfterInBankStatementAnalyzer {

    private static final String RESOURCES = "src/main/resources/";

     public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {
         final Path path = Paths.get(RESOURCES + fileName);
         final List<String> lines = Files.readAllLines(path);

         final List<BankDTO> bankDTOList = bankStatementParser.parseLinesFrom(lines);
         final AfterInBankStatementProcessor afterInBankStatementProcessor = new AfterInBankStatementProcessor(bankDTOList);

//         before Lambda
         final List<BankDTO> dtoListBeforeLambda = afterInBankStatementProcessor.findTransactions(new BankDtoIsInFebruaryAndExpensive());

//         after Lambda
         final List<BankDTO> dtoListAfterLambda = afterInBankStatementProcessor.findTransactions(
             bankDTO -> bankDTO.getDate().getMonth() == Month.FEBRUARY && bankDTO.getAmount() >= 1000
         );
     }
}
