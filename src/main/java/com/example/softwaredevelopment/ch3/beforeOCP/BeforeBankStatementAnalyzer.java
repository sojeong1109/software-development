package com.example.softwaredevelopment.ch3.beforeOCP;

import com.example.softwaredevelopment.ch2.BankDTO;
import com.example.softwaredevelopment.ch3.BankStatementParser;
import com.example.softwaredevelopment.ch3.afterOCP.BankDtoIsInFebruaryAndExpensive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BeforeBankStatementAnalyzer {

    private static final String RESOURCES = "src/main/resources/";

     public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {
         final Path path = Paths.get(RESOURCES + fileName);
         final List<String> lines = Files.readAllLines(path);

         final List<BankDTO> bankDTOList = bankStatementParser.parseLinesFrom(lines);
         final BeforeBankStatementProcessor beforeBankStatementProcessor = new BeforeBankStatementProcessor(bankDTOList);
         collectSummary(beforeBankStatementProcessor);
     }

    private static void collectSummary(final BeforeBankStatementProcessor beforeBankStatementProcessor) {
        final List<BankDTO> monthList = beforeBankStatementProcessor.findDtoInMonth(Month.FEBRUARY);
        final List<BankDTO> greaterList = beforeBankStatementProcessor.findDtoGreaterThanEqual(10000);
        final List<BankDTO> greaterAndMonth = beforeBankStatementProcessor.findDtoInMonthAndGreater(Month.FEBRUARY, 10000);
    }
}
