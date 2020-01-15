package com.bhcloud.jeefast.common.utils.qrcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.Validate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;


public class QRCodeUtil {
    private static final String CHARSET = "UTF-8";
    private static final String FORMAT_NAME = "PNG";

    /**
     * 创建二维码
     * @param qrCode
     * @return
     * @throws WriterException
     * @throws IOException
     */
    private static BufferedImage createImage(QRCode qrCode) throws WriterException, IOException {

        Validate.notNull(qrCode, "QRCode can't be null");

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, qrCode.getCharset());
        hints.put(EncodeHintType.MARGIN, qrCode.getMargin());
        BitMatrix bitMatrix = new MultiFormatWriter().encode(qrCode.getContent(), BarcodeFormat.QR_CODE, qrCode.getQrImgSize(), qrCode.getQrImgSize(), hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? qrCode.getQrImgColor() : qrCode.getQrImgBgColor());
            }
        }
        if (StringUtils.isNotBlank(qrCode.getLogoPath())) {
            // 画logo
            drawLogo(image, qrCode);
        }
        return image;
    }

    /**
     * 画logo
     * @param source
     * @param qrCode
     * @throws IOException
     */
    private static void drawLogo(BufferedImage source, QRCode qrCode) throws IOException {
        File file = new File(qrCode.getLogoPath());
        if (!file.exists()) {
            return;
        }
        Image src = ImageIO.read(file);
        int width = src.getWidth(null);
        int height = src.getHeight(null);

        if (qrCode.isCompressLogo()) { // 压缩LOGO
            if (width > qrCode.getLogoImgSize()) {
                width = qrCode.getLogoImgSize();
            }
            if (height > qrCode.getLogoImgSize()) {
                height = qrCode.getLogoImgSize();
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (qrCode.getQrImgSize() - width) / 2;
        int y = (qrCode.getQrImgSize() - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        //logo外边框
        //Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        //graph.setStroke(new BasicStroke(3f));
        //graph.draw(shape);
        graph.dispose();
    }

    /**
     * 生成带Logo的二维码，并输出到指定的路径
     *
     * @throws Exception
     */
    public static void encode(QRCode qrCode, String destPath) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(qrCode);
        FileUtils.createDirectory(destPath);
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }

    /**
     * 生成带Logo的二维码，并输出到指定的输出流
     *
     * @throws Exception
     */
    public static void encode(QRCode qrCode, OutputStream output) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(qrCode);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * @param file 二维码
     * @return 返回解析得到的二维码内容
     * @throws Exception String
     *                   TODO 二维码解析
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * @param path 二维码存储位置
     * @return 返回解析得到的二维码内容
     * @throws Exception String
     *                   TODO 二维码解析
     */
    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }
}