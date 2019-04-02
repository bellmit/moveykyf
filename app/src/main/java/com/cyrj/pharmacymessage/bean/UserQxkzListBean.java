package com.cyrj.pharmacymessage.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UserQxkzListBean implements Parcelable{
    private String YWLB;
    private Integer KSDM;

    public String getYWLB() {
        return YWLB;
    }

    public void setYWLB(String YWLB) {
        this.YWLB = YWLB;
    }

    public Integer getKSDM() {
        return KSDM;
    }

    public void setKSDM(Integer KSDM) {
        this.KSDM = KSDM;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.YWLB);
        dest.writeInt(this.KSDM);
    }

    public UserQxkzListBean() {
    }

    protected UserQxkzListBean(Parcel in) {
        this.YWLB = in.readString();
        this.KSDM = in.readInt();
    }

    public static final Creator<UserQxkzListBean> CREATOR = new Creator<UserQxkzListBean>() {
        @Override
        public UserQxkzListBean createFromParcel(Parcel source) {
            return new UserQxkzListBean(source);
        }

        @Override
        public UserQxkzListBean[] newArray(int size) {
            return new UserQxkzListBean[size];
        }
    };
}
