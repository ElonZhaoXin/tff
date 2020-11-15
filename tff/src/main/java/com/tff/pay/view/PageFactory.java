package com.tff.pay.view;

import org.springframework.stereotype.Component;

@Component
public class PageFactory {

    public QrPage buildQrPage() {
        return QrPage.childBuilder()
                    .title("支付")
                    .description("收款二维码")
                    .templateName("qrpage")
                    .build();
    }
}
