package com.uahannam.java.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class PostDto extends BaseDto {

    @Min(value = 1, message = "충전 금액은 1 이상이어야 합니다.")
    private Integer amount;
}
