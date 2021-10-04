package com.operations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TakeMoneyResponse {

    @JsonProperty("result")
    private int result;

    @JsonProperty("errorText")
    private String errorText;

    public TakeMoneyResponse(int result, String errorText) {
        this.result = result;
        this.errorText = errorText;

    }

    public TakeMoneyResponse(){}

}
