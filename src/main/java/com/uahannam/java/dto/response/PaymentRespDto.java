package com.uahannam.java.dto.response;

import com.uahannam.java.dto.query.ChargeDto;

import java.util.List;

public class PaymentRespDto {
    private String status;

    private List<ChargeDto> data;

    public PaymentRespDto(String status, List<ChargeDto> data) {
        this.status = status;
        this.data = data;
    }
}