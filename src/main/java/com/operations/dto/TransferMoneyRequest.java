package com.operations.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferMoneyRequest {

    @JsonProperty(value = "user_id", required = true)
    private Long user_id;

    @JsonProperty(value = "to_user_id", required = true)
    private Long to_user_id;

    @JsonProperty(value = "amount", required = true)
    private BigDecimal amount;

    @JsonCreator
    public TransferMoneyRequest(Long user_id, Long to_user_id, BigDecimal amount) {
        this.user_id = user_id;
        this.to_user_id = to_user_id;
        this.amount = amount;
    }

    public TransferMoneyRequest() {
    }

}
