package com.cyrj.pharmacymessage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.adapter.RukuHistoryDetailsAdapter;
import com.cyrj.pharmacymessage.base.BaseActivity;
import com.cyrj.pharmacymessage.bean.RukuDetailsBean;
import com.cyrj.pharmacymessage.bean.RukuHistoryBean;
import com.cyrj.pharmacymessage.bean.YpxxBean;
import com.cyrj.pharmacymessage.custom.CustomDialog;
import com.cyrj.pharmacymessage.databinding.ActivityRukuDetailsBinding;
import com.cyrj.pharmacymessage.help.RecycleViewDivider;
import com.cyrj.pharmacymessage.help.UIUtil;
import com.cyrj.pharmacymessage.port.RxView;
import com.cyrj.pharmacymessage.presenter.RuKuHistoryPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 入库历史的详情页面
 */
public class RukuDetailsActivity extends BaseActivity<RuKuHistoryPresenter,ActivityRukuDetailsBinding> implements RxView<List<RukuDetailsBean>> {

    private String rkfs;
    private String rkdh;
    private int yksb;
    private String jgid;
    private TextView tv_ruku_way;
    private RecyclerView recycleviewHistoryDetails;
    private RukuHistoryDetailsAdapter rukuHistoryDetailsAdapter;
    private int rkpb;
    private YpxxBean ypInfoBeans;
    private CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruku_details);
        mPresenter.onView(this);
    }

    @Override
    public void initLisener() {
        if (rkpb==0) {
            setRightTile("确认入库", v -> {
                //确认入库存
                customDialog.show();
            });
        }
        customDialog.setYesOnclickListener("入库", new CustomDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                insertKcmx();
                customDialog.dismiss();
            }
        });
        customDialog.setNoOnclickListener("取消", new CustomDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                customDialog.dismiss();
            }
        });
    }

    @Override
    public void initView() {
        mBindingView.recycleviewHistoryDetails.setLayoutManager(new LinearLayoutManager(this));
        mBindingView.recycleviewHistoryDetails.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL));
        tv_ruku_way = mBindingView.tvRukuWay;
        recycleviewHistoryDetails = mBindingView.recycleviewHistoryDetails;
        setTitile("入库详情");
        customDialog = new CustomDialog(this);
        customDialog.setMessage("是否确认入库");
        initData();
    }
    private void initData(){
        Intent intent=getIntent();
        yksb = intent.getIntExtra("yksb",0);
        jgid = intent.getStringExtra("jgid");
        RukuHistoryBean rukuHistoryBean= intent.getParcelableExtra("rukuBean");
        ypInfoBeans = intent.getParcelableExtra("ypxxBean");
        rkfs = rukuHistoryBean.getRKFS();
        rkdh = rukuHistoryBean.getRKDH();

        String cgrq = rukuHistoryBean.getCGRQ();
        int pwd=rukuHistoryBean.getPWD();
        Integer fdjs=rukuHistoryBean.getFDJS();
        rkpb = rukuHistoryBean.getRKPB();
        //
        if (rkfs.equals("1")){
            tv_ruku_way.setText("购入");
        }else if (rkfs.equals("2")){
            tv_ruku_way.setText("赠送");
        }else if (rkfs.equals("3")){
            tv_ruku_way.setText("盘盈");
        }else if (rkfs.equals("4")){
            tv_ruku_way.setText("自制");
        }else if (rkfs.equals("5")){
            tv_ruku_way.setText("招标");
        }else if (rkfs.equals("6")){
            tv_ruku_way.setText("其他");
        }
        mBindingView.tvCaigouTime.setText(cgrq);
        if (pwd==0){
            mBindingView.tvBuyWay.setText("货到票未到");
        }else{
            mBindingView.tvBuyWay.setText("货到票已到");
        }
        if (fdjs!=null) {
            mBindingView.tvFudanNum.setText(String.valueOf(fdjs));
        }else{
            mBindingView.tvFudanNum.setText("无");
        }
        mPresenter.selectRukuHistoryDetails(rkfs,rkdh,yksb,jgid);

    }
    private void insertKcmx(){

    }
    @Override
    public void success(List<RukuDetailsBean> dateil) {
        Observable.create(new ObservableOnSubscribe<List<RukuDetailsBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<RukuDetailsBean>> emitter) throws Exception {
                for (int i=0;i<dateil.size();i++){
                    for (int j=0;j<ypInfoBeans.getYpmlList().size();j++){
                        if (dateil.get(i).getYPXH().equals(ypInfoBeans.getYpmlList().get(j).getYPXH())){
                            dateil.get(i).setYPMC(ypInfoBeans.getYpmlList().get(j).getYPMC());
                            dateil.get(i).setYPDW(ypInfoBeans.getYpmlList().get(j).getYPDW());
                            dateil.get(i).setYPGG(ypInfoBeans.getYpmlList().get(j).getYPGG());
                        }
                    }
                }
                emitter.onNext(dateil);
            }
        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(new Observer<List<RukuDetailsBean>>() {
              @Override
              public void onSubscribe(Disposable d) {

              }

              @Override
              public void onNext(List<RukuDetailsBean> rukuHistoryDetailsBean) {
                  if (rukuHistoryDetailsAdapter == null) {
                      rukuHistoryDetailsAdapter = new RukuHistoryDetailsAdapter(R.layout.adapter_ruku_details, rukuHistoryDetailsBean);
                      recycleviewHistoryDetails.setAdapter(rukuHistoryDetailsAdapter);
                  } else {
                      rukuHistoryDetailsAdapter.notifyItemRangeChanged(0,rukuHistoryDetailsBean.size()-1);
                  }
              }

              @Override
              public void onError(Throwable e) { }

              @Override
              public void onComplete() { }
          });

    }
    @Override
    public void fail(String errMessage) {
        UIUtil.showToast(errMessage);
    }
}
