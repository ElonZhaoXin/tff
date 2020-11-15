package com.tff.pay.controller;

import com.tff.common.utils.qrcode.QrcodeUtils;
import com.tff.pay.PayService;
import com.tff.pay.config.AssetLoader;
import com.tff.pay.model.TradePrecreateReqeust;
import com.tff.pay.model.TradePrecreateResponse;
import com.tff.pay.view.Constant;
import com.tff.pay.view.PageFactory;
import com.tff.pay.view.QrPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.rmi.runtime.Log;

import java.util.Random;

@Controller
@RequestMapping("/resource")
@AllArgsConstructor
@Slf4j
public class PayController {

    final PayService payService;

    final PageFactory pageFactory;

    final AssetLoader assetLoader;

    @RequestMapping("/pay")
    public String share(@RequestParam(required = false) String orderId, Model model) {
        TradePrecreateReqeust reqeust = TradePrecreateReqeust.builder()
                .orderId("202011150001"+ new Random().nextInt(1000000))
                .amount("10.00")
                .title("资源").build();
        TradePrecreateResponse response = payService.tradePrecreate(reqeust);
        String qrImageStringBase64 = QrcodeUtils.createQrcodeBase64(response.getQrCode(), assetLoader.getAliLogFile());
        //构造页面属性
        QrPage qrPage = pageFactory.buildQrPage();
        qrPage.setQrCodeBase64("data:image/png;base64," + qrImageStringBase64);
        model.addAttribute(Constant.ATTRIBUTE_NAME_PAGE, qrPage);

        log.info("生成orderid：" + reqeust.getOrderId());
        return "qrpage";
    }
}
