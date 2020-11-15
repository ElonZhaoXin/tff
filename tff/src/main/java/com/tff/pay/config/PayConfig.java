package com.tff.pay.config;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 支付服务配置类
 */
@Configuration
@EnableConfigurationProperties(AlipayProperties.class)
@AllArgsConstructor
public class PayConfig {

    final AlipayProperties alipayProperties;

    @Bean
    public AlipayClient alipayClient() throws Exception {
        CertAlipayRequest certParams = new CertAlipayRequest();
        BeanUtils.copyProperties(alipayProperties, certParams);
        return new DefaultAlipayClient(certParams);
    }
}
