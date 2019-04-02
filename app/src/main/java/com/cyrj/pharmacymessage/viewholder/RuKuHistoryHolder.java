package com.cyrj.pharmacymessage.viewholder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.cyrj.pharmacymessage.R;

public class RuKuHistoryHolder extends BaseViewHolder{

    public TextView tv_ruku_order;
    public TextView tv_ruku_way;
    public TextView ruku_time;
    public TextView tv_rkpb;

    public RuKuHistoryHolder(View view) {
        super(view);
        tv_ruku_order = view.findViewById(R.id.tv_ruku_order);
        tv_ruku_way = view.findViewById(R.id.tv_ruku_way);
        ruku_time = view.findViewById(R.id.ruku_time);
        tv_rkpb = view.findViewById(R.id.tv_rkpb);
    }
}
