package com.uahannam.java.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostDto {

    @NotNull(message = "사용자 id 는 필수입니다.")
    private String userId;

    @Min(value = 1, message = "충전 금액은 1 이상이어야 합니다.")
    private Integer amount;
}
