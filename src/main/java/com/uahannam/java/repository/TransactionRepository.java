package com.uahannam.java.repository;

import com.uahannam.java.dto.query.TransactionDto;
import com.uahannam.java.entity.PaymentTransaction;
import com.uahannam.java.enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<PaymentTransaction, Long> {

    @Query("select new com.uahannam.java.dto.query.TransactionDto(pt.id, pt.status, pt.amount, pt.reg_date) " +
            "from PaymentTransaction pt " +
            "where pt.status = :status")
    List<TransactionDto> findDtoByStatus(TransactionStatus status);

    @Query("select new com.uahannam.java.dto.query.TransactionDto(pt.id, pt.status, pt.amount, pt.reg_date) " +
            "from PaymentTransaction pt " +
            "where pt.paymentId = :paymentId")
    List<TransactionDto> findDtoByMemberId(Long paymentId);

    @Query("select new com.uahannam.java.dto.query.TransactionDto(pt.id, pt.status, pt.amount, pt.reg_date) " +
            "from PaymentTransaction pt " +
            "where pt.reg_date = :date")
    List<TransactionDto> findDtoByReg_date(LocalDateTime date);

    @Query("select new com.uahannam.java.dto.query.TransactionDto(pt.id, pt.status, pt.amount, pt.reg_date) " +
            "from PaymentTransaction pt " +
            "where pt.amount = :amount")
    List<TransactionDto> findDtoByAmount(Integer amount);

}
