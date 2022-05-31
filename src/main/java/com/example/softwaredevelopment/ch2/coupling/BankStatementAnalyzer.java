package com.example.softwaredevelopment.ch2.coupling;

import com.example.softwaredevelopment.ch2.BankDTO;
import com.example.softwaredevelopment.ch2.cohesion.BankStatementProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

/**
 *
 * BankStatementParser 인터페이스로 만들어 사용.
 *
 * BeforeBankStatementAnalyzer 와 BankStatementCSVParser 의 결합 제거거
 *
 */
public class BankStatementAnalyzer {

    private static final String RESOURCES = "src/main/resources/";

     public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException {
         final Path path = Paths.get(RESOURCES + fileName);
         final List<String> lines = Files.readAllLines(path);

         final List<BankDTO> bankDTOList = bankStatementParser.parseLinesFrom(lines);
         final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankDTOList);
         collectSummary(bankStatementProcessor);
     }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("총 계 : " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("1월 총계 : " +  bankStatementProcessor.selectInMonth(Month.JANUARY));
        System.out.println("Salary 항목 총계 : " +  bankStatementProcessor.calculateTotalForCategory("Salary"));
    }
}
