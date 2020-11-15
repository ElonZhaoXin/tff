package com.tff.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("alipay")
@Data
public class AlipayProperties {
    private String serverUrl;
    private String appId;
    private String privateKey;
    private String format = "json";
    private String charset = "utf-8";
    private String signType = "RSA2";
    private String certPath;
    private String alipayPublicCertPath;
    private String rootCertPath;
}
