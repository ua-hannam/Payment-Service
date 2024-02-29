package com.uahannam.java.service;

import com.uahannam.java.dto.query.TransactionDto;
import com.uahannam.java.dto.response.HistoryRespDto;
import com.uahannam.java.entity.Payment;
import com.uahannam.java.entity.Transaction;
import com.uahannam.java.enums.PaymentStatus;
import com.uahannam.java.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    // 상태와 Payment 객체를 받아 Transaction을 저장하는 메소드
    public void saveTransaction(Payment payment, PaymentStatus status) {
        Transaction transaction = Transaction.builder()
                .paymentId(payment.getId())
                .status(status)
                .amount(payment.getAmount())
                .description("Payment status changed to " + status)
                .build();
        transactionRepository.save(transaction);
    }

    public HistoryRespDto fetchTransactionHistory(Long userId) {
        List<TransactionDto> transactions = transactionRepository.findDtoByMemberId(userId);
        // HistoryRespDto에 맞게 Transaction 정보를 변환하여 반환
        return HistoryRespDto.fromTransactionDtos(transactions);
    }
}
