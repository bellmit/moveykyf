package com.cyrj.pharmacymessage.help;

import com.cyrj.pharmacymessage.MyApplication;


/**
 * WIFI preference辅助类 Created by huangy on 14-3-25.
 */
public class SettingPrefUtils {

    public static final String SETING_PREF = "data";
    public static final String IP_KEY = "ip";
    public static final String PORT_KEY = "port";//端口号
    public static final String SSID_KEY = "ssid";
    public static final String SSID_PWD_KEY = "ssid_pwd";

    public static final String AGENCY_ID_KEY = "jgid";
    public static final String AGENCY_NAME_KEY = "agency";
    public static final String VIB_KEY = "vib_type";
    public static final String VOICE_KEY = "voice_type";
    public static final String PINGMU_KEY = "pingmu_type";
    public static final String TOKEN = "token";
    public static final String USERID = "userId";
    public static final String USERNAME = "userName";
    private static MyApplication preferences;

    /**
     * 获取当前声音提示
     * @return 默认为true
     */
    public static boolean isVoice() {

        return preferences.getBoolean(VOICE_KEY, true);
    }

    /**
     * 获取当前JGID
     * @return 默认为1
     */
    public static String getJgid() {
        return preferences.getString(AGENCY_ID_KEY, Config.JGID);
    }

    /**
     * 获取当前机构名称
     * @return 默认读取
     */
    public static String getAgency() {

        return preferences.getString(AGENCY_NAME_KEY, Config.AGNECY);
    }

    /**
     * 获取WIFI SSID
     * @return 失败返回null, 默认为""字符串
     */
    public static String getSSID() {

        return preferences.getString(SSID_KEY, "");
    }

    /**
     * 获取WIFI 密码
     * @return WIFI 密码,默认为""字符串，失败返回null。
     */
    public static String getPassword() {

        return preferences.getString(SSID_PWD_KEY, "");
    }

    /**
     * 获取IP
     *
     * @return IP, 默认为""字符串，失败返回null。
     */
    public static String getIP() {

        return preferences.getString(IP_KEY, "");
    }


    /**
     * 获取服务端端口
     * @return 服务端端口, 默认为""字符串，失败返回null。
     */
    public static String getPort() {


        return preferences.getString(PORT_KEY, "");
    }

    /**
     * 获取震动设置
     *
     * @return
     */
    public static boolean isVib() {

        return preferences.getBoolean(VIB_KEY, true);
    }
    /**
     * 获取屏幕长亮设置
     * @return
     */
    public static boolean isPingmu() {

        return preferences.getBoolean(PINGMU_KEY, true);
    }
}
