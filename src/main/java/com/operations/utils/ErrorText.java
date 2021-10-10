package com.operations.utils;

public enum ErrorText {
    NO_USER_ID("Пользователь c указанным id не обнаружен"),
    USER_ID_HAVE_TO_BE_POSITIVE("user_id должен быть > 0"),
    AMOUNT_HAVE_TO_BE_POSITIVE("Сумма должна быть > 0"),
    INSUFFICIENT_FUNDS("Недостаточно средств");

    private String errorText;

    ErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }

}
