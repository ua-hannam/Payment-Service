package com.uahannam.java.dto.query;

import com.uahannam.java.enums.PaymentStatus;

public class PaymentDto implements History {
    private PaymentStatus status;
    private Integer amount;
    private Integer updatedBalance;
}
