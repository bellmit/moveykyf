package com.cyrj.pharmacymessage.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cyrj.pharmacymessage.bean.CKLS;
import com.cyrj.pharmacymessage.bean.ChukuHistoryBean;
import com.cyrj.pharmacymessage.viewholder.ChukuViewHolder;

public class ChukuHistoryAdapter extends BaseQuickAdapter<CKLS,ChukuViewHolder> {


    public ChukuHistoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(ChukuViewHolder helper, CKLS item) {
        helper.tv_chuku_order.setText(item.getCKDH());
        helper.chuku_date.setText(item.getCKRQ());
        helper.tv_yfsb.setText(item.getYFMC());
        helper.tv_chuku_way.setText(item.getCKFSMC());
    }

}
