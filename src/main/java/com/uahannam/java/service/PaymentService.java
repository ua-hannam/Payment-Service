package com.uahannam.java.service;

import com.uahannam.java.client.MemberServiceClient;
import com.uahannam.java.dto.query.PaymentDto;
import com.uahannam.java.dto.query.TransactionDto;
import com.uahannam.java.dto.response.HistoryRespDto;
import com.uahannam.java.entity.Payment;
import com.uahannam.java.entity.Transaction;
import com.uahannam.java.enums.PaymentStatus;
import com.uahannam.java.repository.PaymentRepository;
import com.uahannam.java.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TransactionRepository transactionRepository;
    private final MemberServiceClient memberServiceClient;

    // 포인트 충전
    @Transactional
    public PaymentDto pointCharge(Long userId, Integer amount) {
        Payment pendingPayment = Payment.builder()
                .memberId(userId)
                .amount(amount)
                .status(PaymentStatus.PENDING)
                .build();
        paymentRepository.save(pendingPayment);
        saveTransaction(pendingPayment);

        try {
            paymentRepository.save(pendingPayment);
            pendingPayment.setStatus(PaymentStatus.SUCCESS);
            saveTransaction(pendingPayment);
        } catch (Exception e) {
            pendingPayment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(pendingPayment);
            saveTransaction(pendingPayment);
            throw new RuntimeException("failed to process payment ", e);
        }

        // Transaction 생성 로직은 일단 생략

        return new PaymentDto(pendingPayment.getId(),
                pendingPayment.getStatus(),
                pendingPayment.getAmount());
    }

    // 결제
    @Transactional
    public PaymentDto processPayment(Long userId, Integer amount) {
        Payment pendingPayment = Payment.builder()
                .memberId(userId)
                .amount(amount)
                .status(PaymentStatus.PENDING)
                .build();
        Integer leftPoints = memberServiceClient.getPoints(userId);

        if (leftPoints < amount) // 요금 부족
            pendingPayment.setStatus(PaymentStatus.DECLINED);

        try {
            paymentRepository.save(pendingPayment);
            pendingPayment.setStatus(PaymentStatus.SUCCESS);
            saveTransaction(pendingPayment);
        } catch (Exception e) {
            pendingPayment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(pendingPayment);
            saveTransaction(pendingPayment);
            throw new RuntimeException("failed to process payment ", e);
        }

        return new PaymentDto(pendingPayment.getId(),
                pendingPayment.getStatus(),
                pendingPayment.getAmount());
    }

    // 결제 이력 조회
    public HistoryRespDto fetchPaymentHistory(Long userId) {
        List<PaymentDto> payments = paymentRepository.findDtoByMemberId(userId);

        return HistoryRespDto.fromPaymentDtos(payments);
    }

    // 거래 이력 조회
    public HistoryRespDto fetchTransactionHistory(Long userId) {
        List<TransactionDto> transactions = transactionRepository.findDtoByMemberId(userId);
        // HistoryRespDto에 맞게 Transaction 정보를 변환하여 반환
        return HistoryRespDto.fromTransactionDtos(transactions);
    }

    private void saveTransaction(Payment payment) {
        Transaction transaction = Transaction.builder()
                .paymentId(payment.getId())
                .status(payment.getStatus())
                .amount(payment.getAmount())
                .description(payment.getStatus().name() + ": " + payment.getAmount())
                .build();

        transactionRepository.save(transaction);
    }
}