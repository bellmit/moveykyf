package com.cyrj.pharmacymessage.help;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cyrj.pharmacymessage.MyApplication;

public class UIUtil {
    /**
     * 检测网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context) {
        try {
            if (context != null) {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo != null) {
                    return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return  false;
    }
    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }
    public static void showToast(String s) {
        CommonUtils.showToast(MyApplication.getContext(), s);
    }
}
