package com.cyrj.pharmacymessage.viewholder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.cyrj.pharmacymessage.R;

public class ChukuViewHolder extends BaseViewHolder{

    public TextView tv_chuku_order;
    public TextView tv_yfsb;
    public TextView tv_chuku_way;
    public TextView chuku_date;

    public ChukuViewHolder(View view) {
        super(view);
        tv_chuku_order = view.findViewById(R.id.tv_chuku_order);
        tv_yfsb = view.findViewById(R.id.tv_yfsb);
        tv_chuku_way = view.findViewById(R.id.tv_chuku_way);
        chuku_date = view.findViewById(R.id.chuku_date);
    }
}
