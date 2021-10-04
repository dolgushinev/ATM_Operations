package com.operations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetBalanceResponse {

    @JsonProperty("result")
    private BigDecimal result;

    @JsonProperty("errorText")
    private String errorText;

    public GetBalanceResponse(BigDecimal result, String errorText) {
        this.result = result;
        this.errorText = errorText;
    }

    public GetBalanceResponse() {
    }
}
