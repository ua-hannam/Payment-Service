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

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TransactionService transactionService;
    private final MemberServiceClient memberServiceClient;

    @Transactional
    public PaymentDto pointCharge(Long userId, Integer amount) {
        // 초기 상태를 PENDING으로 설정하고 상태 변경 리스트에 추가
        List<PaymentStatus> statusChanges = new ArrayList<>();
        statusChanges.add(PaymentStatus.PENDING);

        // Payment 객체 생성
        Payment payment = Payment.builder()
                .memberId(userId)
                .amount(amount)
                .status(PaymentStatus.PENDING)
                .build();

        try {
            // 여기서 비즈니스 로직을 처리하고 성공하면 SUCCESS 상태 추가
            payment.setStatus(PaymentStatus.SUCCESS); // 성공 상태로 설정 (아직 저장 전)
            statusChanges.add(PaymentStatus.SUCCESS);
        } catch (Exception e) {
            // 실패한 경우, 상태를 FAILED로 설정하고 상태 변경 리스트에 추가
            payment.setStatus(PaymentStatus.FAILED); // 실패 상태로 설정 (아직 저장 전)
            statusChanges.add(PaymentStatus.FAILED);
        }

        // Payment 객체 저장, 이 시점에서 Payment 객체에 ID가 할당됨
        payment = paymentRepository.save(payment);

        // 상태 변경 리스트를 순회하며 각 상태에 대한 Transaction 기록
        for (PaymentStatus status : statusChanges) {
            transactionService.saveTransaction(payment, status);
        }

        // 최종 상태를 반영한 PaymentDto 반환
        return new PaymentDto(payment.getId(), payment.getStatus(), payment.getAmount());
    }

    // 결제
    @Transactional
    public PaymentDto processPayment(Long userId, Integer amount) {
        List<PaymentStatus> statusChanges = new ArrayList<>();
        statusChanges.add(PaymentStatus.PENDING); // 초기 상태 추가

        Payment pendingPayment = Payment.builder()
                .memberId(userId)
                .amount(amount)
                .status(PaymentStatus.PENDING)
                .build();

        // 사용자의 현재 포인트 확인
        Integer leftPoints = memberServiceClient.getPoints(userId);

        if (leftPoints < amount) {
            // 요금 부족으로 인한 결제 거부
            pendingPayment.setStatus(PaymentStatus.DECLINED);
            statusChanges.add(PaymentStatus.DECLINED);
        } else {
            try {
                paymentRepository.save(pendingPayment); // PENDING 상태 저장
                pendingPayment.setStatus(PaymentStatus.SUCCESS); // 성공 처리
                statusChanges.add(PaymentStatus.SUCCESS);
            } catch (Exception e) {
                pendingPayment.setStatus(PaymentStatus.FAILED); // 실패 처리
                statusChanges.add(PaymentStatus.FAILED);
            }
        }

        // Payment 객체 저장, 이 시점에서 Payment 객체에 ID가 할당됨
        pendingPayment = paymentRepository.save(pendingPayment);

        // 상태 변경 리스트를 순회하며 각 상태에 대한 Transaction 기록
        for (PaymentStatus status : statusChanges) {
            transactionService.saveTransaction(pendingPayment, status);
        }

        // 최종 상태를 반영한 PaymentDto 반환
        return new PaymentDto(pendingPayment.getId(), pendingPayment.getStatus(), pendingPayment.getAmount());
    }

    // 결제 이력 조회
    public HistoryRespDto fetchPaymentHistory(Long userId) {
        List<PaymentDto> payments = paymentRepository.findDtoByMemberId(userId);

        return HistoryRespDto.fromPaymentDtos(payments);
    }

}