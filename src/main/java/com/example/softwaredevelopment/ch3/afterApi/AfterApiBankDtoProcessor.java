package com.example.softwaredevelopment.ch3.afterApi;

import com.example.softwaredevelopment.ch2.BankDTO;
import com.example.softwaredevelopment.ch3.afterOCP.BankDtoFilter;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class AfterApiBankDtoProcessor {

    private final List<BankDTO> bankDTOList;

    public AfterApiBankDtoProcessor(
        final List<BankDTO> bankDTOList
    ) {
        this.bankDTOList = bankDTOList;
    }

    public double summarizeTransactions(final BankDtoSummarizer bankDtoSummarizer) {
        double result = 0;
        for (BankDTO bankDTO : bankDTOList) {
            result = bankDtoSummarizer.summarize(result, bankDTO);
        }
        return result;
    }
    public double calculateTotalInMonth(final Month month) {
        return summarizeTransactions((acc, bankTransaction) ->
            bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
    }

    public List<BankDTO> findTransactionsGreaterThanEqual(final int amount) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
    }

    public List<BankDTO> findTransactions(final BankDtoFilter bankTransactionFilter) {
        final List<BankDTO> result = new ArrayList<>();
        for (final BankDTO bankTransaction : bankDTOList) {
            if (bankTransactionFilter.test(bankTransaction)) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

}
