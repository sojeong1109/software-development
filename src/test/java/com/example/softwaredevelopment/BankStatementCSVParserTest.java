package com.example.softwaredevelopment;

import com.example.softwaredevelopment.ch2.BankDTO;
import com.example.softwaredevelopment.ch2.coupling.BankStatementCSVParser;
import com.example.softwaredevelopment.ch2.coupling.BankStatementParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

/**
 * 테스트 코드
 *
 * 1. 테스트 콘텍스트 설정 (예제에서는 파싱할 행을 설정)
 * 2. 동작을 실행 (예제에서는 입력행을 파싱한다)
 * 4. 예상된 결과 를 어서션으로 지정 (예제에서는 날짜, 금액 설명이 제대로 파싱 되었는지 확인)
 */
public class BankStatementCSVParserTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() {
//        Assertions.fail("Not yet implementd");

        final String line = "2017-01-30,-50,Tesco";

        final BankDTO result = statementParser.parseFrom(line);
        final BankDTO expected = BankDTO
            .builder()
            .date(LocalDate.of(2017, Month.JANUARY, 30))
            .amount(-50)
            .description("Tesco")
            .build();
        final double tolerance = 0.0d;


        Assertions.assertEquals(expected.getDate(), result.getDate());
        Assertions.assertEquals(expected.getAmount(), result.getAmount());
        Assertions.assertEquals(expected.getDescription(), result.getDescription());
    }
}
