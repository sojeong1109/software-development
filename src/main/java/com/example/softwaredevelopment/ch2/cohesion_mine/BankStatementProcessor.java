//package com.example.softwaredevelopment.ch2;
//
//import com.example.softwaredevelopment.ch2.BankDTO;
//
//import java.time.Month;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * BeforeBankStatementAnalyzer 에서 계산하는 부분만 빼서 만들어 응집도를 높임.
// *
// * ver1. 생성자 매개변수 없이 다 메소드로 뺌.
// */
//public class BeforeBankStatementProcessor {
//
//    /**
//     * 내역 총 합계 구하기
//     *
//     * @param bankDTOList
//     * @return
//     */
//    public double calculateTotalAmount(final List<BankDTO> bankDTOList) {
//        return bankDTOList
//            .stream()
//            .collect(Collectors.summarizingDouble(BankDTO::getAmount))
//            .getSum();
//    }
//
//    /**
//     * 특정 월 합계
//     *
//     * @param bankDTOList
//     * @param month
//     * @return
//     */
//    public double selectInMonth(final List<BankDTO> bankDTOList, final Month month) {
//        double total = 0d;
//        for (BankDTO bankDTO : bankDTOList) {
//            if (bankDTO.getDate().getMonth() == month) {
//                total += bankDTO.getAmount();
//            }
//        }
//
//        return total;
//
////        code to stream
////        return bankDTOList
////            .stream()
////            .filter(dto -> dto.getDate().getMonth() == month)
////            .collect(Collectors.toList());
//
//    }
//}
