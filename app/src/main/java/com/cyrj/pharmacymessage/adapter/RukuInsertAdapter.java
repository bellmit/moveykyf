package com.cyrj.pharmacymessage.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyrj.pharmacymessage.MyApplication;
import com.cyrj.pharmacymessage.pojo.RuKu02;
import com.cyrj.pharmacymessage.viewholder.RukuInsertViewHolder;

import java.util.List;

public class RukuInsertAdapter extends RecyclerView.Adapter<RukuInsertViewHolder>{
    private int layoutResId;
    private List<RuKu02> data;
    public RukuInsertAdapter(int layoutResId, @Nullable List<RuKu02> data) {
        this.layoutResId=layoutResId;
        this.data=data;
    }

    private BtnOnClickListener btnOnClickListener;
    public void setBtnOnClickListener(BtnOnClickListener btnOnClickListener){
        this.btnOnClickListener=btnOnClickListener;
    }

    @NonNull
    @Override
    public RukuInsertViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(MyApplication.getContext()).inflate(layoutResId,viewGroup,false);
        RukuInsertViewHolder rukuInsertViewHolder=new RukuInsertViewHolder(view);
        return rukuInsertViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RukuInsertViewHolder helper, int position) {
        helper.tv_batch_number.setText(data.get(position).getYPPH());
        helper.tv_YP_time.setText(data.get(position).getYPXQ());
        helper.tv_drug_name.setText(data.get(position).getYPMC());
        helper.tv_drug_guige.setText(data.get(position).getYPGG());
        helper.tv_drug_unit.setText(data.get(position).getYPDW());
        helper.tv_drug_number.setText(String.valueOf(data.get(position).getRKSL())+data.get(position).getYPDW());
        helper.my_lives_revise.setOnClickListener(v -> {
            btnOnClickListener.modiflcation(position);
            helper.sml_layout.smoothClose();
        });
        helper.my_lives_delete.setOnClickListener(v -> {
            btnOnClickListener.delete(position);
            helper.sml_layout.smoothClose();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface BtnOnClickListener{
        void modiflcation(int position);
        void delete(int position);
    }

}
