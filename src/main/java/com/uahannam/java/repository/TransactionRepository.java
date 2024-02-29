package com.uahannam.java.repository;

import com.uahannam.java.dto.query.TransactionDto;
import com.uahannam.java.entity.Transaction;
import com.uahannam.java.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select new com.uahannam.java.dto.query.TransactionDto(pt.id, pt.status, pt.amount, pt.reg_date) " +
            "from Transaction pt " +
            "where pt.status = :status")
    List<TransactionDto> findDtoByStatus(PaymentStatus status);

    @Query("select new com.uahannam.java.dto.query.TransactionDto(pt.id, pt.status, pt.amount, pt.reg_date) " +
            "from Transaction pt " +
            "where pt.paymentId = :paymentId")
    List<TransactionDto> findDtoByMemberId(Long paymentId);

    @Query("select new com.uahannam.java.dto.query.TransactionDto(pt.id, pt.status, pt.amount, pt.reg_date) " +
            "from Transaction pt " +
            "where pt.reg_date = :date")
    List<TransactionDto> findDtoByReg_date(LocalDateTime date);

    @Query("select new com.uahannam.java.dto.query.TransactionDto(pt.id, pt.status, pt.amount, pt.reg_date) " +
            "from Transaction pt " +
            "where pt.amount = :amount")
    List<TransactionDto> findDtoByAmount(Integer amount);

}
