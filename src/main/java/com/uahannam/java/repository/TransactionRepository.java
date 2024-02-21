package com.uahannam.java.repository;

import com.uahannam.java.entity.PaymentTransaction;
import com.uahannam.java.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<PaymentTransaction, Long> {
    List<PaymentTransaction> findByStatus(TransactionStatus status);

    List<PaymentTransaction> findByMemberId(Long userId);

    List<PaymentTransaction> findByReg_date(LocalDateTime date);

    List<PaymentTransaction> findByAmount(Integer amount);

}
