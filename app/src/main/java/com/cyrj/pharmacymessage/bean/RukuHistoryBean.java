package com.cyrj.pharmacymessage.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class RukuHistoryBean implements Parcelable{
    private String RKDH;
    private String RKRQ;
    private String RKFS;
    private Integer RKPB;
    private String CGRQ;
    private Integer PWD;
    private Integer FDJS;

    public String getCGRQ() {
        return CGRQ;
    }

    public void setCGRQ(String CGRQ) {
        this.CGRQ = CGRQ;
    }

    public Integer getPWD() {
        return PWD;
    }

    public void setPWD(Integer PWD) {
        this.PWD = PWD;
    }

    public Integer getFDJS() {
        return FDJS;
    }

    public void setFDJS(Integer FDJS) {
        this.FDJS = FDJS;
    }

    public Integer getRKPB() {
        return RKPB;
    }

    public void setRKPB(Integer RKPB) {
        this.RKPB = RKPB;
    }

    public String getRKDH() {
        return RKDH;
    }

    public void setRKDH(String RKDH) {
        this.RKDH = RKDH;
    }

    public String getRKRQ() {
        return RKRQ;
    }

    public void setRKRQ(String RKRQ) {
        this.RKRQ = RKRQ;
    }

    public String getRKFS() {
        return RKFS;
    }

    public void setRKFS(String RKFS) {
        this.RKFS = RKFS;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.RKDH);
        dest.writeString(this.RKRQ);
        dest.writeString(this.RKFS);
        dest.writeValue(this.RKPB);
        dest.writeString(this.CGRQ);
        dest.writeValue(this.PWD);
        dest.writeValue(this.FDJS);
    }

    public RukuHistoryBean() {
    }

    protected RukuHistoryBean(Parcel in) {
        this.RKDH = in.readString();
        this.RKRQ = in.readString();
        this.RKFS = in.readString();
        this.RKPB = (Integer) in.readValue(Integer.class.getClassLoader());
        this.CGRQ = in.readString();
        this.PWD = (Integer) in.readValue(Integer.class.getClassLoader());
        this.FDJS = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<RukuHistoryBean> CREATOR = new Creator<RukuHistoryBean>() {
        @Override
        public RukuHistoryBean createFromParcel(Parcel source) {
            return new RukuHistoryBean(source);
        }

        @Override
        public RukuHistoryBean[] newArray(int size) {
            return new RukuHistoryBean[size];
        }
    };
}
