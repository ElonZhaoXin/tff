package com.tff.pay;

import com.tff.pay.model.TradePrecreateReqeust;
import com.tff.pay.model.TradePrecreateResponse;
import com.tff.pay.model.TradeQueryRequest;
import com.tff.pay.model.TradeQueryResponse;

public interface PayService {
    /**
     * 创建二维码支付订单，返回二维码
     * @param reqeust
     * @return
     */
    TradePrecreateResponse tradePrecreate(TradePrecreateReqeust reqeust);

    TradeQueryResponse tradeQuery(TradeQueryRequest request);
}
