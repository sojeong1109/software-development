package com.example.softwaredevelopment.ch2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

@Builder
@Getter
@Setter
/**
 * bankTransaction
 */
public class BankDTO {

    private LocalDate date;
    private double amount;
    private String description;

    public Month getMonth() {
        return date.getMonth();
    }

    // 결과 찍어내는 코드
    @Override
    public String toString() {
        return "BankDTO {" +
            "date = " + date +
            "amount = " + amount +
            "description = " + description +
            "}";
    }

    // 객체 같은지 비교
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankDTO dto = (BankDTO) o;
        return Double.compare(dto.amount, amount) == 0 && date.equals(dto.date) && description.equals(dto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, description);
    }
}
