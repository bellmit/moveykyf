package com.cyrj.pharmacymessage.fragment;


import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.adapter.ChukuHistoryAdapter;
import com.cyrj.pharmacymessage.base.BaseFragment;
import com.cyrj.pharmacymessage.bean.BaseBean;
import com.cyrj.pharmacymessage.bean.ChukuHistoryBean;
import com.cyrj.pharmacymessage.bean.YkTypeBean;
import com.cyrj.pharmacymessage.bean.YpxxBean;
import com.cyrj.pharmacymessage.custom.spinner.MaterialSpinner;
import com.cyrj.pharmacymessage.databinding.FragmentYkremoveBinding;
import com.cyrj.pharmacymessage.help.RecycleViewDivider;
import com.cyrj.pharmacymessage.help.SettingPrefUtils;
import com.cyrj.pharmacymessage.help.UIUtil;
import com.cyrj.pharmacymessage.port.RxView;
import com.cyrj.pharmacymessage.presenter.YkRemovePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 *药库出库
 */
@SuppressLint("ValidFragment")
public class YKRemoveFragment extends BaseFragment<YkRemovePresenter,FragmentYkremoveBinding> implements RxView<BaseBean>{
    private MaterialSpinner materialSpinner;
    private List<YkTypeBean> tklmList;
    private List<String> yklbList;
    private int yksb;
    private String userId;
    private String jgid;
    private SwipeRefreshLayout ck_swipe;
    private RecyclerView ck_recycleView;
    private YpxxBean ypInfoBeanList;
    private ChukuHistoryAdapter chukuHistoryAdapter;

    public YKRemoveFragment() { }

    public YKRemoveFragment(List<YkTypeBean> tklmList){
        this.tklmList=tklmList;
    }
    @Override
    public int setContent() {
        return R.layout.fragment_ykremove;

    }

    @Override
    protected void initView() {
        hideBackImg();
        showMenu();
        setTitle("药库出库");
        mPresenter.onView(this);
        if (tklmList.size()>0){
            mBaseBinding.commonTitle.llSelectYklb.setVisibility(View.VISIBLE);

        }
        materialSpinner = mBaseBinding.commonTitle.spinner;
        ck_swipe = mBindingView.ckSwipe;
        ck_recycleView = mBindingView.ckRecycleview;
        ck_recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ck_recycleView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        initData();
    }
    private void initData(){
        yklbList = new ArrayList<>();
        for (YkTypeBean ykTypeBean:tklmList){
            yklbList.add(ykTypeBean.getYKMC());
        }
        yksb = tklmList.get(0).getYKSB();
        materialSpinner.setItems(yklbList);
        materialSpinner.setSelectedIndex(0);
        userId = MyApplication.getString(SettingPrefUtils.USERID,null);
        jgid = MyApplication.getString(SettingPrefUtils.AGENCY_ID_KEY,null);
        chukuHistoryAdapter = new ChukuHistoryAdapter(R.layout.adapter_chuku_history);
        ck_recycleView.setAdapter(chukuHistoryAdapter);
        mPresenter.getCkhistory(userId,Integer.valueOf(jgid),yksb);
//        mPresenter.getYpxxList();
    }
    @Override
    protected void initPresenter() {
        materialSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                for (YkTypeBean ykTypeBean:tklmList){
                    if (yklbList.get(position).equals(ykTypeBean.getYKMC())){
                        yksb=ykTypeBean.getYKSB();
                    }
                }
                mPresenter.getCkhistory(userId,Integer.valueOf(jgid),yksb);
            }
        });

    }

    @Override
    public void success(BaseBean data) {
        if (ck_swipe.isRefreshing()){
            ck_swipe.setRefreshing(false);
        }
        if (data.getType()==0) {
            //如果返回的时历史列表
            ChukuHistoryBean chukuHistoryBeans = (ChukuHistoryBean) data.getData();
            if (chukuHistoryBeans.getCklsList().size() > 0) {
                //如果数据部位0
                chukuHistoryAdapter.setNewData(chukuHistoryBeans.getCklsList());
                chukuHistoryAdapter.notifyItemRangeChanged(0, chukuHistoryBeans.getCklsList().size() - 1);
            } else {
                UIUtil.showToast("暂无数据");
            }
        }else{
            //如果返回的是药品信息列表
            ypInfoBeanList = (YpxxBean) data.getData();
        }
    }

    @Override
    public void fail(String errMessage) {

    }
}
