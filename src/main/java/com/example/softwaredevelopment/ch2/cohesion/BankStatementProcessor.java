package com.example.softwaredevelopment.ch2.cohesion;

import com.example.softwaredevelopment.ch2.BankDTO;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BankStatementAnalyzer 에서 계산하는 부분만 빼서 만들어 응집도를 높임.
 *
 * ver2. 생성자에 list 담음.
 */
public class BankStatementProcessor {

    private final List<BankDTO> bankDTOList;

    public BankStatementProcessor(final List<BankDTO> bankDTOList) {
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
}
