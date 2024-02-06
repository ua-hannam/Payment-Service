package com.uahannam.java.enums;

// 결제 이후의 거래 상태, 거래의 모든 기록을 의미함
public enum TransactionStatus {
    SUCCESS,
    FAILED,
    CANCELED, // 결제 취소
    DECLINED, // 결제 거부 (ex.포인트 부족)
}
