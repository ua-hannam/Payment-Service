package com.uahannam.java.dto.query;

import com.uahannam.java.enums.TransactionStatus;

import java.time.LocalDateTime;

public class TransactionDto implements History {

    private Long id;
    private TransactionStatus status;
    private Integer amount;
    private LocalDateTime date;

    public TransactionDto(Long id, TransactionStatus status, Integer amount, LocalDateTime date) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.date = date;
    }
}
