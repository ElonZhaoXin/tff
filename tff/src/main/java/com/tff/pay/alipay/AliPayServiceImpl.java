package com.tff.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.google.gson.Gson;
import com.tff.common.exception.ServiceException;
import com.tff.pay.PayService;
import com.tff.pay.model.TradePrecreateReqeust;
import com.tff.pay.model.TradePrecreateResponse;
import com.tff.pay.model.TradeQueryRequest;
import com.tff.pay.model.TradeQueryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AliPayServiceImpl implements PayService {

    final AlipayClient alipayClient;


    @Override
    public TradePrecreateResponse tradePrecreate(TradePrecreateReqeust reqeust) {
        AlipayTradePrecreateRequest alipayTradePrecreateRequest = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setOutTradeNo(reqeust.getOrderId());
        model.setTotalAmount(reqeust.getAmount());
        model.setSubject(reqeust.getTitle());
        alipayTradePrecreateRequest.setBizModel(model);
        AlipayTradePrecreateResponse alipayTradePrecreateResponse;
        try {
            alipayTradePrecreateResponse = alipayClient.certificateExecute(alipayTradePrecreateRequest);
        } catch (AlipayApiException e) {
            throw new ServiceException("支付宝统一收单线下交易预创建异常", e);
        }
        if (!alipayTradePrecreateResponse.isSuccess()) {
            throw new ServiceException(alipayTradePrecreateResponse.getMsg());
        }
        log.info("支付宝创建二维码支付订单成功，返回报文{}", new Gson().toJson(alipayTradePrecreateResponse));
        TradePrecreateResponse response = new TradePrecreateResponse();
        response.setOutTradeNo(alipayTradePrecreateResponse.getOutTradeNo());
        response.setQrCode(alipayTradePrecreateResponse.getQrCode());
        return response;
    }

    @Override
    public TradeQueryResponse tradeQuery(TradeQueryRequest request) {
        AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(request.getOrderId());
        alipayTradeQueryRequest.setBizModel(model);
        AlipayTradeQueryResponse alipayTradeQueryResponse;
        try {
            alipayTradeQueryResponse = alipayClient.certificateExecute(alipayTradeQueryRequest);
        } catch (AlipayApiException e) {
            throw new ServiceException("支付宝统一收单线下交易查询创建异常", e);
        }
        if (!alipayTradeQueryResponse.isSuccess()) {
            throw new ServiceException(alipayTradeQueryResponse.getMsg());
        }
        TradeQueryResponse response = new TradeQueryResponse();
        response.setStatus(alipayTradeQueryResponse.getTradeStatus());
        return response;
    }
}
