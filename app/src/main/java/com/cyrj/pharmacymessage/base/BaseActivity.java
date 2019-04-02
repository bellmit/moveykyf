package com.cyrj.pharmacymessage.base;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bsoft.mob.barcode.BarcodeImpl;
import com.bsoft.mob.barcode.Config;
import com.bsoft.mob.barcode.IBarCode;
import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.databinding.ActivityBaseBinding;
import com.cyrj.pharmacymessage.help.CommonUtils;
import com.cyrj.pharmacymessage.utils.TUtil;
import com.kaopiz.kprogresshud.KProgressHUD;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity<P extends BasePresenter,D extends ViewDataBinding>extends AppCompatActivity{
    protected P mPresenter;
    protected D mBindingView;
    protected ActivityBaseBinding mBaseBindingView;
    private KProgressHUD mProgressDialog;
    private CompositeDisposable mCompositeSubscription;
    public IBarCode barCode;

    public BroadcastReceiver barBroadcast;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mPresenter.onCreate();
        mPresenter.mActivity=this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        barBroadcast = getBarcodeReceiver();
        barCode = new BarcodeImpl();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //每个页面可以分为两个部分，上面的标题栏，下面的内容部分
        mBindingView= DataBindingUtil.inflate(getLayoutInflater(),layoutResID,null,false);//下面内容部分
        //将下面的内容布局添加到包括全屏布局的页面中
        mBaseBindingView=DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base,null,false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mBindingView.getRoot().setLayoutParams(params);
        RelativeLayout mContainer = mBaseBindingView.container;
        mContainer.addView(mBindingView.getRoot());
        getWindow().setContentView(mBaseBindingView.getRoot());
        initView();
        initLisener();
        mBaseBindingView.commonTitle.llLiftBack.setOnClickListener(v -> onFinlsh());
    }
        public abstract void initLisener();
        public abstract void initView();
    /**
     * 获取要监听的条码广播接收者
     *
     * @return 默认返回null
     */
    public BroadcastReceiver getBarcodeReceiver() {
        return null;
    }
        /**
         * 设置标题
         */
        public void setTitile(String titile){
            if (!TextUtils.isEmpty(titile)){
                mBaseBindingView.commonTitle.tvTitle.setText(titile);
            }else{
                mBaseBindingView.commonTitle.tvTitle.setText("");
            }
        }
    public void onFinlsh(){
        finish();
    }
    /**
     * 隐藏标题栏
     */
    public void hideTitleBar(){
        mBaseBindingView.commonTitle.rlTitleBar.setVisibility(View.GONE);
    }
    /**
     * 隐藏返回箭头
     */
    public void hideBackImg(){
        mBaseBindingView.commonTitle.llLiftBack.setVisibility(View.GONE);
    }
    /**
     * 显示菜单
     */
    public void showMenu(){
        mBaseBindingView.commonTitle.llLiftMenu.setVisibility(View.VISIBLE);
    }
    /**
     * 返回标题
     */
    public void hideTitleText(){
        mBaseBindingView.commonTitle.tvTitle.setVisibility(View.GONE);
    }
    /**
     * 设置右侧文字
     */
    public void setRightTile(String rightTile,View.OnClickListener onClickListener){
        mBaseBindingView.commonTitle.tvRightText.setText(TextUtils.isEmpty(rightTile)?"":rightTile);
        mBaseBindingView.commonTitle.llRightText.setVisibility(View.VISIBLE);
        mBaseBindingView.commonTitle.llRightImg.setVisibility(View.GONE);
        if (onClickListener!=null){
            mBaseBindingView.commonTitle.llRightText.setOnClickListener(onClickListener);
        }
    }
    /**
     * 设置右侧图片
     */
    protected void setRightImg(int img, View.OnClickListener listener){
        mBaseBindingView.commonTitle.llRightText.setVisibility(View.GONE);
        mBaseBindingView.commonTitle.llRightImg.setVisibility(View.VISIBLE);
        if(img>0){
            mBaseBindingView.commonTitle.ivRightImg.setImageResource(img);
        }
        if(listener!=null){
            mBaseBindingView.commonTitle.llRightImg.setOnClickListener(listener);
        }
    }
    /**
     * 设置右侧图片
     */
    protected void setRightImg(int img, View.OnClickListener listener,float width,float height){
        mBaseBindingView.commonTitle.llRightText.setVisibility(View.GONE);
        mBaseBindingView.commonTitle.llRightImg.setVisibility(View.VISIBLE);
        if(img>0){
            mBaseBindingView.commonTitle.ivRightImg.setImageResource(img);
        }
        if(listener!=null){
            mBaseBindingView.commonTitle.llRightImg.setOnClickListener(listener);
        }
        if(width>0&&height>0){
            ViewGroup.LayoutParams layoutParams = mBaseBindingView.commonTitle.ivRightImg.getLayoutParams();
            layoutParams.width= CommonUtils.dip2px(MyApplication.getContext(),width);
            layoutParams.height= CommonUtils.dip2px(MyApplication.getContext(),height);
            mBaseBindingView.commonTitle.ivRightImg.setLayoutParams(layoutParams);
        }
    }
    /**
     * 显示进度框
     * @param str
     */
    public final void showInfoProgressDialog(final String... str) {
        if (mProgressDialog == null) {
            mProgressDialog = new KProgressHUD(MyApplication.getContext());
            mProgressDialog.setCancellable(true);
        }
        if (str.length == 0) {
            mProgressDialog.setLabel("加载中...");
        } else {
            mProgressDialog.setLabel(str[0]);
        }

        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }
    /**
     * 隐藏等待条
     */
    public final void hideInfoProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog=null;
        }
    }
    /**
     * 页面是否有数据
     * @param isShow   true有数据  false没数据
     */
    protected void showNoData(boolean isShow){
        if(!isShow){
            mBaseBindingView.llNodata.setVisibility(View.VISIBLE);
            mBindingView.getRoot().setVisibility(View.GONE);
        }else {
            mBaseBindingView.llNodata.setVisibility(View.GONE);
            mBindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }
    /**
     * 移除网络请求
     */
    public void removeSubscription() {
        if (this.mCompositeSubscription != null && mCompositeSubscription.isDisposed()) {
            this.mCompositeSubscription.dispose();
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (null != barCode) {
            try {
                if (barCode.onKeyDown(keyCode, event, this)) {
                    return true;
                }
            } catch (Exception e) {
                Log.e(Config.TAG, e.getMessage(), e);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (null != barCode) {
            try {
                if (barCode.onKeyUp(keyCode, event, this)) {
                    return true;
                }
            } catch (Exception e) {
                Log.e(Config.TAG, e.getMessage(), e);
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != barCode) {
            try {
                barCode.start(this);
            } catch (Exception e) {
                Log.e(Config.TAG, e.getMessage(), e);
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (null != barBroadcast) {

            String[] actions = getActions();
            if (actions != null && actions.length != 0) {
                IntentFilter filter = new IntentFilter();
                for (String action : actions) {
                    filter.addAction(action);
                }
                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
                lbm.registerReceiver(barBroadcast, filter);
            }
        }
    }
    public MyApplication getMyApplication() {
        return (MyApplication) getApplication();
    }
    @Override
    public void onPause() {
        super.onPause();
        if (null != barCode) {
            try {
                barCode.close();
            } catch (Exception e) {
                Log.e(Config.TAG, e.getMessage(), e);
            }
        }

    }
    @Override
    protected void onStop() {
        if (null != barBroadcast) {
            LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
            lbm.unregisterReceiver(barBroadcast);
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        hideInfoProgressDialog();
        removeSubscription();
        super.onDestroy();
    }
    /**
     * 获取要监听的条码扫描Actions
     *
     * @return 默认实现返回null
     */
    public String[] getActions() {
        return null;
    }
}
