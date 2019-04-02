package com.cyrj.pharmacymessage.base;

import android.app.Activity;

import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.help.UIUtil;
import com.cyrj.pharmacymessage.port.RxView;
import com.cyrj.pharmacymessage.port.ZthPresenter;
import com.cyrj.pharmacymessage.utils.Api;
import com.cyrj.pharmacymessage.utils.RetrofitUtils;

public class BasePresenter implements ZthPresenter {

    public Api api;
    public RxView rxView;
    public Activity mActivity;
    //在这个类中找到RetrofitUtils
    @Override
    public void onCreate() {
        api = RetrofitUtils.get();
        if (!UIUtil.isNetWorkConnected(MyApplication.getContext())){
            UIUtil.showToast("无网络,请检查网络");
        }
    }

    @Override
    public void onView(RxView rxView) {
        this.rxView=rxView;
    }


}
