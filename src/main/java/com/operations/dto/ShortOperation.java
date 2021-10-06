package com.operations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ShortOperation {

    @JsonProperty("oper_date")
    private LocalDateTime oper_date;

    @JsonProperty("oper_type")
    private int oper_type;

    @JsonProperty("amount")
    private BigDecimal amount;

    public ShortOperation(int oper_type, BigDecimal amount, LocalDateTime oper_date) {
        this.oper_type = oper_type;
        this.amount = amount;
        this.oper_date = oper_date;
    }
}
