package com.uahannam.java.enums;

public enum TransactionStatus {
    APPROVED, // 승인
    REVERSED, // 거래 완료 후 취소
    REFUNDED, // 고객에게 돈을 다시 돌려준다
    CANCELLED // 거래 최종승인 전 중단
}
