package com.example.softwaredevelopment.ch3.afterIn;

import com.example.softwaredevelopment.ch2.BankDTO;

import java.time.Month;

public class BankDtoIsInFebruaryAndExpensive implements BankDtoFilter {

    @Override
    public boolean test(BankDTO bankDTO) {
        return bankDTO.getDate().getMonth() == Month.FEBRUARY && bankDTO.getAmount() > 1000;
    }

}
