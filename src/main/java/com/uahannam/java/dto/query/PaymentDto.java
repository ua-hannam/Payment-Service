package com.uahannam.java.dto.query;

import com.uahannam.java.enums.PaymentStatus;

public class PaymentDto implements History {
    private Long id;
    private PaymentStatus status;
    private Integer amount;

    public PaymentDto(Long id, PaymentStatus status, Integer amount) {
        this.id = id;
        this.status = status;
        this.amount = amount;
    }
}
