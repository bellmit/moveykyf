package com.cyrj.pharmacymessage.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class AnalyseCodeService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public AnalyseCodeService(String name) {
        super(name);
    }
    /**
     * 扫描条码字符串 EXTRA KEY,VALUE 是String类型
     */
    public static final String SCAN_RESULT_EXTRA = "com.cyrj.pharmacymessage.service.AnalyseCodeService.SCAN_RESULT_EXTRA";
    /**
     * 扫描类型EXTRA KEY。VALUE值：0 条码二维码 ；1 rfid
     */
    public static final String SCAN_TYPE = "com.cyrj.pharmacymessage.service.AnalyseCodeService.SCAN_TYPE";
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        String scanResult = intent.getStringExtra(SCAN_RESULT_EXTRA);
        return super.onStartCommand(intent, flags, startId);
    }
}
