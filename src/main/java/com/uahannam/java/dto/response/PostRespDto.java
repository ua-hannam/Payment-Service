package com.uahannam.java.dto.response;

import com.uahannam.java.dto.query.ChargeDto;

import java.util.List;

public class PostRespDto {
    private String status;

    private List<ChargeDto> data;

    public PostRespDto(String status, List<ChargeDto> data) {
        this.status = status;
        this.data = data;
    }
}