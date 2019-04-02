package com.cyrj.pharmacymessage.viewholder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.cyrj.pharmacymessage.R;

public class RuKuDetailsViewHolder extends BaseViewHolder{

    public final TextView tv_drug_name;
    public final TextView tv_drug_guige;
    public final TextView tv_drug_unit;
    public final TextView tv_drug_number;
    public final TextView tv_pifa_price;
    public final TextView tv_retail_price;
    public final TextView tv_pihao;
    public final TextView tv_xiaoqi;

    public RuKuDetailsViewHolder(View view) {
        super(view);
        tv_drug_name = view.findViewById(R.id.tv_drug_name);
        tv_drug_guige = view.findViewById(R.id.tv_drug_guige);
        tv_drug_unit = view.findViewById(R.id.tv_drug_unit);
        tv_drug_number = view.findViewById(R.id.tv_drug_number);
        tv_pifa_price = view.findViewById(R.id.tv_pifa_price);
        tv_retail_price = view.findViewById(R.id.tv_retail_price);
        tv_pihao = view.findViewById(R.id.tv_pihao);
        tv_xiaoqi = view.findViewById(R.id.tv_xiaoqi);
    }
}
