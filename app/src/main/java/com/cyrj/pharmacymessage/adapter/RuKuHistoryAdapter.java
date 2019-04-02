package com.cyrj.pharmacymessage.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cyrj.pharmacymessage.bean.RukuHistoryBean;
import com.cyrj.pharmacymessage.viewholder.RuKuHistoryHolder;

public class RuKuHistoryAdapter extends BaseQuickAdapter<RukuHistoryBean,RuKuHistoryHolder>{


    public RuKuHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(RuKuHistoryHolder helper, RukuHistoryBean item) {
        helper.tv_ruku_order.setText(item.getRKDH());
        if (item.getRKFS().equals("1")){
            helper.tv_ruku_way.setText("购入");
        }else if (item.getRKFS().equals("2")){
            helper.tv_ruku_way.setText("赠送");
        }else if (item.getRKFS().equals("3")){
            helper.tv_ruku_way.setText("盘盈");
        }else if (item.getRKFS().equals("4")){
            helper.tv_ruku_way.setText("自制");
        }else if (item.getRKFS().equals("5")){
            helper.tv_ruku_way.setText("招标");
        }else if (item.getRKFS().equals("6")){
            helper.tv_ruku_way.setText("其他");
        }

        helper.ruku_time.setText(item.getRKRQ());
        if (item.getRKPB()==0){
            //已保存尚未确认
            helper.tv_rkpb.setText("尚未入库");
            helper.tv_rkpb.setTextColor(Color.parseColor("#f90101"));
        }else{
            //已保存也已确认
            helper.tv_rkpb.setText("已确认入库");
            helper.tv_rkpb.setTextColor(Color.parseColor("#05CEAB"));
        }
    }
}
