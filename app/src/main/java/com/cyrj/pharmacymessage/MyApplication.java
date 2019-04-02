package com.cyrj.pharmacymessage;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.cyrj.pharmacymessage.bean.UserBean;
import com.cyrj.pharmacymessage.help.UrlConfig;

public class MyApplication extends Application{
    private static Context context;
    public static String baseUrl;
    /**
     * 当前登陆返回的信息
     */
    public UserBean loginResponse;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        baseUrl= UrlConfig.getWebBaseUrl();

    }
    public static Context getContext(){
        return context;
    }
    public static SharedPreferences getPreferences() {
        return getContext().getSharedPreferences("data", MODE_PRIVATE);
    }

    public static String getString(String key, String defValue) {

        return getPreferences().getString(key, defValue);
    }

    public static Boolean getBoolean(String key, Boolean defValue) {

        return getPreferences().getBoolean(key, defValue);
    }
    public static int getInt(String key, int defValue) {

        return getPreferences().getInt(key, defValue);
    }

    public static long getLong(String key, long defValue) {

        return getPreferences().getLong(key, defValue);
    }

    /**
     * 保存字符串变量
     */
    public static void saveString( String key, String value) {
        getPreferences().edit().putString(key, value).commit();
    }
    public static void saveBoolean( String key, Boolean value) {
        getPreferences().edit().putBoolean(key, value).commit();
    }

    public static void saveInt( String key, int value) {
        getPreferences().edit().putInt(key, value).commit();
    }

    public static void saveLong( String key, long value) {
        getPreferences().edit().putLong(key, value).commit();
    }
}
