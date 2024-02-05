package com.uahannam.java.enums;

// 결제 중간중간의 상태에 대한 상태
public enum TransactionStatus {
    IN_PROGRESS,
    SUCCESS,
    FAILED,
    CANCELED, // 결제 취소
    DECLINED, // 결제 거부 (ex.포인트 부족)
}
