package com.cyrj.pharmacymessage.service.updata;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;


import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.help.CommonUtils;

import java.io.File;

/**
 * Description:更新下载后台进程
 * User: chenzheng
 * Date: 2016/12/14 0014
 * Time: 16:24
 */
public class UpdateService extends Service{

    private String apkUrl;
    private String filePath;
    private NotificationManager notificationManager;
    private Notification notification;
    private PendingIntent pendingIntent;


    @Override
    public void onCreate() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        filePath = Environment.getExternalStorageDirectory()+"/AppUpdate/energy.apk";
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent==null){
            notifyUser(getString(R.string.update_download_failed), getString(R.string.update_download_failed), 0);

            stopSelf();

        }
        apkUrl = intent.getStringExtra("apkUrl");
        notifyUser(getString(R.string.update_download_start), getString(R.string.update_download_start), 0);
        startDownload();
        return super.onStartCommand(intent, flags, startId);
    }

    private void startDownload() {
        UpdateManager.getInstance().startDownloads(apkUrl, filePath, new UpdateDownloadListener() {
            @Override
            public void onStarted() {

            }

            @Override
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onProgressChanged(int progress, String downloadUrl) {

                notifyUser(getString(R.string.update_download_progressing), getString(R.string.update_download_progressing), progress);
            }


            @Override
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onFinished(float completeSize, String downloadUrl) {
                notifyUser(getString(R.string.update_download_finish), getString(R.string.update_download_finish), 100);
//                NotificationChannel mChannel = notificationManager.getNotificationChannel(PUSH_CHANNEL_ID);
                notificationManager.deleteNotificationChannel(PUSH_CHANNEL_ID);
                stopSelf();

            }

            @Override
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onFailure() {
                notifyUser(getString(R.string.update_download_failed), getString(R.string.update_download_failed), 0);
                notificationManager.deleteNotificationChannel(PUSH_CHANNEL_ID);
                stopSelf();
            }
        });
    }

    /**
     * 更新notification
     * @param result
     * @param msg
     * @param progress
     */
    private static final int PUSH_NOTIFICATION_ID = (0x001);
    private static final String PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID";
    private static final String PUSH_CHANNEL_NAME = "PUSH_NOTIFY_NAME";
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void notifyUser(String result, String msg, int progress){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//适配8.0

            NotificationChannel channel = new NotificationChannel(PUSH_CHANNEL_ID, PUSH_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(false);
            channel.enableVibration(false);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
            Notification.Builder builder=new Notification.Builder(this,PUSH_CHANNEL_ID);
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentTitle(getString(R.string.app_name));
            if (progress > 0 && progress <= 100) {

                builder.setProgress(100, progress, false);

            } else {
                builder.setProgress(0, 0, false);
            }
            builder.setContentText(msg);
            builder.setAutoCancel(true);
            builder.setWhen(System.currentTimeMillis());
            builder.setTicker(result);
            builder.setContentIntent(progress >= 100 ? getContentIntent() :
                    PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
            notification = builder.build();
        }else {
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this,PUSH_CHANNEL_ID);
            builder.setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentTitle(getString(R.string.app_name));
            if (progress > 0 && progress <= 100) {

                builder.setProgress(100, progress, false);

            } else {
                builder.setProgress(0, 0, false);
            }
            builder.setContentText(msg);
            builder.setAutoCancel(true);
            builder.setWhen(System.currentTimeMillis());
            builder.setTicker(result);
            builder.setContentIntent(progress >= 100 ? getContentIntent() :
                    PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
            notification = builder.build();
        }
            notificationManager.notify(PUSH_NOTIFICATION_ID, notification);
        }

    /**
     * 进入apk安装程序
     * @return
     */
    private PendingIntent getContentIntent() {
        File apkFile = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) { //适配安卓7.0
            Uri apkUri = FileProvider.getUriForFile(MyApplication.getContext(), CommonUtils.getInstance().getPageName()+".provider", apkFile);
            //对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        }else {
            intent.setDataAndType(Uri.fromFile(apkFile),
                    "application/vnd.android.package-archive");
        }
        startActivity(intent);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
