package com.cyrj.pharmacymessage.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.barcode.BarcodeActions;
import com.cyrj.pharmacymessage.base.BaseActivity;
import com.cyrj.pharmacymessage.bean.UserBean;
import com.cyrj.pharmacymessage.databinding.ActivityLoginBinding;
import com.cyrj.pharmacymessage.help.SettingPrefUtils;
import com.cyrj.pharmacymessage.help.UIUtil;
import com.cyrj.pharmacymessage.port.RxView;
import com.cyrj.pharmacymessage.presenter.LoginPresenter;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity<LoginPresenter,ActivityLoginBinding> implements RxView<UserBean>{

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPresenter.onView(this);
        hideTitleBar();
        initData();
    }

    @Override
    public void initLisener() {
//        mBindingView.
        //广播
        barBroadcast=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (BarcodeActions.BARCODE_GET.equals(intent.getAction())) {

                }
            }
        };
        mBindingView.btnLogin.setOnClickListener(view ->{
            //mBindingView.loginSwitch.isChecked()  false是用户登录
           String userName= mBindingView.etUsername.getText().toString().trim();
           String password=mBindingView.etPassword.getText().toString().trim();
            if (TextUtils.isEmpty(userName)){
                UIUtil.showToast("用户名不能为空");
                return;
            }
            if (mBindingView.loginSwitch.isChecked()){
                if (TextUtils.isEmpty(password)){
                    UIUtil.showToast("密码不能为空");
                }else{
                    disposable = Observable.create((ObservableOnSubscribe<Boolean>) emitter -> {
                       boolean isSuccess=mPresenter.validateUser(userName,password);
                       emitter.onNext(isSuccess);
                    }).subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(aBoolean -> {
                          if (aBoolean){
                              //进入管理页面
                              startActivity(new Intent(getApplicationContext(),LoginSettingActivity.class));
                          }else{
                              UIUtil.showToast("登录失败");
                          }
                      });
                }
            }else{
                //直接进行登录操作
                //获取机构ID
               String JDid =SettingPrefUtils.getJgid();
                mPresenter.userLogin(userName,password,JDid,null);
//                startActivity(new Intent(MyApplication.getContext(),MainActivity.class));

            }
        });
        mBindingView.loginSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                //管理登录
                mBindingView.tvTitle.setText("管理员登录");
            }else{
                mBindingView.tvTitle.setText("用户登录");
            }
            String userName=mBindingView.etUsername.getText().toString();
            String password=mBindingView.etPassword.getText().toString();
            if (!TextUtils.isEmpty(userName)){
                mBindingView.etUsername.setText(null);
            }
            if (!TextUtils.isEmpty(password)){
                mBindingView.etPassword.setText(null);
            }
        });
    }

    @Override
    public void initView() {

    }
    private void initData(){

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable!=null) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
        }
    }
    @Override
    public void success(UserBean dateil) {
        getMyApplication().loginResponse=dateil;
        startActivity(new Intent(MyApplication.getContext(),MainActivity.class).putExtra("qxkzList",dateil));
    }

    @Override
    public void fail(String errMessage) {

    }
}
