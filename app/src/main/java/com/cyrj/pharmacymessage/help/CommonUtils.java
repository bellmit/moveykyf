package com.cyrj.pharmacymessage.help;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.cyrj.pharmacymessage.MyApplication;

public class CommonUtils {

    private static CommonUtils commonUtils;

    public static CommonUtils getInstance(){
        if (commonUtils==null) {
            synchronized (CommonUtils.class) {
                if (commonUtils==null) {
                    commonUtils = new CommonUtils();
                }
            }
        }
        return commonUtils;
    }
    public static boolean isNotEmpty(String input){
        if (input == null || "".equals(input)||"null".equals(input))
            return true;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context,float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    protected static Toast toast = null;
    public static void showToast(Context context, String title) {

        if (toast == null) {
            toast = Toast.makeText(context, title, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            toast.setText(title);
            toast.show();
        }
    }
    public static  String getPageName(){
        return MyApplication.getContext().getPackageName();
    }
}
