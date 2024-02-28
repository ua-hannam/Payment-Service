package com.uahannam.java.dto.query;


import com.uahannam.java.enums.PaymentStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionDto implements History {

    private Long id;
    private PaymentStatus status;
    private Integer amount;
    private LocalDateTime date;

    public TransactionDto(Long id, PaymentStatus status, Integer amount, LocalDateTime date) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.date = date;
    }
}
