package com.cyrj.pharmacymessage.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    /**
     * 转化为yyyy-mm-dd HH:mm
     *
     * @return
     */
    public static String timeFormatyyyymmddHHmm(String text) {
        StringBuilder sb = new StringBuilder();
        if (text == null || "".equals(text)) {
            return "00-00 00:00";
        }
        if (text.length() >= 4) {
            sb.append(text.substring(0, 4) + "-");
        }
        if (text.length() >= 6) {
            sb.append(text.substring(4, 6)).append("-");
        }
        if (text.length() >= 8) {
            sb.append(text.substring(6, 8)).append(" ");
        }
        if (text.length() >= 10) {
            sb.append(text.substring(8, 10)).append(":");
        }
        if (text.length() >= 12) {
            sb.append(text.substring(10, 12)).append("");
        }
        return sb.toString();
    }
    /**
     * 转化为yyyy-mm-dd
     *
     * @return
     */
    public static String timeFormatyyyymmdd(String text) {
        StringBuilder sb = new StringBuilder();
        if (text == null || "".equals(text)) {
            return "00-00";
        }
        if (text.length() >= 4) {
            sb.append(text.substring(0, 4) + "-");
        }
        if (text.length() >= 6) {
            sb.append(text.substring(4, 6)).append("-");
        }
        if (text.length() >= 8) {
            sb.append(text.substring(6, 8)).append(" ");
        }
        return sb.toString();
    }
    /**
     * 转化为mm-dd HH:mm
     *
     * @return
     */
    public static String timeFormatmmddHHmm(String text) {
        StringBuilder sb = new StringBuilder();
        if (text == null || "".equals(text)) {
            return "00-00 00:00";
        }

        if (text.length() >= 6) {
            sb.append(text.substring(4, 6)).append("-");
        }
        if (text.length() >= 8) {
            sb.append(text.substring(6, 8)).append(" ");
        }
        if (text.length() >= 10) {
            sb.append(text.substring(8, 10)).append(":");
        }
        if (text.length() >= 12) {
            sb.append(text.substring(10, 12)).append("");
        }
        return sb.toString();
    }
    /**
     * 转化为 HH:mm
     *
     * @return
     */
    public static String timeFormatHHmm(String text) {
        StringBuilder sb = new StringBuilder();
        if (text == null || "".equals(text)) {
            return "00-00 00:00";
        }
        if (text.length() >= 10) {
            sb.append(text.substring(8, 10)).append(":");
        }
        if (text.length() >= 12) {
            sb.append(text.substring(10, 12)).append("");
        }
        return sb.toString();
    }
    /**
     * 转化为yyyy年mm月dd日
     *
     * @return
     */
    public static String timeFormat3(String text) {
        String str = "";
        if (text == null || "".equals(text)) {
            return "0000年00月00日";
        }
        if (text.length() >= 4) {
            str += text.substring(0, 4) + "年";
        }
        if (text.length() >= 6) {
            str += text.substring(4, 6) + "月";
        }
        if (text.length() >= 8) {
            str += text.substring(6, 8) + "日";
        }
        return str;
    }
    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
