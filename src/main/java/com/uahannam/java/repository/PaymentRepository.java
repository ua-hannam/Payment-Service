package com.uahannam.java.repository;

import com.uahannam.java.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStatus(String status);

    List<Payment> findByMemberId(Long userId);

    List<Payment> findByReg_date(LocalDateTime date);

    List<Payment> findByMod_date(LocalDateTime date);

    List<Payment> findByAmount(Integer amount);
}
