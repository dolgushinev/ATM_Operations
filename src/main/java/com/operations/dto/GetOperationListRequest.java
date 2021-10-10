package com.operations.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetOperationListRequest {

    @JsonProperty(value = "user_id", required = true)
    private Long user_id;

    @JsonProperty(value = "from")
    private LocalDateTime from;

    @JsonProperty(value = "to")
    private LocalDateTime to;

    @JsonCreator
    public GetOperationListRequest(Long user_id) {
        this.user_id = user_id;
        this.from = from;
        this.to = to;
    }

    public GetOperationListRequest() {
    }

}
