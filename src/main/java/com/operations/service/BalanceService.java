package com.operations.service;

import com.operations.entity.Operation;
import com.operations.repository.BalanceRepository;
import com.operations.utils.ErrorText;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final BalanceRepository balanceRepository;

    public Pair<BigDecimal, String> getBalance(Long userId) {

        if (userId <= 0) return Pair.of(BigDecimal.valueOf(-1), ErrorText.USER_ID_HAVE_TO_BE_POSITIVE.getErrorText());

        return balanceRepository.getBalance(userId);
    }

    public Pair<Integer, String>  putMoney(Long userId, BigDecimal amount) {

        if (userId <= 0) return Pair.of(0, ErrorText.USER_ID_HAVE_TO_BE_POSITIVE.getErrorText());
        if (amount.compareTo(BigDecimal.ZERO) <= 0) return Pair.of(0, ErrorText.AMOUNT_HAVE_TO_BE_POSITIVE.getErrorText());

        return balanceRepository.putMoney(userId, amount);
    }

    public Pair<Integer, String>  takeMoney(Long userId, BigDecimal amount) {

        if (userId <= 0) return Pair.of(0, ErrorText.USER_ID_HAVE_TO_BE_POSITIVE.getErrorText());
        if (amount.compareTo(BigDecimal.ZERO) <= 0) return Pair.of(0, ErrorText.AMOUNT_HAVE_TO_BE_POSITIVE.getErrorText());

        return balanceRepository.takeMoney(userId, amount);
    }

    public Pair<Integer, String> transferMoney(Long userId, Long toUserId, BigDecimal amount) {

        if (userId <= 0 || toUserId <=0) return Pair.of(0, ErrorText.USER_ID_HAVE_TO_BE_POSITIVE.getErrorText());

        if (amount.compareTo(BigDecimal.ZERO) <= 0) return Pair.of(0, ErrorText.AMOUNT_HAVE_TO_BE_POSITIVE.getErrorText());

        return balanceRepository.transferMoney(userId, toUserId, amount);
    }

    public List<Operation> getOperationList(Long user_id, LocalDateTime from, LocalDateTime to) {
        return balanceRepository.getOperationList(user_id, from, to);
    }
}
