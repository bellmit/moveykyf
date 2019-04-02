package com.cyrj.pharmacymessage.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class YpxxBean implements Parcelable{
    List<YpInfoBean> ypmlList;

    public List<YpInfoBean> getYpmlList() {
        return ypmlList;
    }

    public void setYpmlList(List<YpInfoBean> ypmlList) {
        this.ypmlList = ypmlList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.ypmlList);
    }

    public YpxxBean() {
    }

    protected YpxxBean(Parcel in) {
        this.ypmlList = in.createTypedArrayList(YpInfoBean.CREATOR);
    }

    public static final Creator<YpxxBean> CREATOR = new Creator<YpxxBean>() {
        @Override
        public YpxxBean createFromParcel(Parcel source) {
            return new YpxxBean(source);
        }

        @Override
        public YpxxBean[] newArray(int size) {
            return new YpxxBean[size];
        }
    };
}
