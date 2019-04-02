package com.cyrj.pharmacymessage.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.util.Date;

public class PickerUtil {
//    Calendar startDate = Calendar.getInstance();
//    Calendar endDate = Calendar.getInstance();
    //endDate.set(2020,1,1);

    /**
     * 时间选择器
     */
    public void showTimePickView(Context context) {
//        startDate.set(2010,0,1);
//        endDate.set(2030,11,1);
        TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                returnData.data(date);
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(18)
                .setTitleSize(20)//标题文字大小
                .setTitleText(null)//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setSubmitColor(Color.parseColor("#666666"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#666666"))//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
//                .setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        pvTime.show();
    }
    private ReturnData returnData;
    public void setReturnData(ReturnData returnData){
        this.returnData=returnData;
    }
    public interface ReturnData{
        void data(Date date);
    }
}
