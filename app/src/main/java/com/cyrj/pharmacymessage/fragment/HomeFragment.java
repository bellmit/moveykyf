package com.cyrj.pharmacymessage.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.activity.RukuActivity;
import com.cyrj.pharmacymessage.activity.RukuDetailsActivity;
import com.cyrj.pharmacymessage.adapter.RuKuHistoryAdapter;
import com.cyrj.pharmacymessage.base.BaseFragment;
import com.cyrj.pharmacymessage.bean.BaseBean;
import com.cyrj.pharmacymessage.bean.RukuHistoryBean;
import com.cyrj.pharmacymessage.bean.YkTypeBean;
import com.cyrj.pharmacymessage.bean.YpxxBean;
import com.cyrj.pharmacymessage.custom.spinner.MaterialSpinner;
import com.cyrj.pharmacymessage.databinding.FragmentHomeBinding;
import com.cyrj.pharmacymessage.help.RecycleViewDivider;
import com.cyrj.pharmacymessage.help.SettingPrefUtils;
import com.cyrj.pharmacymessage.help.UIUtil;
import com.cyrj.pharmacymessage.port.RxView;
import com.cyrj.pharmacymessage.presenter.RuKuHistoryPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 药库历史
 */
@SuppressLint("ValidFragment")
public class HomeFragment extends BaseFragment<RuKuHistoryPresenter,FragmentHomeBinding> implements RxView<BaseBean>{


    private RuKuHistoryAdapter adapter;
    private MaterialSpinner materialSpinner;
    private int yksb;
    private List<YkTypeBean> tklmList;
    private List<String> yaoku;
    private String userId;
    private String jgid;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<RukuHistoryBean> rukuHistoryBeans;
    private YpxxBean ypInfoBeanList;

    public HomeFragment() { }

    public HomeFragment(List<YkTypeBean> tklmList){
        this.tklmList=tklmList;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        hideBackImg();
        showMenu();
        setTitle(getResources().getString(R.string.in_storage));
        mPresenter.onView(this);
        yaoku = new ArrayList<>();
        materialSpinner = mBaseBinding.commonTitle.spinner;
        swipeRefreshLayout = mBindingView.swiperefreshlayout;
        mBindingView.ykRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBindingView.ykRecycle.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));

        if (tklmList.size()>0){
            mBaseBinding.commonTitle.llSelectYklb.setVisibility(View.VISIBLE);

        }
        adapter = new RuKuHistoryAdapter(R.layout.adapter_ruku_history);
        mBindingView.ykRecycle.setAdapter(adapter);
        adapter.setEnableLoadMore(false);//关闭上拉加载

        initData();
    }
    private void initData(){

        for (YkTypeBean ykTypeBean:tklmList){
            yaoku.add(ykTypeBean.getYKMC());
        }
        yksb=tklmList.get(0).getYKSB();
        materialSpinner.setItems(yaoku);
        materialSpinner.setSelectedIndex(0);
        userId = MyApplication.getString(SettingPrefUtils.USERID,null);
        jgid = MyApplication.getString(SettingPrefUtils.AGENCY_ID_KEY,null);
        mPresenter.selectRukuHistoryList(userId,jgid,yksb);//请求历史列表
        mPresenter.getYpxxList();
    }
    @Override
    protected void initPresenter() {
        mBindingView.llRuku.setOnClickListener(v -> {
            //跳转入库
            startActivity(new Intent(MyApplication.getContext(), RukuActivity.class)
            .putExtra("XTSB",yksb));
        });
        materialSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                for (YkTypeBean ykTypeBean:tklmList){
                    if (yaoku.get(position).equals(ykTypeBean.getYKMC())){
                        yksb=ykTypeBean.getYKSB();
                    }
                }
                mPresenter.selectRukuHistoryList(userId,jgid,yksb);
            }
        });

        adapter.setOnItemClickListener((adapter1, view, position) -> {
            //跳转到入库详情
            if (rukuHistoryBeans!=null&&ypInfoBeanList!=null) {

                startActivity(new Intent(getActivity(), RukuDetailsActivity.class)
                        .putExtra("yksb", yksb)
                        .putExtra("jgid", jgid)
                        .putExtra("rukuBean",rukuHistoryBeans.get(position))
                        .putExtra("ypxxBean",ypInfoBeanList));
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.selectRukuHistoryList(userId,jgid,yksb);
            }
        });

    }

    @Override
    public void success(BaseBean data) {
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
            if (data.getType()==0) {
            //如果返回的时历史列表
                rukuHistoryBeans = (List<RukuHistoryBean>) data.getData();
                if (rukuHistoryBeans.size() > 0) {
                    //如果数据部位0
                    adapter.setNewData(rukuHistoryBeans);
                    adapter.notifyItemRangeChanged(0, rukuHistoryBeans.size() - 1);
                } else {

                }
            }else{
                //如果返回的是药品信息列表
                ypInfoBeanList = (YpxxBean) data.getData();
            }
    }

    @Override
    public void fail(String errMessage) {
        UIUtil.showToast(errMessage);
    }
    @Subscribe
    public void onEvent(String messageEvent){
        if (messageEvent.equals(getResources().getString(R.string.refreshRukuHistoryFragment))) {
            mPresenter.selectRukuHistoryList(userId,jgid,yksb);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
