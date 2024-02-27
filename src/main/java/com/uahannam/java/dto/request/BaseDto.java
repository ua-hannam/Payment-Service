package com.uahannam.java.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BaseDto {

    @NotNull(message = "사용자 id 는 필수입니다.")
    private String userId;
}
