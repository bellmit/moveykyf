package com.cyrj.pharmacymessage.barcode;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bsoft.mob.barcode.Config;
import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 解析条码扫描结果，并广播扫描结果 Created by hy on 14-3-7.
 */
public class AnalyseCodeService extends IntentService {

    /**
     * 扫描条码字符串 EXTRA KEY,VALUE 是String类型
     */
    public static final String SCAN_RESULT_EXTRA = "com.cyrj.pharmacymessage.barcode.AnalyseCodeService.SCAN_RESULT_EXTRA";

    /**
     * 扫描类型EXTRA KEY。VALUE值：0 条码二维码 ；1 rfid
     */
    public static final String SCAN_TYPE = "com.cyrj.pharmacymessage.barcode.AnalyseCodeService.SCAN_TYPE";


    //用于在主线程中显示Toast
    private Handler handler_ = new Handler();

    private MediaPlayer mCurrentMediaPlayer;

    private ExecutorService es;

    public AnalyseCodeService() {
        super(AnalyseCodeService.class.getName());
    }

    public AnalyseCodeService(String name) {
        super(AnalyseCodeService.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        es = Executors.newFixedThreadPool(3);
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        //某些厂商不会处罚onHandleIntent 方法 ,例如优博讯 u6200s
        es.submit(new Runnable() {
            @Override
            public void run() {
                onHandleMyIntent(intent);
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }


    public void onHandleMyIntent(Intent intent){

        String scanResult = intent.getStringExtra(SCAN_RESULT_EXTRA);
        int scanType = intent.getIntExtra(SCAN_TYPE, 0);
        playSound(R.raw.sound_key);

        if (TextUtils.isEmpty(scanResult)) {
            playSound(R.raw.wrong);
            showToast(getString(R.string.barcode_empty_tip));
            return;
        }
        scanResult = scanResult.trim();
        MyApplication app = (MyApplication) getApplication();

        //未登陆扫描，例如扫描胸卡
        if (app.loginResponse == null) {
            broadCast(scanResult, BarcodeActions.BARCODE_GET);
            playSound(R.raw.sound_ok);
            return;
        }

        //已登陆，解析条码类型
        String prefixes = app.loginResponse.prefixs;

        analyseCode(scanResult, scanType,  prefixes);
    }

    /**
     * 异步线程执行
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {

    }

    /**
     * 解析扫描业务规则
     *
     * @param scanResult
     * @param scanType
     * @param prefixes
     */
    private void analyseCode(String scanResult, int scanType,String prefixes) {

        //规则列表为空判断
        if (prefixes == null || prefixes.isEmpty()) {
            showToast(getString(R.string.cannot_analyse_barcode_whithout_set_rule));
            playSound(R.raw.wrong);
            return;
        }


        //条码字符串为空判断
        if (TextUtils.isEmpty(scanResult)) {
            playSound(R.raw.wrong);
            showToast(getString(R.string.barcode_empty_tip));
            return;
        }

        if (!TextUtils.isEmpty(prefixes)){
            if (scanResult.startsWith(prefixes)) {
                broadCast(scanResult, BarcodeActions.XK_GET);
            } else if (scanResult.startsWith(prefixes)) {
                broadCast(scanResult, BarcodeActions.BQ_GET);
            } else if (scanResult.startsWith(prefixes)) {
                broadCast(scanResult, BarcodeActions.DB_GET);
            } else {
                broadCast(scanResult, scanType == 0 ? BarcodeActions.BARCODE_GET : BarcodeActions.RFID_GET);
                playSound(R.raw.sound_ok);
                return;
            }
        }


        //默认当前静配中心，没有规则
        showToast(getString(R.string.cannot_analyse_barcode_whithout_set_rule));
        playSound(R.raw.wrong);


    }


    /**
     * 在UI 线程 中调用Toast show函数
     *
     * @param msg show message
     */
    public void showToast(final String msg) {

        handler_.post(new Runnable() {

            @Override
            public void run() {
                try {
                    Toast.makeText(getApplicationContext(), msg,
                            Toast.LENGTH_SHORT).show();
                } catch (RuntimeException e) {
                    Log.e(Config.TAG,
                            "A runtime exception was thrown while executing code in a runnable",
                            e);
                }
            }

        });
    }

    private void playSound(int resId) {
        // Stop current player, if there's one playing
        if (null != mCurrentMediaPlayer) {
            mCurrentMediaPlayer.stop();
            mCurrentMediaPlayer.release();
        }

        mCurrentMediaPlayer = MediaPlayer.create(this, resId);
        if (null != mCurrentMediaPlayer) {
            mCurrentMediaPlayer.start();
        }
    }


    private void broadCast(String barcode, String action) {
        Intent intent1 = new Intent(action);
        intent1.putExtra(BarcodeActions.EXTRA_BARCODE_DATA, barcode);
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.sendBroadcast(intent1);
    }

}
