package com.operations.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Operation {

    private Long id;

    private Long user_id;

    private int oper_type;

    private BigDecimal amount;

    private LocalDateTime oper_date;

    public Operation(Long id, Long user_id, int oper_type, BigDecimal amount, LocalDateTime oper_date) {
        this.id = id;
        this.user_id = user_id;
        this.oper_type = oper_type;
        this.amount = amount;
        this.oper_date = oper_date;
    }
}
