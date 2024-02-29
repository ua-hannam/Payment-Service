package com.uahannam.java.dto.response;

import com.uahannam.java.dto.query.History;
import com.uahannam.java.dto.query.PaymentDto;
import com.uahannam.java.dto.query.TransactionDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HistoryRespDto {

    private List<History> histories;

    private HistoryRespDto(List<? extends History> histories) {
        this.histories = new ArrayList<>(histories);
    }

    public static HistoryRespDto fromPaymentDtos(List<PaymentDto> paymentDtos) {
        return new HistoryRespDto(paymentDtos);
    }

    public static HistoryRespDto fromTransactionDtos(List<TransactionDto> transactionDtos) {
        return new HistoryRespDto(transactionDtos);
    }
}
