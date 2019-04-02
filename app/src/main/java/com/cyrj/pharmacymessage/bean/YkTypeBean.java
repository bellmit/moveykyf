package com.cyrj.pharmacymessage.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class YkTypeBean implements Parcelable{
    private Integer YKSB;
    private String YKMC;

    public Integer getYKSB() {
        return YKSB;
    }

    public void setYKSB(Integer YKSB) {
        this.YKSB = YKSB;
    }

    public String getYKMC() {
        return YKMC;
    }

    public void setYKMC(String YKMC) {
        this.YKMC = YKMC;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.YKSB);
        dest.writeString(this.YKMC);
    }

    public YkTypeBean() {
    }

    protected YkTypeBean(Parcel in) {
        this.YKSB = (Integer) in.readValue(Integer.class.getClassLoader());
        this.YKMC = in.readString();
    }

    public static final Creator<YkTypeBean> CREATOR = new Creator<YkTypeBean>() {
        @Override
        public YkTypeBean createFromParcel(Parcel source) {
            return new YkTypeBean(source);
        }

        @Override
        public YkTypeBean[] newArray(int size) {
            return new YkTypeBean[size];
        }
    };
}
