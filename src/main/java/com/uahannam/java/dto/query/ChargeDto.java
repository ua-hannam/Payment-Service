package com.uahannam.java.dto.query;


public class ChargeDto {
    private int amount;
    private int updatedBalance;

    public ChargeDto(int amount, int updatedBalance) {
        this.amount = amount;
        this.updatedBalance = updatedBalance;
    }
}
