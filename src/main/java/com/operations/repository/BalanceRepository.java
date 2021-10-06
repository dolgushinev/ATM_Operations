package com.operations.repository;

import com.operations.entity.Operation;
import com.operations.utils.ErrorText;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;

import java.math.BigDecimal;
import java.util.List;

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

    @Transactional
    public Pair<Integer, String> putMoney(Long user_id, BigDecimal amount) {
        String errorText = "";

        Integer result = jdbcTemplate.update("UPDATE atm_operations.\"BALANCES\" SET balance = balance + ? WHERE USER_ID = ?", amount, user_id);

        if (result == 0) errorText = ErrorText.NO_USER_ID.getErrorTest();

        jdbcTemplate.update("INSERT INTO atm_operations.\"OPERATIONS_LIST\" (user_id, oper_type, amount, oper_date) VALUES (?, 1, ?, ?)", user_id, amount, LocalDateTime.now());

        return Pair.of(result, errorText);

    }

    @Transactional
    public Pair<Integer, String> takeMoney(Long user_id, BigDecimal amount) {
        String errorText = "";
        Integer result = 0;

        try {
            BigDecimal balance = jdbcTemplate.queryForObject("SELECT balance FROM atm_operations.\"BALANCES\" WHERE USER_ID = ?", BigDecimal.class, user_id);

            if ((balance.subtract(amount)).compareTo(BigDecimal.ZERO) < 0)
                return Pair.of(0, ErrorText.INSUFFICIENT_FUNDS.getErrorTest());
        } catch (EmptyResultDataAccessException e) {
            result = 0;
            errorText = ErrorText.NO_USER_ID.getErrorTest();
            return Pair.of(result, errorText);
        }

        result = jdbcTemplate.update("UPDATE atm_operations.\"BALANCES\" SET balance = balance - ? WHERE USER_ID = ?", amount, user_id);

        jdbcTemplate.update("INSERT INTO atm_operations.\"OPERATIONS_LIST\" (user_id, oper_type, amount, oper_date) VALUES (?, 2, ?, ?)", user_id, amount, java.time.LocalDateTime.now());

        return Pair.of(result, errorText);

    }


    public List<Operation> getOperationList(Long user_id, LocalDateTime from, LocalDateTime to) {

        List<Operation> operations;

        if (from == null || to == null) {
            operations = jdbcTemplate.query("SELECT * FROM atm_operations.\"OPERATIONS_LIST\" WHERE user_id = ?", new OperationsMapper(), user_id);
        } else {
            operations = jdbcTemplate.query("SELECT * FROM atm_operations.\"OPERATIONS_LIST\" WHERE user_id = ? and oper_date >= ? and oper_date <= ?", new OperationsMapper(), user_id, Timestamp.valueOf(from), Timestamp.valueOf(to));
        }

        return operations;
    }


    public class OperationsMapper implements RowMapper<Operation> {
        @Override
        public Operation mapRow(ResultSet rs, int i) throws SQLException {
            Operation o = new Operation(rs.getLong("id"), rs.getLong("user_id"), rs.getInt("oper_type"), rs.getBigDecimal("amount"), rs.getObject("oper_date", LocalDateTime.class));
            return o;
        }
    }

}
