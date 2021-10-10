package com.operations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransferMoneyResponse {

    @JsonProperty("result")
    private int result;

    @JsonProperty("errorText")
    private String errorText;

    public TransferMoneyResponse(int result, String errorText) {
        this.result = result;
        this.errorText = errorText;

    }

    public TransferMoneyResponse(){}
}
