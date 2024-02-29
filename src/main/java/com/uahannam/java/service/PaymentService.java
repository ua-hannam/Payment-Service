package com.uahannam.java.service;

import com.uahannam.java.client.MemberServiceClient;
import com.uahannam.java.dto.query.PaymentDto;
import com.uahannam.java.dto.response.HistoryRespDto;
import com.uahannam.java.entity.Payment;
import com.uahannam.java.enums.PaymentStatus;
import com.uahannam.java.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TransactionService transactionService;
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
        transactionService.saveTransaction(pendingPayment);

        try {
            paymentRepository.save(pendingPayment);
            pendingPayment.setStatus(PaymentStatus.SUCCESS);
            transactionService.saveTransaction(pendingPayment);
        } catch (Exception e) {
            pendingPayment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(pendingPayment);
            transactionService.saveTransaction(pendingPayment);
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
            transactionService.saveTransaction(pendingPayment);
        } catch (Exception e) {
            pendingPayment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(pendingPayment);
            transactionService.saveTransaction(pendingPayment);
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

}