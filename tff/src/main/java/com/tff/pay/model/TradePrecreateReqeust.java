package com.tff.pay.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradePrecreateReqeust {
    /**
     * 订单号
     */
    private String orderId;

    /**
     * 订单名称
     */
    private String title;

    /**
     * 金额
     */
    private String amount;

}
