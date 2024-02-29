package com.uahannam.java.entity;

import com.uahannam.java.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends SysTimeCol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    private Integer amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @LastModifiedDate
    private LocalDateTime mod_date;

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
