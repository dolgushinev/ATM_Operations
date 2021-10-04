package com.operations.repository;

import com.operations.utils.ErrorText;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@RequiredArgsConstructor
public class BalanceRepository {
    private final JdbcTemplate jdbcTemplate;

    public Pair<BigDecimal, String> getBalance(Long user_id) {
        String errorText = "";
        BigDecimal result = BigDecimal.ZERO;

        try {
            result = jdbcTemplate.queryForObject("SELECT balance FROM atm_operations.\"BALANCES\" WHERE USER_ID = ?", BigDecimal.class, user_id);
        } catch (EmptyResultDataAccessException e) {
            result = BigDecimal.valueOf(-1);
            errorText = ErrorText.NO_USER_ID.getErrorTest();
        }

        return Pair.of(result, errorText);

    }

    public Pair<Integer, String> putMoney(Long user_id, BigDecimal amount) {
        String errorText = "";

        Integer result = jdbcTemplate.update("UPDATE atm_operations.\"BALANCES\" SET balance = balance + ? WHERE USER_ID = ?", amount, user_id);

        if (result == 0) errorText = ErrorText.NO_USER_ID.getErrorTest();

        return Pair.of(result, errorText);

    }

    public Pair<Integer, String> takeMoney(Long user_id, BigDecimal amount) {
        String errorText = "";
        Integer result = 0;

        try {
            BigDecimal balance = jdbcTemplate.queryForObject("SELECT balance FROM atm_operations.\"BALANCES\" WHERE USER_ID = ?", BigDecimal.class, user_id);

            if ((balance.subtract(amount)).compareTo(BigDecimal.ZERO) < 0) return Pair.of(0, ErrorText.INSUFFICIENT_FUNDS.getErrorTest());
        } catch (EmptyResultDataAccessException e) {
            result = 0;
            errorText = ErrorText.NO_USER_ID.getErrorTest();
            return Pair.of(result, errorText);
        }

        result = jdbcTemplate.update("UPDATE atm_operations.\"BALANCES\" SET balance = balance - ? WHERE USER_ID = ?", amount, user_id);

        return Pair.of(result, errorText);

    }

}
