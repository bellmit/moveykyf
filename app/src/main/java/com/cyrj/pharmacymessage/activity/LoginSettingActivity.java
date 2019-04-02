package com.cyrj.pharmacymessage.activity;

import android.Manifest;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bsoft.mob.wifi.WifiScanActivity;
import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.base.BaseActivity;
import com.cyrj.pharmacymessage.databinding.ActivityLoginSettingBinding;
import com.cyrj.pharmacymessage.help.SettingPrefUtils;
import com.cyrj.pharmacymessage.help.UIUtil;
import com.cyrj.pharmacymessage.help.UrlConfig;
import com.cyrj.pharmacymessage.port.RxView;
import com.cyrj.pharmacymessage.presenter.LoginPresenter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginSettingActivity extends BaseActivity<LoginPresenter,ActivityLoginSettingBinding> implements RxView<ScanResult>{
    private static final int RQT_GET_SSID = 0;
    String ipStr;
    String port;
    String ssidStr;
    String ssidPwd;
    boolean vib;
    boolean pingmu;
    boolean sound;
    String agencyName;
    String agencyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_setting);
        mPresenter.onView(this);
    }

    @Override
    public void initView() {
        setTitile("管理员设置");
        //数据初始化
        initData();
        saveSettingsInPref(false);
    }
    @Override
    public void initLisener() {
        mBindingView.settingScanImage.setOnClickListener(v -> {
            new RxPermissions(this).request(Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION).subscribe(aBoolean -> {
                         if (aBoolean){
                                //进入wifi列表
                             Intent intent = new Intent(this, WifiScanActivity.class);
                             startActivityForResult(intent, RQT_GET_SSID);
                         }
            });
            mBindingView.settingSsidEdit.setText("");
        });
        setRightTile("保存",v -> {

            saveSettingsInPref(true);
        });
    }

    private void initData(){

    }
    private void saveSettingsInPref(boolean isWrite){
       Observable.create((ObservableOnSubscribe<Boolean>) emitter -> {
           if (isWrite) {
               //如果是写入
               boolean pingmu = mBindingView.settingTransfuseSwitch.isChecked();
               boolean zd = mBindingView.settingVibSwitch.isChecked();
               boolean voice = mBindingView.settingLogSwitch.isChecked();

//               SharedPreferences pref = getSharedPreferences(
//                       SettingPrefUtils.SETING_PREF, Context.MODE_PRIVATE);
//               SharedPreferences.Editor editor = pref.edit();
               MyApplication.saveBoolean(SettingPrefUtils.PINGMU_KEY, pingmu);
               MyApplication.saveBoolean(SettingPrefUtils.VIB_KEY, zd);
               MyApplication.saveBoolean(SettingPrefUtils.VOICE_KEY, voice);
               MyApplication.saveString(SettingPrefUtils.AGENCY_ID_KEY, mBindingView.settingAgencyIdEdit.getText().toString().trim());//机构ID
               MyApplication.saveString(SettingPrefUtils.AGENCY_NAME_KEY, mBindingView.settingAgencyEdit.getText().toString().trim());//机构名称

               MyApplication.saveString(SettingPrefUtils.IP_KEY, mBindingView.settingSetIpEdit.getText().toString().trim());//IP
               MyApplication.saveString(SettingPrefUtils.PORT_KEY, mBindingView.settingSetPortEdit.getText().toString().trim());//端口号

               MyApplication.saveString(SettingPrefUtils.SSID_KEY, mBindingView.settingSsidEdit.getText().toString().trim());//SSID
               MyApplication.saveString(SettingPrefUtils.SSID_PWD_KEY, mBindingView.settingSetSsidPasswordEdit.getText().toString().trim());//wifi密码
               emitter.onNext(isWrite);
           }else{
               //读取
               ipStr = SettingPrefUtils.getIP();
               port = SettingPrefUtils.getPort();
               ssidStr = SettingPrefUtils.getSSID();
               ssidPwd = SettingPrefUtils.getPassword();
               vib = SettingPrefUtils.isVib();
               sound = SettingPrefUtils.isVoice();
               agencyId = SettingPrefUtils.getJgid();
               agencyName = SettingPrefUtils.getAgency();
               pingmu = SettingPrefUtils.isPingmu();
               emitter.onNext(isWrite);
           }
        })
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<Boolean>() {
                   @Override
                   public void onSubscribe(Disposable d) { }

                   @Override
                   public void onNext(Boolean aBoolean) {
                        if (aBoolean){
                            MyApplication.baseUrl= UrlConfig.getWebBaseUrl(mBindingView.settingSetIpEdit.getText().toString().trim(),
                                    mBindingView.settingSetPortEdit.getText().toString().trim());
                            UIUtil.showToast("保存成功");
                            finish();
                        }else{
                            //读取数据
                            mBindingView.settingTransfuseSwitch.setChecked(pingmu);//屏幕
                            mBindingView.settingVibSwitch.setChecked(vib);//震动
                            mBindingView.settingLogSwitch.setChecked(sound);//常亮
                            mBindingView.settingAgencyEdit.setText(agencyName);
                            mBindingView.settingAgencyIdEdit.setText(agencyId);
                            mBindingView.settingSetIpEdit.setText(ipStr);
                            mBindingView.settingSetPortEdit.setText(port);
                            mBindingView.settingSsidEdit.setText(ssidStr);
                            mBindingView.settingSetSsidPasswordEdit.setText(ssidPwd);
                        }
                   }

                   @Override
                   public void onError(Throwable e) { }

                   @Override
                   public void onComplete() { }
               });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void success(ScanResult dateil) {
        mBindingView.settingSsidEdit.setText(dateil.SSID);
    }

    @Override
    public void fail(String errMessage) {
        UIUtil.showToast(errMessage);
    }
}
