package com.uahannam.java.entity;

import com.uahannam.java.enums.TransactionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payement_Transaction")
public class PaymentTransaction {

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

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
