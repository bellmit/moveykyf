package com.cyrj.pharmacymessage.base;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 实现Obsever观察retrofit请求网络后返回的数据,用来处理返回的数据时加载进度框
 * @param <T>
 */
public abstract class BaseCallBack<T> implements Observer<T> {
    private Context mContext;
    private KProgressHUD mProgressDialog;
    public BaseCallBack(){}
    public BaseCallBack(Context mContext){
        this.mContext=mContext;
    }
    @Override
    public void onSubscribe(Disposable d) {
        if (mContext!=null) {
            //显示加载进度条
            showInfoProgressDialog(mContext, "加载中...");
        }
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onError(e.getLocalizedMessage());
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }
    /**
     * response中的code为200
     */
    public abstract void onSuccess(T data);
    public abstract void onError(String error);
    /**
     * 隐藏等待条
     */
    private void hideInfoProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog=null;
        }
    }

    /**
     * 显示等待条
     */
    private  void showInfoProgressDialog(Context context, final String str) {
        if (mProgressDialog == null) {
            mProgressDialog = new KProgressHUD(context);
            mProgressDialog.setCancellable(true);

        }

        mProgressDialog.setLabel(str);

        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }
    /**
     * 当请求完成时调用（无论成功或失败）
     */
    public void onFinish(){
        //如果没有加入进度条操作可以不调用super
        hideInfoProgressDialog();
    }
}
