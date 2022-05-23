package com.example.softwaredevelopment.ch2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * KISS 원칙을 이용해 만든 파일의
 * 갓 클래스 안티 패턴을 없애기 위해
 * csv 파싱 부분만 별도로 맨 클래스 파일.
 *
 * CSV to DTO
 */
public class BankStatementCSVParser {

    private static final DateTimeFormatter DATE_PATTERN
        = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<BankDTO> parseLinesFromCSV(final List<String> lines) {
        final List<BankDTO> bankDTOList = new ArrayList<>();

        for (String line : lines) {
            bankDTOList.add(parseFromCSV(line));
        }

        return bankDTOList;
    }

    private static BankDTO parseFromCSV(final String line) {
        final String[] columns = line.split(",");

        return BankDTO.builder()
            .date(LocalDate.parse(columns[0], DATE_PATTERN))
            .amount(Double.parseDouble(columns[1]))
            .description(columns[2])
            .build();
    }

}
