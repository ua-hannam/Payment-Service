package com.uahannam.java.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST */
    INVALID_PAYMENT_AMOUNT(HttpStatus.BAD_REQUEST, "유효하지 않은 결제 금액입니다: %s"),
    PAYMENT_METHOD_NOT_SUPPORTED(HttpStatus.BAD_REQUEST, "지원하지 않는 결제 방식입니다: %s"),
    PAYMENT_DATA_INCOMPLETE(HttpStatus.BAD_REQUEST, "결제 데이터가 불완전합니다."),
    INVALID_TRANSACTION_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 트랜잭션 ID 입니다: %s"),

    /* 401 UNAUTHORIZED */
    UNAUTHORIZED_PAYMENT_REQUEST(HttpStatus.UNAUTHORIZED, "인증되지 않은 결제 요청입니다."),
    PAYMENT_VERIFICATION_FAILED(HttpStatus.UNAUTHORIZED, "결제 인증에 실패했습니다."),

    /* 404 NOT FOUND */
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 결제 ID가 없습니다: %s"),
    TRANSACTION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 트랜잭션 ID가 없습니다: %s"),

    /* 409 CONFLICT */
    DUPLICATE_PAYMENT_REQUEST(HttpStatus.CONFLICT, "중복된 결제 요청입니다."),
    PAYMENT_ALREADY_PROCESSED(HttpStatus.CONFLICT, "이미 처리된 결제입니다.");

    private final HttpStatus httpStatus;
    private final String detail; // %s 가 포함된 detail
}