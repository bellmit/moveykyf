package com.cyrj.pharmacymessage.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.activity.MainActivity;
import com.cyrj.pharmacymessage.databinding.FragmentBaseBinding;
import com.cyrj.pharmacymessage.utils.TUtil;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragment<E extends BasePresenter,SV extends ViewDataBinding> extends Fragment {
    //布局view
    protected SV mBindingView;

    protected Context mContext;
    protected FragmentBaseBinding mBaseBinding;
    public E mPresenter;
    private KProgressHUD mProgressDialog;
    private RxPermissions rxPermissions;
    private CompositeDisposable mCompositeSubscription;
    private MainActivity mainActivity;
    // 内容布局
    protected RelativeLayout mContainer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter==null){
            mPresenter = TUtil.getT(this, 0);
            mPresenter.onCreate();
            mPresenter.mActivity=getActivity();
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBaseBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false);
        mBindingView = DataBindingUtil.inflate(getActivity().getLayoutInflater(), setContent(), null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mBindingView.getRoot().setLayoutParams(params);
        mContainer = mBaseBinding.container;
        mContainer.addView(mBindingView.getRoot());
        mContext = getContext();
        mainActivity= (MainActivity) getActivity();
        initView();
        initLisener();
        initPresenter();
        return mBaseBinding.getRoot();
    }
    /**
     * 布局
     */
    public abstract int setContent();
    protected abstract void initView();

    protected abstract void initPresenter();
    private void initLisener() {
        mBaseBinding.llErrorRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onRefresh();
            }
        });
        mBaseBinding.commonTitle.llLiftBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        mBaseBinding.commonTitle.llLiftMenu.setOnClickListener(v -> {
            //点击侧滑菜单
            if (mainActivity!=null){
                if (mainActivity.mDlNavButtom!=null){
                    if (mainActivity.mDlNavButtom.isDrawerOpen(Gravity.LEFT)){
                        mainActivity.mDlNavButtom.closeDrawer(Gravity.LEFT);
                    }else{
                        mainActivity.mDlNavButtom.openDrawer(Gravity.LEFT);
                    }
                }

            }

        });
    }

    /**
     * 隐藏标题栏
     */
    protected void hideTitleBar(){
        mBaseBinding.commonTitle.rlTitleBar.setVisibility(View.GONE);
    }
    /**
     * 隐藏返回箭头
     */
    protected void hideBackImg(){
        mBaseBinding.commonTitle.llLiftBack.setVisibility(View.GONE);
    }
    /**
     * 显示菜单
     */
    public void showMenu(){
        mBaseBinding.commonTitle.llLiftMenu.setVisibility(View.VISIBLE);
    }
    /**
     * 设置标题
     */
    protected void setTitle(String title){
        if (!TextUtils.isEmpty(title)){
            mBaseBinding.commonTitle.tvTitle.setText(title);
        }else{
            mBaseBinding.commonTitle.tvTitle.setText("");
        }
    }
    /**
     * 设置右侧文字
     */
    protected void setRightTitle(String rightTitle, View.OnClickListener listener){
        mBaseBinding.commonTitle.tvRightText.setText(!TextUtils.isEmpty(rightTitle)?rightTitle:"");
        mBaseBinding.commonTitle.llRightText.setVisibility(View.VISIBLE);
        mBaseBinding.commonTitle.llRightImg.setVisibility(View.GONE);
        if(listener!=null){
            mBaseBinding.commonTitle.llRightText.setOnClickListener(listener);
        }
    }
    /**
     * 设置右侧图片
     */
    protected void setRightImg(int img, View.OnClickListener listener){
        mBaseBinding.commonTitle.llRightText.setVisibility(View.GONE);
        mBaseBinding.commonTitle.llRightImg.setVisibility(View.VISIBLE);
        if(img>0){
            mBaseBinding.commonTitle.ivRightImg.setImageResource(img);
        }
        if(listener!=null){
            mBaseBinding.commonTitle.llRightImg.setOnClickListener(listener);
        }
    }
    /**
     * 显示进度框
     * @param str
     */
    public final void showInfoProgressDialog(final String... str) {
        if (mProgressDialog == null) {
            mProgressDialog = new KProgressHUD(mContext);
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
     * 显示错误页面或正常页面
     */
    protected void showErroView(boolean isShow){
        if(isShow){
            mBaseBinding.llErrorRefresh.setVisibility(View.VISIBLE);
            mBindingView.getRoot().setVisibility(View.GONE);
        }else {
            mBaseBinding.llErrorRefresh.setVisibility(View.GONE);
            mBindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }
    /**
     * 页面是否有数据
     * @param isShow   true有数据  false没数据
     */
    protected void showNoData(boolean isShow){
        if(!isShow){
            mBaseBinding.llNodata.setVisibility(View.VISIBLE);
            mBindingView.getRoot().setVisibility(View.GONE);
        }else {
            mBaseBinding.llNodata.setVisibility(View.GONE);
            mBindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }
    /**
     * 移除网络请求
     */
    public void removeSubscription() {
        if (this.mCompositeSubscription != null && !mCompositeSubscription.isDisposed()) {
            this.mCompositeSubscription.dispose();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        hideInfoProgressDialog();
        removeSubscription();
    }
}
