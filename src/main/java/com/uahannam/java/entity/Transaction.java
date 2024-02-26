package com.uahannam.java.entity;

import com.uahannam.java.enums.TransactionStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "Transaction")
public class Transaction extends SysTimeCol{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_id")
    private Long paymentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus status;

    private Integer amount;

    private String description;
}