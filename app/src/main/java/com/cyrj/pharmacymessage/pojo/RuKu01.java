package com.cyrj.pharmacymessage.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 前端传递过来参数类
 */
public class RuKu01 implements Parcelable{
    /**
     * 机构ID
     */
    private Integer JGID;
    /**
     * 药库识别
     */
    private Integer XTSB;
    /**
     * 药库方式
     */
    private Integer RKFS;
    /**
     * 入库单号
     */
    private Integer RKDH;
    /**
     * 票未到
     */
    private Integer PWD;
    /**
     * 单位序号
     */
    private Integer DWXH;
    /**
     * 财务判别
     */
    private Integer CWPB;
    /**
     * 附单据数
     */
    private Integer FDJS;
    /**
     * 入库备注
     */
    private String RKBZ;
    /**
     * 入库判断
     */
    private Integer RKPB;
    /**
     * 采购日期
     */
    private String CGRQ;
    /**
     * 录入日期
     */
    private String LRRQ;
    /**
     * 入库日期
     */
    private String RKRQ;
    /**
     * 采购工号
     */
    private String CGGH;
    /**
     * 操作工号
     */
    private String CZGH;
    /**
     * 定价方式
     */
    private Integer DJFS;
    /**
     * 定价公式
     */
    private String DJGS;

    /**
     * 入库02表
     */
    private List<RuKu02> ruKu02;

    public Integer getJGID() {
        return JGID;
    }

    public void setJGID(Integer JGID) {
        this.JGID = JGID;
    }

    public Integer getXTSB() {
        return XTSB;
    }

    public void setXTSB(Integer XTSB) {
        this.XTSB = XTSB;
    }

    public Integer getRKFS() {
        return RKFS;
    }

    public void setRKFS(Integer RKFS) {
        this.RKFS = RKFS;
    }

    public Integer getRKDH() {
        return RKDH;
    }

    public void setRKDH(Integer RKDH) {
        this.RKDH = RKDH;
    }

    public Integer getPWD() {
        return PWD;
    }

    public void setPWD(Integer PWD) {
        this.PWD = PWD;
    }

    public Integer getDWXH() {
        return DWXH;
    }

    public void setDWXH(Integer DWXH) {
        this.DWXH = DWXH;
    }

    public Integer getCWPB() {
        return CWPB;
    }

    public void setCWPB(Integer CWPB) {
        this.CWPB = CWPB;
    }

    public Integer getFDJS() {
        return FDJS;
    }

    public void setFDJS(Integer FDJS) {
        this.FDJS = FDJS;
    }

    public String getRKBZ() {
        return RKBZ;
    }

    public void setRKBZ(String RKBZ) {
        this.RKBZ = RKBZ;
    }

    public Integer getRKPB() {
        return RKPB;
    }

    public void setRKPB(Integer RKPB) {
        this.RKPB = RKPB;
    }

    public String getCGRQ() {
        return CGRQ;
    }

    public void setCGRQ(String CGRQ) {
        this.CGRQ = CGRQ;
    }

    public String getLRRQ() {
        return LRRQ;
    }

    public void setLRRQ(String LRRQ) {
        this.LRRQ = LRRQ;
    }

    public String getRKRQ() {
        return RKRQ;
    }

    public void setRKRQ(String RKRQ) {
        this.RKRQ = RKRQ;
    }

    public String getCGGH() {
        return CGGH;
    }

    public void setCGGH(String CGGH) {
        this.CGGH = CGGH;
    }

    public String getCZGH() {
        return CZGH;
    }

    public void setCZGH(String CZGH) {
        this.CZGH = CZGH;
    }

    public Integer getDJFS() {
        return DJFS;
    }

    public void setDJFS(Integer DJFS) {
        this.DJFS = DJFS;
    }

    public String getDJGS() {
        return DJGS;
    }

    public void setDJGS(String DJGS) {
        this.DJGS = DJGS;
    }

    public List<RuKu02> getRuKu02() {
        return ruKu02;
    }

    public void setRuKu02(List<RuKu02> ruKu02) {
        this.ruKu02 = ruKu02;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.JGID);
        dest.writeValue(this.XTSB);
        dest.writeValue(this.RKFS);
        dest.writeValue(this.RKDH);
        dest.writeValue(this.PWD);
        dest.writeValue(this.DWXH);
        dest.writeValue(this.CWPB);
        dest.writeValue(this.FDJS);
        dest.writeString(this.RKBZ);
        dest.writeValue(this.RKPB);
        dest.writeString(this.CGRQ);
        dest.writeString(this.LRRQ);
        dest.writeString(this.RKRQ);
        dest.writeString(this.CGGH);
        dest.writeString(this.CZGH);
        dest.writeValue(this.DJFS);
        dest.writeString(this.DJGS);
    }

    public RuKu01() {
    }

    protected RuKu01(Parcel in) {
        this.JGID = (Integer) in.readValue(Integer.class.getClassLoader());
        this.XTSB = (Integer) in.readValue(Integer.class.getClassLoader());
        this.RKFS = (Integer) in.readValue(Integer.class.getClassLoader());
        this.RKDH = (Integer) in.readValue(Integer.class.getClassLoader());
        this.PWD = (Integer) in.readValue(Integer.class.getClassLoader());
        this.DWXH = (Integer) in.readValue(Integer.class.getClassLoader());
        this.CWPB = (Integer) in.readValue(Integer.class.getClassLoader());
        this.FDJS = (Integer) in.readValue(Integer.class.getClassLoader());
        this.RKBZ = in.readString();
        this.RKPB = (Integer) in.readValue(Integer.class.getClassLoader());
        this.CGRQ = in.readString();
        this.LRRQ = in.readString();
        this.RKRQ = in.readString();
        this.CGGH = in.readString();
        this.CZGH = in.readString();
        this.DJFS = (Integer) in.readValue(Integer.class.getClassLoader());
        this.DJGS = in.readString();
    }

    public static final Creator<RuKu01> CREATOR = new Creator<RuKu01>() {
        @Override
        public RuKu01 createFromParcel(Parcel source) {
            return new RuKu01(source);
        }

        @Override
        public RuKu01[] newArray(int size) {
            return new RuKu01[size];
        }
    };
}
