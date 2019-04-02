package com.cyrj.pharmacymessage.help;

import android.text.TextUtils;

public class UrlConfig {
    public static final String DEFAULT_IP = "10.0.38.186";
    public static final String DEFAULT_PORT = "8080";
    public static final String SUFFIX = "mms";
    public static String getWebBaseUrl() {

        String ipStr = SettingPrefUtils.getIP();
        String port = SettingPrefUtils.getPort();
        if (TextUtils.isEmpty(ipStr)) {
            ipStr = UrlConfig.DEFAULT_IP;
        }
        if (TextUtils.isEmpty(port)) {
            port = UrlConfig.DEFAULT_PORT;
        }
        StringBuilder baseUrlSf = new StringBuilder("http://");
        baseUrlSf.append(ipStr).append(":").append(port).append("/").append(SUFFIX).append("/");
        return baseUrlSf.toString();
    }
    public static String getWebBaseUrl(String ipStr, String port) {
        if (TextUtils.isEmpty(ipStr)) {
            ipStr = UrlConfig.DEFAULT_IP;
        }
        if (TextUtils.isEmpty(port)) {
            port = UrlConfig.DEFAULT_PORT;
        }
        StringBuilder baseUrlSf = new StringBuilder("http://");
        baseUrlSf.append(ipStr).append(":").append(port).append("/");
        return baseUrlSf.toString();
    }

}
