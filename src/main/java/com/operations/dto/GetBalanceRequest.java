package com.operations.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class GetBalanceRequest {

    @JsonProperty(value = "user_id", required = true)
    private Long user_id;

    @JsonCreator
    public GetBalanceRequest(Long user_id){
        this.user_id = user_id;
    }

    public GetBalanceRequest() {}

}
