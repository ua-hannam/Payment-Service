package com.uahannam.java.service;

import com.uahannam.java.dto.query.TransactionDto;
import com.uahannam.java.dto.response.HistoryRespDto;
import com.uahannam.java.entity.Payment;
import com.uahannam.java.entity.Transaction;
import com.uahannam.java.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Transactional
    public void saveTransaction(Payment payment) {
        Transaction transaction = Transaction.builder()
                .paymentId(payment.getId())
                .status(payment.getStatus())
                .amount(payment.getAmount())
                .description(payment.getStatus().name() + ": " + payment.getAmount())
                .build();

        transactionRepository.save(transaction);
    }

    public HistoryRespDto fetchTransactionHistory(Long userId) {
        List<TransactionDto> transactions = transactionRepository.findDtoByMemberId(userId);
        // HistoryRespDto에 맞게 Transaction 정보를 변환하여 반환
        return HistoryRespDto.fromTransactionDtos(transactions);
    }
}
