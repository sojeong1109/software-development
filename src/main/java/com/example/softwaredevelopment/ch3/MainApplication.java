package com.example.softwaredevelopment.ch3;

import com.example.softwaredevelopment.ch3.beforeOCP.BeforeBankStatementAnalyzer;

import java.io.IOException;

public class MainApplication {

    public static void main(final String... args) throws IOException {

        final BeforeBankStatementAnalyzer beforeBankStatementAnalyzer = new BeforeBankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        beforeBankStatementAnalyzer.analyze(args[0], bankStatementParser);
    }
}
