package com.tff.pay.config;

import lombok.Getter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
@Getter
public class AssetLoader {

    private File aliLogFile;

    static final String ALIPAY_LOGO_IMAGE_FILE_PATH = "static/assets/images/pay/alipay.png";


    @PostConstruct
    public void init() throws IOException {
        // load image
        aliLogFile = new ClassPathResource(ALIPAY_LOGO_IMAGE_FILE_PATH).getFile();
    }
}
