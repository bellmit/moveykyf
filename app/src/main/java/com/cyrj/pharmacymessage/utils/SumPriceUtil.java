package com.cyrj.pharmacymessage.utils;

import java.math.BigDecimal;

/**
 * 合计价格
 */

public class SumPriceUtil {
    //加
    public static String getAddPrice(String p1, String p2){
        BigDecimal b1 = new BigDecimal(p1);
        BigDecimal b2 = new BigDecimal(p2);
        String ss=b1.add(b2).toString();
        return ss;
    }
    //减
    public static String getMinusPrice(String p1, String p2){
        BigDecimal b1 = new BigDecimal(p1);
        BigDecimal b2 = new BigDecimal(p2);
        String ss=b1.subtract(b2).toString();
        return ss;
    }
    //乘
    public static String getRidePrice(String p1, String p2) {
        BigDecimal b1 = new BigDecimal(p1);
        BigDecimal b2 = new BigDecimal(p2);
        String ss = b1.multiply(b2).toString();
        return ss;
    }
    //除
    public static double divisionMethod(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
