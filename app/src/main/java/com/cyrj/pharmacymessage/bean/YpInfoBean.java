package com.cyrj.pharmacymessage.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 药品信息
 */
public class YpInfoBean implements Parcelable{
    private String YPMC;

    private String YPDW;

    private String YPGG;

    private Integer YPXH;

    public Integer getYPXH() {
        return YPXH;
    }

    public void setYPXH(Integer YPXH) {
        this.YPXH = YPXH;
    }

    public String getYPMC() {
        return YPMC;
    }

    public void setYPMC(String YPMC) {
        this.YPMC = YPMC;
    }

    public String getYPDW() {
        return YPDW;
    }

    public void setYPDW(String YPDW) {
        this.YPDW = YPDW;
    }

    public String getYPGG() {
        return YPGG;
    }

    public void setYPGG(String YPGG) {
        this.YPGG = YPGG;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.YPMC);
        dest.writeString(this.YPDW);
        dest.writeString(this.YPGG);
        dest.writeValue(this.YPXH);
    }

    public YpInfoBean() {
    }

    protected YpInfoBean(Parcel in) {
        this.YPMC = in.readString();
        this.YPDW = in.readString();
        this.YPGG = in.readString();
        this.YPXH = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<YpInfoBean> CREATOR = new Creator<YpInfoBean>() {
        @Override
        public YpInfoBean createFromParcel(Parcel source) {
            return new YpInfoBean(source);
        }

        @Override
        public YpInfoBean[] newArray(int size) {
            return new YpInfoBean[size];
        }
    };
}
