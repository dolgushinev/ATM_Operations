package com.operations.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PutMoneyRequest {

    @JsonProperty(value = "user_id", required = true)
    private Long user_id;

    @JsonProperty(value = "amount", required = true)
    private BigDecimal amount;

    @JsonCreator
    public PutMoneyRequest(Long user_id, BigDecimal amount) {
        this.user_id = user_id;
        this.amount = amount;
    }

    public PutMoneyRequest() {
    }

}
