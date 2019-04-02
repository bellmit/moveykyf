package com.cyrj.pharmacymessage.presenter;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.text.TextUtils;

import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.base.BaseCallBack;
import com.cyrj.pharmacymessage.base.BasePresenter;
import com.cyrj.pharmacymessage.bean.BaseBean;
import com.cyrj.pharmacymessage.bean.UserBean;
import com.cyrj.pharmacymessage.db.Database;
import com.cyrj.pharmacymessage.help.SettingPrefUtils;
import com.cyrj.pharmacymessage.help.UIUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

public class LoginPresenter extends BasePresenter{
    private static final int RQT_GET_SSID = 0;
    /**
     * 接收上个页面返回的数据
     */
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        if (requestCode == RQT_GET_SSID && resultCode == RESULT_OK) {
            ScanResult result = data.getParcelableExtra("result");
            analyScanResult(result);
        }
    }
    private void analyScanResult(ScanResult result) {

        if (result == null) {
            return;
        }
        rxView.success(result);
    }
    /**
     * 用户登录
     * @param userName
     * @param password
     * @param JGID
     */
    public void userLogin(String userName,String password,String JGID,String deviceId){
        Map<String,String>data=new HashMap<>();
        data.put("username",userName);
        data.put("password",password);
        data.put("jgid",JGID);
        data.put("deviceId",deviceId);
        api.login(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCallBack<BaseBean<UserBean>>(mActivity) {
                    @Override
                    public void onSuccess(BaseBean<UserBean> data) {
                        if (data.isSuccess()){
                            //登录
                            MyApplication.saveString(SettingPrefUtils.TOKEN,data.getData().getToken());
                            MyApplication.saveString(SettingPrefUtils.USERID,data.getData().getUserId());
                            MyApplication.saveString(SettingPrefUtils.USERNAME,data.getData().getUserName());
                            MyApplication.saveString(SettingPrefUtils.AGENCY_ID_KEY,data.getData().getJgid());
                            rxView.success(data.getData());
                        }else{
                            UIUtil.showToast(data.getErrorMessage());
                        }
                    }

                    @Override
                    public void onError(String error) {
                        UIUtil.showToast(error);
                    }
                });

    }
    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    public boolean validateUser(String username, String password) {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return false;
        }
        boolean isRight = false;
        Uri uri = Database.User.CONTENT_URI;
        String[] projection = {Database.User.USER_NAME, Database.User.PASSWORD};
        String selection = Database.User.USER_NAME + "=?";
        String[] selectionArgs = {username};
        Cursor cursor = MyApplication.getContext().getContentResolver().query(uri,
                projection, selection, selectionArgs, null);
        if (cursor.moveToNext()) {
            String pwdStr = cursor.getString(1);
            isRight = password.equals(pwdStr);
        }
        cursor.close();
        return isRight;
    }
}
