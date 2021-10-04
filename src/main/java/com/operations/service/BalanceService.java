package com.operations.service;

import com.operations.repository.BalanceRepository;
import com.operations.utils.ErrorText;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final BalanceRepository balanceRepository;

    public Pair<BigDecimal, String> getBalance(Long userId) {

        if (userId <= 0) return Pair.of(BigDecimal.valueOf(-1), ErrorText.USER_ID_HAVE_TO_BE_POSITIVE.getErrorTest());

        return balanceRepository.getBalance(userId);
    }

    public Pair<Integer, String>  putMoney(Long userId, BigDecimal amount) {

        if (userId <= 0) return Pair.of(0, ErrorText.USER_ID_HAVE_TO_BE_POSITIVE.getErrorTest());
        if (amount.compareTo(BigDecimal.ZERO) <= 0) return Pair.of(0, ErrorText.AMOUNT_HAVE_TO_BE_POSITIVE.getErrorTest());

        return balanceRepository.putMoney(userId, amount);
    }

    public Pair<Integer, String>  takeMoney(Long userId, BigDecimal amount) {

        if (userId <= 0) return Pair.of(0, ErrorText.USER_ID_HAVE_TO_BE_POSITIVE.getErrorTest());
        if (amount.compareTo(BigDecimal.ZERO) <= 0) return Pair.of(0, ErrorText.AMOUNT_HAVE_TO_BE_POSITIVE.getErrorTest());

        return balanceRepository.takeMoney(userId, amount);
    }

}
