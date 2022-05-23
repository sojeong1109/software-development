package com.example.softwaredevelopment.ch2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * KISS 원칙 ( = 응용프로그램 코드를 한개의 클래스로 구현 함)
 *
 * 현 파일 고려되지 않은 부분
 * - 예외처리 (파일이 비어있는 경우 & 파싱에러 & 행 데이터 validation)
 *
 * 이 파일은 안티 패턴 임.
 * - 한개의 거대한 갓 클래스 때문에 코드 이해 어려움.
 * - 파싱하는데 파일형식을 현재 .csv 한개에만 특화되어 있음.
 * - 데이터 셀이 추가/삭제 될 경우 고려가 안되어있음.
 *
 */
public class BankTransactionAnalyzerSimple1 {

    private static final String RESOURCES = "src/main/resources/";

    public static void main(final String... args) throws IOException {

        final Path path = Paths.get(RESOURCES + args[0]);
        final List<String> lines = Files.readAllLines(path);

        /**
         * task1. 수입과 지출의 총 합계 구하기
         */
        double total = 0d;
        for (String line : lines) {
            final String[] columns = line.split(",");
            double amount = Double.parseDouble(columns[1]);
            total += amount;
        }
        System.out.println("총 사용금액 : " + total);

        /**
         * task2. 달 별로 입출금 내역
         */
        // 1월의 내역
        double total2 = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (String line : lines) {
            final String[] columns = line.split(",");
            LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if (date.getMonth() == Month.JANUARY) {
                final double amount = Double.parseDouble(columns[1]);
                total2 += amount;
            }
        }
        System.out.println("1월 총 사용금액 : " + total2);

        /**
         * task3. 지출이 가장 높은 상위 10건
         */
        /**
         * task4. 돈을 가장 많이 소비한 항목은 어떤건지
         */
    }
}
