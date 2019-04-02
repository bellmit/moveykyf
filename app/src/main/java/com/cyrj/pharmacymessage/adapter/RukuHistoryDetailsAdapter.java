package com.cyrj.pharmacymessage.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cyrj.pharmacymessage.bean.RukuDetailsBean;
import com.cyrj.pharmacymessage.viewholder.RuKuDetailsViewHolder;

import java.util.List;

public class RukuHistoryDetailsAdapter extends BaseQuickAdapter<RukuDetailsBean,RuKuDetailsViewHolder>{
    public RukuHistoryDetailsAdapter(int layoutResId, @Nullable List<RukuDetailsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(RuKuDetailsViewHolder helper, RukuDetailsBean item) {
        helper.tv_drug_name.setText(item.getYPMC());
        helper.tv_drug_guige.setText(item.getYPGG());
        helper.tv_drug_unit.setText(item.getYPDW());
        helper.tv_drug_number.setText(String.valueOf(item.getRKSL())+item.getYPDW());
        if (TextUtils.isEmpty(item.getYPPH())){
            helper.tv_pihao.setText("空");
        }else{
            helper.tv_pihao.setText(item.getYPPH());
        }
        if (TextUtils.isEmpty(item.getYPXQ())){
            helper.tv_xiaoqi.setText("空");
        }else{
            helper.tv_xiaoqi.setText(item.getYPXQ());
        }

        helper.tv_pifa_price.setText(item.getPFJG());
        helper.tv_retail_price.setText(item.getLSJG());
    }
}
