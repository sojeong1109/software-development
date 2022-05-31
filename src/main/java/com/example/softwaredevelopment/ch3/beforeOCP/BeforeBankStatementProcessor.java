package com.example.softwaredevelopment.ch3.beforeOCP;

import com.example.softwaredevelopment.ch2.BankDTO;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BeforeBankStatementProcessor {

    private final List<BankDTO> bankDTOList;

    public BeforeBankStatementProcessor(final List<BankDTO> bankDTOList) {
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

     /**
     * ch3.
     * 특정 금액 이상의 은행 거래 내역 찾기
     * @param amount
     * @return
     */
    public List<BankDTO> findDtoGreaterThanEqual(final int amount) {
        final List<BankDTO> result = new ArrayList<>();
        for (BankDTO bankDTO : bankDTOList) {
            if (bankDTO.getAmount() >= amount) {
                result.add(bankDTO);
            }
        }
        return result;
    }

    /**
     * ch3.
     * 특정 월의 입출금 내역 찾기
     * @param month
     * @return
     */
    public List<BankDTO> findDtoInMonth(final Month month) {
        final List<BankDTO> result = new ArrayList<>();
        for (BankDTO bankDTO : bankDTOList) {
            if (bankDTO.getMonth() == month) {
                result.add(bankDTO);
            }
        }
        return result;
     }

    /**
     * ch3.
     * 특정 월의 입출금 내역 찾기 && 특정 금액 이상의 은행 거래
     *
     * 아래 코드의 문제점.
     * 1. 거래 내역의 여러 속성을 조합할수록 코드가 점점 복잡
     * 2. 반복 로직과 비지니스 로직이 결합되어 분리 어려움
     * 3. 코드 반복
     *
     * 해결
     * 개방/폐쇄 원칙을 따라
     * BankDtoFilter 인터페이스를 만들기.
     * BankDto 를 인수로 받아 boolean을 반환하는 test 메서드를 포함.
     */
    public List<BankDTO> findDtoInMonthAndGreater(final Month month, final int amount) {
        final List<BankDTO> result = new ArrayList<>();
        for (BankDTO bankDTO : bankDTOList) {
            if (bankDTO.getMonth() == month && bankDTO.getAmount() >= amount) {
                result.add(bankDTO);
            }
        }
        return result;
    }
}
