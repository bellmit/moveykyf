package com.cyrj.pharmacymessage.barcode;

/**
 * barcode actions
 */
public class BarcodeActions {

    /**
     * 条码
     */
    public static final String BARCODE_GET = "com.barcode.barget";

    /**
     * rfid码
     */
    public static final String RFID_GET = "com.barcode.rfidget";

    /**
     * 胸卡条码
     */
    public static final String XK_GET = "com.barcode.xk";

    /**
     * 标签条码
     */
    public static final String BQ_GET = "com.barcode.bq";

    /**
     * 打包条码
     */
    public static final String DB_GET = "com.barcode.db";


    /**
     * 广播{@link android.content.Intent}中条码KEY
     */
    public static final String EXTRA_BARCODE_DATA = "data";

}
