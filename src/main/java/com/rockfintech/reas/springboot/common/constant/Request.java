package com.rockfintech.reas.springboot.common.constant;

import com.rockfintech.reas.springboot.util.config.SpringContextUtil;

public class Request {
    public static final String CHANNEL = "channel";
    public static final String LOCALE = "locale";
    public static final String DATA = "data";
    public static final String SIGNATURE = "springboot";

//    public static final String PLAT_ID = "67EA98F45487660BE05311016B0A2D15";
//    // public static final String PLAT_ID = "2EC6FDB756C54718E050007F010041F3";
//    // public static final String PLAT_ID = "30E917BA72FE6807E05311016B0AE73B";// 测试
//    // public static final String PLAT_ID = "32015409EEB52975E0538E02030A2087";// 生产
//    // public static final String PLAT_ID = "03B4B53D0887457B9B976A97BF7FF134";//贺伟
//    public static final String url = SpringContextUtil.getProperty("anxin.url");
//    public static final String HTTPS_PROXY_HOST = SpringContextUtil.getProperty("https.proxyHost");
//    public static final String HTTPS_PROXY_PORT = SpringContextUtil.getProperty("https.proxyPort");

    public static final String XINAN_IP = SpringContextUtil.getProperty("XINAN.IP");//10.0.96.106/10.0.96.107 端口20050

    public static final String XINAN_PORT = SpringContextUtil.getProperty("XINAN.PORT");

}
