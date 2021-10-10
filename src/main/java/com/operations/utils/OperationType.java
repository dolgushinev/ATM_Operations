package com.operations.utils;

public enum OperationType {
    PUT_MONEY(1),
    TAKE_MONEY(2),
    TRANSFER_MONEY_TO_CLIENT(3),
    TRANSFER_MONEY_FROM_CLIENT(4);

    private int operationCode;

    OperationType(int operationCode) {
        this.operationCode = operationCode;
    }

    public int getOperationCode() {
        return operationCode;
    }

}
