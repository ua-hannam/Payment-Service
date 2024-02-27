package com.uahannam.java.entity;

import com.uahannam.java.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Transaction")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends SysTimeCol{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_id")
    private Long paymentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    private Integer amount;

    private String description;
}
