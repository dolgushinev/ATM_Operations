package com.operations.controllers;

import com.operations.dto.*;
import com.operations.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class OperationsController {

    private final BalanceService balanceService;

    @PostMapping("/getBalance")
    @ResponseBody
    public GetBalanceResponse getBalance(@RequestBody GetBalanceRequest request) {

        Pair<BigDecimal, String> result = balanceService.getBalance(request.getUser_id());

        return new GetBalanceResponse(result.getFirst(), result.getSecond());
    }

    @PostMapping("/putMoney")
    @ResponseBody
    public PutMoneyResponse putMoney(@RequestBody PutMoneyRequest request) {

        Pair<Integer, String> result = balanceService.putMoney(request.getUser_id(), request.getAmount());

        return new PutMoneyResponse(result.getFirst(), result.getSecond());
    }

    @PostMapping("/takeMoney")
    @ResponseBody
    public TakeMoneyResponse takeMoney(@RequestBody TakeMoneyRequest request) {

        Pair<Integer, String> result = balanceService.takeMoney(request.getUser_id(), request.getAmount());

        return new TakeMoneyResponse(result.getFirst(), result.getSecond());
    }

}
