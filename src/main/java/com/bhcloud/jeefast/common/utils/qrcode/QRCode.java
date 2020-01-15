package com.bhcloud.jeefast.common.utils.qrcode;

public class QRCode {
    private String content; // 内容
    private Integer qrImgSize = 128; //二维码图片大小
    private String logoPath;// logo路径
    private Integer logoImgSize; //二维码logo大小
    private Integer margin = 0; //二维码外边距
    private String charset = "UTF-8";//二维码编码类型
    private String qrImgType = "PNG"; //二维码输出文件类型
    private int qrImgColor = 0xFF000000;//二维码颜色,ARGB
    private int qrImgBgColor = 0xFFFFFFFF;//二维码背景色
    private boolean compressLogo = true;//是否压缩logo

    public QRCode() {

    }

    public QRCode(String content) {
        this.setContent(content);
    }

    public QRCode(String content, Integer qrImgSize, String logoPath) {
        this.setContent(content);
        this.setQrImgSize(qrImgSize);
        this.setLogoPath(logoPath);
    }

    public QRCode(String content, Integer qrImgSize, String logoPath, Integer logoImgSize) {
        this.setContent(content);
        this.setQrImgSize(qrImgSize);
        this.setLogoPath(logoPath);
        this.setLogoImgSize(logoImgSize);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getQrImgSize() {
        return qrImgSize;
    }

    public void setQrImgSize(Integer qrImgSize) {
        if(qrImgSize != null){
            this.qrImgSize = qrImgSize;
        }
    }

    public Integer getLogoImgSize() {
        int qrImgSize = this.getQrImgSize();
        return logoImgSize == null ? qrImgSize / 4 : logoImgSize;
    }

    public void setLogoImgSize(Integer logoImgSize) {
        this.logoImgSize = logoImgSize;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        if(margin!=null && margin>=0){
            this.margin = margin;
        }
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        if (StringUtils.isNotBlank(charset)) {
            this.charset = charset;
        }
    }

    public String getQrImgType() {
        return qrImgType;
    }

    public void setQrImgType(String qrImgType) {
        this.qrImgType = qrImgType;
    }

    public int getQrImgColor() {
        return qrImgColor;
    }

    public void setQrImgColor(int qrImgColor) {
        this.qrImgColor = qrImgColor;
    }

    public int getQrImgBgColor() {
        return qrImgBgColor;
    }

    public void setQrImgBgColor(int qrImgBgColor) {
        this.qrImgBgColor = qrImgBgColor;
    }

    public boolean isCompressLogo() {
        return compressLogo;
    }

    public void setCompressLogo(boolean compressLogo) {
        this.compressLogo = compressLogo;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}
