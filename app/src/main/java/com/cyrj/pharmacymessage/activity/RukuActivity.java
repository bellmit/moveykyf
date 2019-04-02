package com.cyrj.pharmacymessage.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;

import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.adapter.RukuInsertAdapter;
import com.cyrj.pharmacymessage.barcode.BarcodeActions;
import com.cyrj.pharmacymessage.base.BaseActivity;
import com.cyrj.pharmacymessage.pojo.RuKu02;
import com.cyrj.pharmacymessage.bean.YpcdBean;
import com.cyrj.pharmacymessage.custom.AddTerminalDialog;
import com.cyrj.pharmacymessage.databinding.ActivityRukuBinding;
import com.cyrj.pharmacymessage.help.RecycleViewDivider;
import com.cyrj.pharmacymessage.help.SettingPrefUtils;
import com.cyrj.pharmacymessage.help.UIUtil;
import com.cyrj.pharmacymessage.port.RxView;
import com.cyrj.pharmacymessage.presenter.RukuPresenter;
import com.cyrj.pharmacymessage.utils.SumPriceUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class RukuActivity extends BaseActivity<RukuPresenter,ActivityRukuBinding> implements RxView<YpcdBean>{

    private RecyclerView rukuRecycleview;
    private ArrayList<RuKu02> ruKu02List;
    private RukuInsertAdapter adapter;
    private AddTerminalDialog addTerminalDialog;
    private int mobiflcationPosition;
    private int yksb;
    private Integer dwxh;
    private String dwmc;
    private String jgid;
    private RuKu02 ruKu02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruku);
        EventBus.getDefault().register(this);
    }

    @Override
    public BroadcastReceiver getBarcodeReceiver() {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("lllllll",intent.getStringExtra(BarcodeActions.EXTRA_BARCODE_DATA));
            }
        };
    }

    @Override
    public void initLisener() {
        mBindingView.btnAddRuku.setOnClickListener(v -> {
            //单位序号
            ruKu02 = new RuKu02();
            ruKu02.setYPXH(520);//药品序号
            ruKu02.setYPCD(878);//药品产地
            ruKu02.setRKSL(200);//入库数量
            ruKu02.setYPCJ("康恩贝");//药品厂家
            ruKu02.setYPPH("12345");//药品批号
            ruKu02.setYPXQ("2020-01-01");//药品效期




            getYpcd(Integer.valueOf(jgid), ruKu02.getYPXH(), ruKu02.getYPCD());

        });
        setRightTile("下一步",v -> {
            startActivity(new Intent(getApplicationContext(),RukuInsertActivity.class)
                    .putParcelableArrayListExtra("ruKu02List",ruKu02List)
                    .putExtra("XTSB",yksb)
                    .putExtra("DWXH",dwxh)
                    .putExtra("DWMC",dwmc));
        });
        adapter.setBtnOnClickListener(new RukuInsertAdapter.BtnOnClickListener() {
            @Override
            public void modiflcation(int position) {
                mobiflcationPosition=position;
                addTerminalDialog.show();
            }

            @Override
            public void delete(int position) {
                ruKu02List.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        addTerminalDialog.setYesOnclickListener("确定",inputCount -> {
            ruKu02List.get(mobiflcationPosition).setRKSL(Integer.valueOf(inputCount));
            adapter.notifyItemChanged(mobiflcationPosition);
        });

    }

    @Override
    public void initView() {
        mPresenter.onView(this);
        setTitile("入库详情");
        rukuRecycleview = mBindingView.rukuRecycleview;
        rukuRecycleview.setLayoutManager(new LinearLayoutManager(this));
        rukuRecycleview.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL));
        addTerminalDialog = new AddTerminalDialog(this);
        addTerminalDialog.setMessage("修改数量");
        addTerminalDialog.setInputType(InputType.TYPE_CLASS_NUMBER);
        initData();
    }
    private void initData(){
        jgid = MyApplication.getString(SettingPrefUtils.AGENCY_ID_KEY,null);
        dwxh = 2;
        dwmc = "康恩贝";
        Intent intent=getIntent();
        yksb = intent.getIntExtra("XTSB",0);
        ruKu02List= new ArrayList<>();
        adapter = new RukuInsertAdapter(R.layout.adapter_ruku_insert, ruKu02List);
        rukuRecycleview.setAdapter(adapter);
    }
    private void getYpcd(int jgid,int ypxh,int ypcd){
        mPresenter.getYpcd(jgid, ypxh, ypcd);
    }
    @Override
    public void success(YpcdBean dateil) {
        ruKu02.setYPMC(dateil.getYPMC());
        ruKu02.setYPGG(dateil.getYPGG());
        ruKu02.setYPDW(dateil.getYPDW());
        String jhjg=dateil.getJHJG();
        String pfjg=dateil.getPFJG();
        String lsjg=dateil.getLSJG();
        ruKu02.setJHJG(Double.valueOf(jhjg));
        ruKu02.setPFJG(Double.valueOf(pfjg));
        ruKu02.setLSJG(Double.valueOf(lsjg));
        ruKu02.setJGID(Integer.valueOf(jgid));
        ruKu02.setXTSB(yksb);//11

        //进货合计（进货数量*进货价格）
        String jhhj=SumPriceUtil.getRidePrice(String.valueOf(ruKu02.getRKSL()),jhjg);
        ruKu02.setJHHJ(Double.valueOf(jhhj));

        ruKu02.setTYPE(0);//库存性质
        //批发金额，数量*批发价格
        String pfje=SumPriceUtil.getRidePrice(String.valueOf(ruKu02.getRKSL()),pfjg);
        ruKu02.setPFJE(Double.valueOf(pfje));
        //零售金额。数量*零售价格
        String lsje=SumPriceUtil.getRidePrice(String.valueOf(ruKu02.getRKSL()),lsjg);
        ruKu02.setLSJE(Double.valueOf(lsje));
        ruKu02.setBZLJ(Double.valueOf(lsjg));
        ruKu02.setKCSB(0);//库存识别初始为0
//        ruKu02.setSBXH(0);//识别序号
        ruKu02.setHGSL(ruKu02.getRKSL());//
        //准备插入验收单号,验收工号，验收日期，付款工号，凭证号码，付款日期，折让金额
        //付款金额，已付金额，付款公式，药品扣率，基本药物标志，计划识别序号
        ruKu02.setYSDH(null);
        ruKu02.setYSGH(null);
        ruKu02.setYSRQ(null);
        ruKu02.setFKGH(null);
        ruKu02.setPZHM(null);
        ruKu02.setFKRQ(null);
        ruKu02.setFPHM(null);
        ruKu02.setZRJE(null);
        ruKu02.setSHHM(null);
        //不为空
        ruKu02.setFKJE(Double.valueOf(jhhj));
        ruKu02.setYFJE(Double.valueOf(jhhj));
        ruKu02.setFKGS(null);
        ruKu02.setYPKL(0.00);
        ruKu02.setJBYWBZ(0);
        ruKu02.setJHSBXH(null);


        ruKu02List.add(ruKu02);
        adapter.notifyItemInserted(0);
    }

    @Override
    public void fail(String errMessage) {
        UIUtil.showToast(errMessage);
    }
    @Subscribe
    public void onEvent(String messageEvent){
        if (messageEvent.equals(getResources().getString(R.string.closeRukuActivity))){
            //如果消息时关闭此activity
            finish();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
