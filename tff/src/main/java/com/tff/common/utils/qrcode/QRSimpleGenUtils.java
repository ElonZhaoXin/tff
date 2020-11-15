package com.tff.common.utils.qrcode;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;

/**
 * 二维码生成工具类,简单类
 */
public class QRSimpleGenUtils {

    public static void main(String[] args) throws Exception{
        QRSimpleGenUtils.createQRImage("https://qr.alipay.com/bax09738iwx0iwgywfsv00ed",
                200,200,"F:\\isharefox\\qrcode","alipay");
    }

    private static final int black = 0xFF000000;
    private static final int white  = 0xFFFFFFFF;


    /**
     *
     * @param content url
     * @param width
     * @param height
     * @return 二维码base64 传到页面展示
     * @throws Exception
     */
    public static String createQRImageStringBase64(String content, int width, int height) throws Exception {
        BufferedImage qrImageBuffer = createQRImageBuffer(content, width, height);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(qrImageBuffer, "png", os);
        return Base64.getEncoder().encodeToString(os.toByteArray());
    }


    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? black : white);
            }
        }
        return image;
    }


    public static void writeToFile(BitMatrix matrix, String format, File file)
            throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        ImageIO.write(image, format, file);
    }

    public static void createQRImage(String content, int width, int height, String path, String fileName) throws Exception {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        if (StringUtils.isNotBlank(path)) {
            if (!path.endsWith("/")) {
                path = path + "/";
            }
        } else {
            path = "";
        }
        String suffix = "jpg";
        if (fileName.indexOf(".") <= -1) {
            fileName = fileName + "." + suffix;
        } else {
            suffix = fileName.split("[.]")[1];
        }
        fileName = path + fileName;
        File file = new File(fileName);
        writeToFile(bitMatrix, suffix, file);
    }


    public static BufferedImage createQRImageBuffer(String content, int width, int height) throws  Exception{
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = toBufferedImage(bitMatrix);
        return image;
    }


}