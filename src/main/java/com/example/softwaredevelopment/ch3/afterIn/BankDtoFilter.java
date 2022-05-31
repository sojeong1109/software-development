package com.example.softwaredevelopment.ch3.afterIn;

import com.example.softwaredevelopment.ch2.BankDTO;

@FunctionalInterface
public interface BankDtoFilter {
    boolean test(BankDTO bankDTO);
}
