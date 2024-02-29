package com.uahannam.java.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("MemberService")
public interface MemberServiceClient {

    @GetMapping("/points/{userId}")
    Integer getPoints(@PathVariable Long userId);
}