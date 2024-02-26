package com.uahannam.java.service;

import com.uahannam.java.dto.query.PaymentDto;
import com.uahannam.java.dto.response.HistoryRespDto;
import com.uahannam.java.entity.Payment;
import com.uahannam.java.enums.PaymentStatus;
import com.uahannam.java.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    // 충전
    public PaymentDto pointCharge(Long userId, Integer amount) {
        Payment.PaymentBuilder amount1 = Payment.builder()
                .memberId(userId)
                .status(PaymentStatus.PENDING)
                .amount(amount);

        // 유저 서비스에서 포인트 충전 로직 필요


    }

    // 결제
    public PaymentDto processPayment(Long userId, Integer amount) {

    }

    public HistoryRespDto fetchPaymentHistory(Long userId) {

    }

    public HistoryRespDto fetchTransactionHistory(Long userId) {

    }
}