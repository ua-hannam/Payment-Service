package com.uahannam.java.dto.response;

import com.uahannam.java.dto.query.History;

import java.util.List;

public class HistoryRespDto {

    private List<History> histories;

    public HistoryRespDto(List<History> histories) {
        this.histories = histories;
    }
}
