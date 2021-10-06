package com.operations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.operations.entity.Operation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetOperationListResponse {

    @JsonProperty("operations")
    private List<ShortOperation> operations;

    public GetOperationListResponse(List<Operation> operations) {

        this.operations = new ArrayList<ShortOperation>();

        for (int i = 0; i < operations.size(); i++) {
            Operation current = operations.get(i);
            ShortOperation operation = new ShortOperation(current.getOper_type(), current.getAmount(), current.getOper_date());
            this.operations.add(operation);
        }
    }

    public GetOperationListResponse() {
    }
}

