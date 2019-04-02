package com.cyrj.pharmacymessage.help;

/**
 * 类 含APP 全局配制常量
 * Created by huangy on 2015/2/12 0012.
 */
public class Config {

    public static final String TAG = "BS-PIVAS";


    /**
     * debug模式下，网络请求不作GZI压缩
     */
    public static final boolean DEBUG = true;

    //使用语音合成
    public static final boolean USE_TTS = true;

    //讯飞语音APP_ID
    public static final String APP_ID = "55adfac4";


    /**
     * ******************默认WIFI配制*****************
     */
    //如果需要安装时就提供默认WIFI设置（仅适用于动态IP），将此变量赋值为true,并将下面相关变量赋值；否则false
    public static final boolean OFFER_DEFAULT_WIFI = false;

    public static final String SSID = "Tank-Private";
    public static final String SSID_PSSWARD = "wxyl2014";
    public static final int CNN_TYPE = 0; // DHCP ,具体可查看 array.xml
    public static final int SECRET_TYPE = 0; // WPA,具体可查看 array.xml
    /*********************默认WIFI配制******************/

    /**
     * ******************默认机构配置*****************
     */
    public static final String AGNECY = "创业健康医院";
    public static final String JGID = "1";
    /*********************默认机构配置******************/


}
