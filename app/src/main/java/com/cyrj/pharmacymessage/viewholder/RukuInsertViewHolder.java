package com.cyrj.pharmacymessage.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cyrj.pharmacymessage.R;
import com.cyrj.pharmacymessage.custom.SwipeMenuLayout;

public class RukuInsertViewHolder extends RecyclerView.ViewHolder{

    public TextView tv_batch_number;
    public TextView tv_YP_time;
    public TextView tv_drug_name;
    public TextView tv_drug_guige;
    public TextView tv_drug_unit;
    public TextView tv_drug_number;
    public TextView my_lives_revise;
    public TextView my_lives_delete;
    public SwipeMenuLayout sml_layout;
    public RukuInsertViewHolder(View view) {
        super(view);
        tv_batch_number = view.findViewById(R.id.tv_batch_number);
        tv_YP_time = view.findViewById(R.id.tv_YP_time);
        tv_drug_name = view.findViewById(R.id.tv_drug_name);
        tv_drug_guige = view.findViewById(R.id.tv_drug_guige);
        tv_drug_unit = view.findViewById(R.id.tv_drug_unit);
        tv_drug_number = view.findViewById(R.id.tv_drug_number);
        my_lives_revise = view.findViewById(R.id.my_lives_revise);
        my_lives_delete = view.findViewById(R.id.my_lives_delete);
        sml_layout = view.findViewById(R.id.sml_layout);
    }
}
