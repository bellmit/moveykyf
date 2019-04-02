package com.cyrj.pharmacymessage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.base.BaseActivity;
import com.cyrj.pharmacymessage.bean.BaseBean;
import com.cyrj.pharmacymessage.pojo.RuKu01;
import com.cyrj.pharmacymessage.pojo.RuKu02;
import com.cyrj.pharmacymessage.bean.RukuWayBean;
import com.cyrj.pharmacymessage.custom.spinner.MaterialSpinner;
import com.cyrj.pharmacymessage.databinding.ActivityRuKuInsertBinding;
import com.cyrj.pharmacymessage.help.SettingPrefUtils;
import com.cyrj.pharmacymessage.help.UIUtil;
import com.cyrj.pharmacymessage.port.RxView;
import com.cyrj.pharmacymessage.presenter.RukuPresenter;
import com.cyrj.pharmacymessage.utils.DateUtil;
import com.cyrj.pharmacymessage.utils.PickerUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 入库的第二步
 */
public class RukuInsertActivity extends BaseActivity<RukuPresenter,ActivityRuKuInsertBinding>implements RxView<BaseBean<List<RukuWayBean>>> {

    private MaterialSpinner materialSpinner;
    private List<String> buyWayList;
    private String jgid;
    private MaterialSpinner buyWaySpinner;
    private List<String> rukuWayList;
    private int yksb;
    private String ruku_way;
    private String buy_way;
    private int rkfs;
    private List<RukuWayBean> rukuWayBeanList;
    private int dwxh;
    private PickerUtil pickerUtil;
    private String cgrq;
    private String lrrq;
    private String rkrq;
    private String userId;
    private int djfs;
    private String djgs;
    private ArrayList<RuKu02> ruKu02List;
    private Integer rkdh;
    private String dwmc;
    private RuKu01 ruKu01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ru_ku_insert);

    }

    @Override
    public void initView() {
        materialSpinner = mBindingView.spinner;
        buyWaySpinner = mBindingView.buyWaySpinner;
        mPresenter.onView(this);
        setTitile("入库内容");
        initData();
    }
    @Override
    public void initLisener() {
        materialSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                buy_way = buyWayList.get(position);
            }
        });
        buyWaySpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                ruku_way = rukuWayList.get(position);
                for (int i=0;i<rukuWayBeanList.size();i++){
                    if (ruku_way.equals(rukuWayBeanList.get(i).getFSMC())){
                        rkfs=rukuWayBeanList.get(i).getRKFS();
                        djfs = rukuWayBeanList.get(i).getDJFS();
                        djgs = rukuWayBeanList.get(i).getDJGS();
                        rkdh = rukuWayBeanList.get(i).getRKDH()+1;
                        mBindingView.tvRukuOrder.setText(String.valueOf(rkdh));
                    }
                }
            }
        });
        mBindingView.btnSave.setOnClickListener(v -> {
            String fdjs=mBindingView.tvFDJS.getText().toString().trim();
            String rkbz=mBindingView.tvRemark.getText().toString().trim();
            ruKu01.setRKDH(rkdh);
            ruKu01.setJGID(Integer.valueOf(jgid));
            ruKu01.setXTSB(yksb);
            ruKu01.setRKFS(rkfs);
            if (buy_way.equals("货到票未到")){
                ruKu01.setPWD(0);
            }else if (buy_way.equals("货到票已到")){
                ruKu01.setPWD(1);
            }
            ruKu01.setDWXH(dwxh);
            ruKu01.setCWPB(0);
            if (TextUtils.isEmpty(fdjs)){
                ruKu01.setFDJS(null);
            }else{
                ruKu01.setFDJS(Integer.valueOf(fdjs));
            }
            ruKu01.setRKBZ(rkbz);
            ruKu01.setRKPB(0);//入库判别1,实物入库，0非实物入库  入库时写入0，加库存时改为1
            ruKu01.setCGRQ(cgrq);
            ruKu01.setLRRQ(lrrq);
            ruKu01.setRKRQ(rkrq);
            ruKu01.setCGGH(userId);
            ruKu01.setCZGH(userId);
            ruKu01.setDJFS(djfs);
            ruKu01.setDJGS(djgs);
            if (ruKu02List!=null) {
                for (int i = 0; i <ruKu02List.size();i++){
                    ruKu02List.get(i).setRKFS(rkfs);
                    ruKu02List.get(i).setRKDH(rkdh);
                    ruKu02List.get(i).setDJFS(djfs);
                    ruKu02List.get(i).setDJGS(djgs);
                }
            }
            ruKu01.setRuKu02(ruKu02List);
            mPresenter.ruku(ruKu01);
        });
        //选择日期
        mBindingView.llCgrq.setOnClickListener(v -> {
            pickerUtil.showTimePickView(this);
        });
        //选择日期的回调
        pickerUtil.setReturnData(new PickerUtil.ReturnData() {
            @Override
            public void data(Date date) {
                String selectTime=DateUtil.dateToStr(date);
                cgrq = selectTime;
                mBindingView.tvBuyDate.setText(selectTime);
            }
        });
    }
    private void initData(){

        pickerUtil = new PickerUtil();
        ruKu01 = new RuKu01();
        Intent intent=getIntent();
        yksb = intent.getIntExtra("XTSB",0);
        dwxh = intent.getIntExtra("DWXH",0);
        dwmc = intent.getStringExtra("DWMC");
        ruKu02List = intent.getParcelableArrayListExtra("ruKu02List");//传递过来的list
        jgid = MyApplication.getString(SettingPrefUtils.AGENCY_ID_KEY,null);
        userId = MyApplication.getString(SettingPrefUtils.USERID,null);
        buy_way="货到票未到";
        buyWayList = new ArrayList<>();
        rukuWayList = new ArrayList<>();
        //对入库的初始化数据进行请求
        mPresenter.getRukuWay(jgid,yksb);
        buyWayList.add("货到票未到");
        buyWayList.add("货到票已到");
        materialSpinner.setItems(buyWayList);
        cgrq=DateUtil.getStringDateShort2();//默认采购日期
        mBindingView.tvBuyDate.setText(cgrq);
        mBindingView.tvGHDW.setText(dwmc);
        //默认录入日期
        lrrq = DateUtil.getStringDateShort1();
        rkrq = DateUtil.getStringDateShort1();
    }

    @Override
    public void success(BaseBean<List<RukuWayBean>> dateil) {
        if (dateil.getType()==0) {
            //获取请求
            rukuWayBeanList = dateil.getData();
            for (int i = 0; i < rukuWayBeanList.size(); i++) {
                rukuWayList.add(rukuWayBeanList.get(i).getFSMC());
            }
            buyWaySpinner.setItems(rukuWayList);
            rkfs = rukuWayBeanList.get(0).getRKFS();
            djfs = rukuWayBeanList.get(0).getDJFS();
            djgs = rukuWayBeanList.get(0).getDJGS();
            rkdh = rukuWayBeanList.get(0).getRKDH() + 1;
            mBindingView.tvRukuOrder.setText(String.valueOf(rkdh));
        }else{
            //入库返回的
            UIUtil.showToast("保存成功");
            //关闭上个activity
            String close=getResources().getString(R.string.closeRukuActivity);
            String refresh=getResources().getString(R.string.refreshRukuHistoryFragment);
            EventBus.getDefault().post(close);
            EventBus.getDefault().post(refresh);
            finish();
        }
    }

    @Override
    public void fail(String errMessage) {
        UIUtil.showToast(errMessage);
    }
}
