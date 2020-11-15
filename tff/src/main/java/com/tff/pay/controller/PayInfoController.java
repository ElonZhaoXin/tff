package com.tff.pay.controller;

import com.tff.pay.PayService;
import com.tff.pay.model.TradeQueryRequest;
import com.tff.pay.model.TradeQueryResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PayInfoController {
    final PayService payService;

    @GetMapping("/order")
    public TradeQueryResponse orderStatus(@RequestParam String orderId) {
        TradeQueryRequest request = TradeQueryRequest.builder()
                .orderId(orderId).build();
        return payService.tradeQuery(request);
    }
}
