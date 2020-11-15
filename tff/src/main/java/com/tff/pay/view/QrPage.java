package com.tff.pay.view;

import lombok.Builder;
import lombok.Data;

/**
 * 二维码收款页面
 */
@Data
public class QrPage extends Page {
    /**
     * 支付二维码 base64
     */
    private String qrCodeBase64;

    @Builder(builderMethodName = "childBuilder")
    public QrPage(String title,
                  String description,
                  String templateName,
                  String qrCodeBase64) {
        super(title, description, templateName);
        this.qrCodeBase64 = qrCodeBase64;
    }
}
