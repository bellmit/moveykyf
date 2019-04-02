package com.cyrj.pharmacymessage.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 药品产地信息
 */
public class YpcdBean implements Parcelable{
    private String JHJG;

    private String PFJG;

    private String LSJG;

    private String YPMC;

    private String YPDW;

    private String YPGG;

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

    public String getJHJG() {
        return JHJG;
    }

    public void setJHJG(String JHJG) {
        this.JHJG = JHJG;
    }

    public String getPFJG() {
        return PFJG;
    }

    public void setPFJG(String PFJG) {
        this.PFJG = PFJG;
    }

    public String getLSJG() {
        return LSJG;
    }

    public void setLSJG(String LSJG) {
        this.LSJG = LSJG;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.JHJG);
        dest.writeString(this.PFJG);
        dest.writeString(this.LSJG);

    }

    public YpcdBean() {
    }

    protected YpcdBean(Parcel in) {
        this.JHJG = in.readString();
        this.PFJG = in.readString();
        this.LSJG = in.readString();

    }

    public static final Creator<YpcdBean> CREATOR = new Creator<YpcdBean>() {
        @Override
        public YpcdBean createFromParcel(Parcel source) {
            return new YpcdBean(source);
        }

        @Override
        public YpcdBean[] newArray(int size) {
            return new YpcdBean[size];
        }
    };
}
