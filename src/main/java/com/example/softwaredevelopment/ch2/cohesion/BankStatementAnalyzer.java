package com.example.softwaredevelopment.ch2.cohesion;

import com.example.softwaredevelopment.ch2.BankDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

/**
 *
 * BankTransactionAnalyzerSimple1 에서
 * Dto 에서 공통으로 만들어 놓은 코드 사용한 ver.
 *
 * BankStatementProcessor2을 사용.
 *
 */
public class BankStatementAnalyzer {

    private static final String RESOURCES = "src/main/resources/";
    private static final BankStatementCSVParser BANK_STATEMENT_CSV_PARSER = new BankStatementCSVParser();

    public static void main(final String... args) throws IOException {

        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        final List<BankDTO> bankDTOList = BANK_STATEMENT_CSV_PARSER.parseLinesFromCSV(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankDTOList);

    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
        System.out.println("총 계 : " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("1월 총계 : " +  bankStatementProcessor.selectInMonth(Month.JANUARY));
        System.out.println("Salary 항목 총계 : " +  bankStatementProcessor.calculateTotalForCategory("Salary"));
    }
}
