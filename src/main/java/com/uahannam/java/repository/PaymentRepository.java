package com.uahannam.java.repository;

import com.uahannam.java.dto.query.PaymentDto;
import com.uahannam.java.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select new com.uahannam.java.dto.query.PaymentDto(p.id, p.status, p.amount) " +
            "from Payment p " +
            "where p.status = :status")
    List<PaymentDto> findDtoByStatus(String status);

    @Query("select new com.uahannam.java.dto.query.PaymentDto(p.id, p.status, p.amount) " +
            "from Payment p " +
            "where p.id = :id")
    List<PaymentDto> findDtoByMemberId(Long id);

    @Query("select new com.uahannam.java.dto.query.PaymentDto(p.id, p.status, p.amount) " +
            "from Payment p " +
            "where p.reg_date = :date")
    List<PaymentDto> findDtoByReg_date(LocalDateTime date);

    @Query("select new com.uahannam.java.dto.query.PaymentDto(p.id, p.status, p.amount) " +
            "from Payment p " +
            "where p.mod_date = :date")
    List<PaymentDto> findDtoByMod_date(LocalDateTime date);

    @Query("select new com.uahannam.java.dto.query.PaymentDto(p.id, p.status, p.amount) " +
            "from Payment p " +
            "where p.amount = :amount")
    List<PaymentDto> findDtoByAmount(Integer amount);
}
