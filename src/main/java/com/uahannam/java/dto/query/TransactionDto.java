package com.uahannam.java.dto.query;

import com.uahannam.java.enums.TransactionStatus;

public class TransactionDto implements History {

    private String transcationId;
    private TransactionStatus type;
    private Integer amount;
    private String date;
}
