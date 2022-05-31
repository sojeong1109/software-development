//package com.example.softwaredevelopment.ch2.cohesion_mine;
//
//import com.example.softwaredevelopment.ch2.BankDTO;
//import com.example.softwaredevelopment.ch2.BeforeBankStatementProcessor;
//import com.example.softwaredevelopment.ch2.cohesion.BankStatementCSVParser;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.Month;
//import java.util.List;
//
///**
// *
// * BankTransactionAnalyzerSimple1 에서
// * Dto 에서 공통으로 만들어 놓은 코드 사용한 ver.
// *
// * BankStatementProcessor1을 사용.
// *
// */
//public class BeforeBankStatementAnalyzer {
//
//    private static final String RESOURCES = "src/main/resources/";
//
//    public static void main(final String... args) throws IOException {
//
//        // 아래 BankStatementCSVParser 로 파싱로직 하는 부분을 위임하였고, BankStatementCSVParser 로 캡슐화된 기능을 재사용해 구현한다.
//        final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();
//        // 아래 BeforeBankStatementProcessor 로 계산하는 하는 부분을 위임하였고, BeforeBankStatementProcessor 로 캡슐화된 기능을 재사용해 구현한다.
//        final BeforeBankStatementProcessor bankStatementProcessor = new BeforeBankStatementProcessor();
//
//        final String fileName = args[0];
//        final Path path = Paths.get(RESOURCES + fileName);
//        final List<String> lines = Files.readAllLines(path);
//
//        final List<BankDTO> bankDTOList = bankStatementCSVParser.parseLinesFromCSV(lines);
//
//        // task1. 수입과 지출의 총 합계 구하기
//        // BeforeBankStatementProcessor 응집도 전 : System.out.println("총 사용금액 : " + calculateTotalAmount(bankDTOList));
//        System.out.println("총 사용금액 : " + bankStatementProcessor.calculateTotalAmount(bankDTOList));
//        // task2. 달 별로 입출금 내역
//        // BeforeBankStatementProcessor 응집도 전 : System.out.println("1월 총 사용금액 : " + bankStatementProcessor.selectInMonth(bankDTOList, Month.JANUARY));
//        System.out.println("1월 총 사용금액 : " + bankStatementProcessor.selectInMonth(bankDTOList, Month.JANUARY));
//    }
//
//    // BeforeBankStatementProcessor 응집도 전 -start-
////    /**
////     * 내역 총 합계 구하기
////     * 계산하는 부분이 정적메서드로 선언 되어 있음.
////     * -> 현재 이 클래스에 정의된 계산관련 작업은 파싱이나 결과 전송과는 직접적인 관련이 없기 때문에 응집도가 떨어지는 클래스임.
////     * -> 하여 계산 연산을 별도로 하는 클래스를 추출하기.
////     *
////     * @param bankDTOList
////     * @return
////     */
////    public static double calculateTotalAmount(final List<BankDTO> bankDTOList) {
////        return bankDTOList
////            .stream()
////            .collect(Collectors.summarizingDouble(BankDTO::getAmount))
////            .getSum();
////    }
////
////    /**
////     * 특정 월에 해당하는 내역 목록 반환
////     * 계산하는 부분이 정적메서드로 선언 되어 있음.
////     * -> 현재 이 클래스에 정의된 계산관련 작업은 파싱이나 결과 전송과는 직접적인 관련이 없기 때문에 응집도가 떨어지는 클래스임.
////     * -> 하여 계산 연산을 별도로 하는 클래스를 추출하기.
////     *
////     * @param bankDTOList
////     * @param month
////     * @return
////     */
////    public static List<BankDTO> selectInMonth(final List<BankDTO> bankDTOList, final Month month) {
////        final List<BankDTO> bankListInMonth = new ArrayList<>();
////
////        for (BankDTO bankDTO : bankDTOList) {
////            if (bankDTO.getDate().getMonth() == month) {
////                bankListInMonth.add(bankDTO);
////            }
////        }
////
////        return bankListInMonth;
////    }
////    BeforeBankStatementProcessor 응집도 전 -end-
//}
