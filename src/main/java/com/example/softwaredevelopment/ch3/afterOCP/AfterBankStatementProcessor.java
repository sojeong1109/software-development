package com.example.softwaredevelopment.ch3.afterOCP;

import com.example.softwaredevelopment.ch2.BankDTO;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AfterBankStatementProcessor {

    private final List<BankDTO> bankDTOList;

    public AfterBankStatementProcessor(final List<BankDTO> bankDTOList) {
        this.bankDTOList = bankDTOList;
    }

    /**
     * 내역 총 합계 구하기
     *
     * @return
     */
    public double calculateTotalAmount() {
        return bankDTOList
            .stream()
            .collect(Collectors.summarizingDouble(BankDTO::getAmount))
            .getSum();
    }

    /**
     * 특정 월 합계
     *
     * @param month
     * @return
     */
    public double selectInMonth(final Month month) {

        double total = 0d;
        for (BankDTO bankDTO : bankDTOList) {
            if (bankDTO.getDate().getMonth() == month) {
                total += bankDTO.getAmount();
            }
        }

        return total;
    }

    /**
     * 항목별 합계
     * @param category
     * @return
     */
    public double calculateTotalForCategory(final String category) {
        double total = 0d;
        for (BankDTO bankDTO : bankDTOList) {
            if (bankDTO.getDescription().equals(category)) {
                total += bankDTO.getAmount();
            }
        }

        return total;
    }

    public List<BankDTO> findTransactions(final BankDtoFilter bankDtoFilter) {
        final  List<BankDTO> result = new ArrayList<>();
        for (BankDTO bankDTO : bankDTOList) {
            if (bankDtoFilter.test(bankDTO)) {
                result.add(bankDTO);
            }
        }
        return result;
    }
}
