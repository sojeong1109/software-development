package com.example.softwaredevelopment.ch3.afterIn;

import com.example.softwaredevelopment.ch2.BankDTO;
import jdk.jfr.Category;

import java.time.Month;
import java.util.List;

public interface AfterInBankDtoProcessor {

    double calculateTotalAmount();
    double calculateTotalInMonth(Month month);
    double calculateTotalInJanuary();
    double calculateAverageAmount();
    double calculateAverageAmountForCategory(Category category);
    List<BankDTO> findTransactions(BankDtoFilter bankDtoFilter);

}
