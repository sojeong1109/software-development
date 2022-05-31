package com.example.softwaredevelopment.ch3.afterApi;

import com.example.softwaredevelopment.ch2.BankDTO;

@FunctionalInterface
public interface BankDtoSummarizer {
    double summarize(double accumulator, BankDTO bankDTO);
}
