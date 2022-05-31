package com.example.softwaredevelopment.ch3;


import com.example.softwaredevelopment.ch2.BankDTO;

import java.util.List;

public interface BankStatementParser {

    BankDTO parseFrom(String line);

    List<BankDTO> parseLinesFrom(List<String> line);

}
