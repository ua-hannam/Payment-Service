package com.uahannam.java.controller;

import com.uahannam.java.dto.query.PaymentDto;
import com.uahannam.java.dto.request.PostDto;
import com.uahannam.java.dto.response.HistoryRespDto;
import com.uahannam.java.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment-service")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<PaymentDto> createCharge(@RequestBody @Valid PostDto postDto) {
        Long userId = Long.valueOf(postDto.getUserId());
        Integer amount = postDto.getAmount();

        PaymentDto chargeRes = paymentService.pointCharge(userId, amount);

        return ResponseEntity.ok(chargeRes);
    }

    @PostMapping("/charges")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody @Valid PostDto postDto) {
        Long userId = Long.valueOf(postDto.getUserId());
        Integer amount = postDto.getAmount();

        PaymentDto payRes = paymentService.processPayment(userId, amount);

        return ResponseEntity.ok(payRes);
    }

    @GetMapping("/members/{userId}/histories")
    public ResponseEntity<HistoryRespDto> getPaymentHistories(@PathVariable Long userId) {
        HistoryRespDto paymentHistory = paymentService.fetchPaymentHistory(userId);

        return ResponseEntity.ok(paymentHistory);
    }

    @GetMapping("/members/{userId}/transactions")
    public ResponseEntity<HistoryRespDto> getTransactions(@PathVariable Long userId) {
        HistoryRespDto transactionHistory = paymentService.fetchTransactionHistory(userId);

        return ResponseEntity.ok(transactionHistory);
    }
}
