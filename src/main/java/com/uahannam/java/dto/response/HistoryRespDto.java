package com.uahannam.java.dto.response;

import com.uahannam.java.dto.query.History;

import java.util.List;

public class HistoryRespDto {

    private List<History> data;

    public HistoryRespDto(List<History> data) {
        this.data = data;
    }
}
