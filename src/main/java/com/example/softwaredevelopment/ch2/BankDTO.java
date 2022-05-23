package com.example.softwaredevelopment.ch2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Month;

@Builder
@Getter
@Setter
public class BankDTO {

    private LocalDate date;
    private double amount;
    private String description;

    public Month getMonth() {
        return date.getMonth();
    }
}
