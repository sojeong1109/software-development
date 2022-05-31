package com.example.softwaredevelopment.ch3;

import com.example.softwaredevelopment.ch2.BankDTO;

import java.util.List;

/**
 * BankStatementCSVParser1에서 결합력을 높이기 위해
 * BankStatementParser 인터페이스 사용한 ver.
 */
public class BankStatementCSVParser implements BankStatementParser {

    @Override
    public BankDTO parseFrom(String line) {
        return null;
    }

    @Override
    public List<BankDTO> parseLinesFrom(List<String> line) {
        return null;
    }
}
