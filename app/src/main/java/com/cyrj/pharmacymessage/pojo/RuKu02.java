package com.cyrj.pharmacymessage.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 入库02
 */
public class RuKu02 implements Parcelable{
    /**
     * 机构ID
     */
    private Integer JGID;
//    /**
//     * 识别序号
//     */
//    private Integer SBXH;
    /**
     * 药库识别
     */
    private Integer XTSB;
    /**
     * 入库方式
     */
    private Integer RKFS;
    /**
     * 入库单号
     */
    private Integer RKDH;
    /**
     * 药品序号
     */
    private Integer YPXH;
    /**
     * 药品产地
     */
    private Integer YPCD;
    /**
     * 药品厂家
     */
    private String YPCJ;
    /**
     * 药品批号
     */
    private String YPPH;
    /**
     * 药品效期
     */
    private String YPXQ;
    /**
     * 批发价格
     */
    private Double PFJG;
    /**
     * 零售价格
     */
    private Double LSJG;
    /**
     * 入库数量
     */
    private Integer RKSL;
    /**
     * 合格数量
     */
    private Integer HGSL;
    /**
     * 进货价格
     */
    private Double JHJG;
    /**
     * 进货合计
     */
    private Double JHHJ;
    /**
     * 验收单号
     */
    private Integer YSDH;
    /**
     * 验收工号
     */
    private String YSGH;
    /**
     * 验收日期
     */
    private String YSRQ;
    /**
     * 付款工号
     */
    private String FKGH;
    /**
     * 凭证号码
     */
    private String PZHM;
    /**
     * 付款日期
     */
    private String FKRQ;
    /**
     * 发票号码
     */
    private String FPHM;
    /**
     * 库存性质
     */
    private Integer TYPE;
    /**
     * 折让金额
     */
    private Double ZRJE;
    /**
     * 随货号码
     */
    private String SHHM;
    /**
     * 批发金额
     */
    private Double PFJE;
    /**
     * 零售金额
     */
    private Double LSJE;
    /**
     * 标准零价
     */
    private Double BZLJ;
    /**
     * 库存识别
     */
    private Integer KCSB;
    /**
     * 定价方式
     */
    private Integer DJFS;
    /**
     * 定价公式
     */
    private String DJGS;
    /**
     * 付款金额
     */
    private Double FKJE;
    /**
     * 已付金额
     */
    private Double YFJE;
    /**
     * 付款公式
     */
    private String FKGS;
    /**
     * 药品扣率
     */
    private Double YPKL;
    /**
     * 基本药物标志
     */
    private Integer JBYWBZ;
    /**
     * 计划识别序号
     */
    private Integer JHSBXH;

    //药品名称
    private String YPMC;
    //药品规格
    private String YPGG;
    //药品单位
    private String YPDW;

    public String getYPMC() {
        return YPMC;
    }

    public void setYPMC(String YPMC) {
        this.YPMC = YPMC;
    }

    public String getYPGG() {
        return YPGG;
    }

    public void setYPGG(String YPGG) {
        this.YPGG = YPGG;
    }

    public String getYPDW() {
        return YPDW;
    }

    public void setYPDW(String YPDW) {
        this.YPDW = YPDW;
    }

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

    public Integer getYPXH() {
        return YPXH;
    }

    public void setYPXH(Integer YPXH) {
        this.YPXH = YPXH;
    }

    public Integer getYPCD() {
        return YPCD;
    }

    public void setYPCD(Integer YPCD) {
        this.YPCD = YPCD;
    }

    public String getYPCJ() {
        return YPCJ;
    }

    public void setYPCJ(String YPCJ) {
        this.YPCJ = YPCJ;
    }

    public String getYPPH() {
        return YPPH;
    }

    public void setYPPH(String YPPH) {
        this.YPPH = YPPH;
    }

    public String getYPXQ() {
        return YPXQ;
    }

    public void setYPXQ(String YPXQ) {
        this.YPXQ = YPXQ;
    }

    public Double getPFJG() {
        return PFJG;
    }

    public void setPFJG(Double PFJG) {
        this.PFJG = PFJG;
    }

    public Double getLSJG() {
        return LSJG;
    }

    public void setLSJG(Double LSJG) {
        this.LSJG = LSJG;
    }

    public Integer getRKSL() {
        return RKSL;
    }

    public void setRKSL(Integer RKSL) {
        this.RKSL = RKSL;
    }

    public Integer getHGSL() {
        return HGSL;
    }

    public void setHGSL(Integer HGSL) {
        this.HGSL = HGSL;
    }

    public Double getJHJG() {
        return JHJG;
    }

    public void setJHJG(Double JHJG) {
        this.JHJG = JHJG;
    }

    public Double getJHHJ() {
        return JHHJ;
    }

    public void setJHHJ(Double JHHJ) {
        this.JHHJ = JHHJ;
    }

    public Integer getYSDH() {
        return YSDH;
    }

    public void setYSDH(Integer YSDH) {
        this.YSDH = YSDH;
    }

    public String getYSGH() {
        return YSGH;
    }

    public void setYSGH(String YSGH) {
        this.YSGH = YSGH;
    }

    public String getYSRQ() {
        return YSRQ;
    }

    public void setYSRQ(String YSRQ) {
        this.YSRQ = YSRQ;
    }

    public String getFKGH() {
        return FKGH;
    }

    public void setFKGH(String FKGH) {
        this.FKGH = FKGH;
    }

    public String getPZHM() {
        return PZHM;
    }

    public void setPZHM(String PZHM) {
        this.PZHM = PZHM;
    }

    public String getFKRQ() {
        return FKRQ;
    }

    public void setFKRQ(String FKRQ) {
        this.FKRQ = FKRQ;
    }

    public String getFPHM() {
        return FPHM;
    }

    public void setFPHM(String FPHM) {
        this.FPHM = FPHM;
    }

    public Integer getTYPE() {
        return TYPE;
    }

    public void setTYPE(Integer TYPE) {
        this.TYPE = TYPE;
    }

    public Double getZRJE() {
        return ZRJE;
    }

    public void setZRJE(Double ZRJE) {
        this.ZRJE = ZRJE;
    }

    public String getSHHM() {
        return SHHM;
    }

    public void setSHHM(String SHHM) {
        this.SHHM = SHHM;
    }

    public Double getPFJE() {
        return PFJE;
    }

    public void setPFJE(Double PFJE) {
        this.PFJE = PFJE;
    }

    public Double getLSJE() {
        return LSJE;
    }

    public void setLSJE(Double LSJE) {
        this.LSJE = LSJE;
    }

    public Double getBZLJ() {
        return BZLJ;
    }

    public void setBZLJ(Double BZLJ) {
        this.BZLJ = BZLJ;
    }

    public Integer getKCSB() {
        return KCSB;
    }

    public void setKCSB(Integer KCSB) {
        this.KCSB = KCSB;
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

    public Double getFKJE() {
        return FKJE;
    }

    public void setFKJE(Double FKJE) {
        this.FKJE = FKJE;
    }

    public Double getYFJE() {
        return YFJE;
    }

    public void setYFJE(Double YFJE) {
        this.YFJE = YFJE;
    }

    public String getFKGS() {
        return FKGS;
    }

    public void setFKGS(String FKGS) {
        this.FKGS = FKGS;
    }

    public Double getYPKL() {
        return YPKL;
    }

    public void setYPKL(Double YPKL) {
        this.YPKL = YPKL;
    }

    public Integer getJBYWBZ() {
        return JBYWBZ;
    }

    public void setJBYWBZ(Integer JBYWBZ) {
        this.JBYWBZ = JBYWBZ;
    }

    public Integer getJHSBXH() {
        return JHSBXH;
    }

    public void setJHSBXH(Integer JHSBXH) {
        this.JHSBXH = JHSBXH;
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
        dest.writeValue(this.YPXH);
        dest.writeValue(this.YPCD);
        dest.writeString(this.YPCJ);
        dest.writeString(this.YPPH);
        dest.writeString(this.YPXQ);
        dest.writeValue(this.PFJG);
        dest.writeValue(this.LSJG);
        dest.writeValue(this.RKSL);
        dest.writeValue(this.HGSL);
        dest.writeValue(this.JHJG);
        dest.writeValue(this.JHHJ);
        dest.writeValue(this.YSDH);
        dest.writeString(this.YSGH);
        dest.writeString(this.YSRQ);
        dest.writeString(this.FKGH);
        dest.writeString(this.PZHM);
        dest.writeString(this.FKRQ);
        dest.writeString(this.FPHM);
        dest.writeValue(this.TYPE);
        dest.writeValue(this.ZRJE);
        dest.writeString(this.SHHM);
        dest.writeValue(this.PFJE);
        dest.writeValue(this.LSJE);
        dest.writeValue(this.BZLJ);
        dest.writeValue(this.KCSB);
        dest.writeValue(this.DJFS);
        dest.writeString(this.DJGS);
        dest.writeValue(this.FKJE);
        dest.writeValue(this.YFJE);
        dest.writeString(this.FKGS);
        dest.writeValue(this.YPKL);
        dest.writeValue(this.JBYWBZ);
        dest.writeValue(this.JHSBXH);
    }

    public RuKu02() {
    }

    protected RuKu02(Parcel in) {
        this.JGID = (Integer) in.readValue(Integer.class.getClassLoader());
//        this.SBXH = (Integer) in.readValue(Integer.class.getClassLoader());
        this.XTSB = (Integer) in.readValue(Integer.class.getClassLoader());
        this.RKFS = (Integer) in.readValue(Integer.class.getClassLoader());
        this.RKDH = (Integer) in.readValue(Integer.class.getClassLoader());
        this.YPXH = (Integer) in.readValue(Integer.class.getClassLoader());
        this.YPCD = (Integer) in.readValue(Integer.class.getClassLoader());
        this.YPCJ = in.readString();
        this.YPPH = in.readString();
        this.YPXQ = in.readString();
        this.PFJG = (Double) in.readValue(Double.class.getClassLoader());
        this.LSJG = (Double) in.readValue(Double.class.getClassLoader());
        this.RKSL = (Integer) in.readValue(Integer.class.getClassLoader());
        this.HGSL = (Integer) in.readValue(Integer.class.getClassLoader());
        this.JHJG = (Double) in.readValue(Double.class.getClassLoader());
        this.JHHJ = (Double) in.readValue(Double.class.getClassLoader());
        this.YSDH = (Integer) in.readValue(Integer.class.getClassLoader());
        this.YSGH = in.readString();
        this.YSRQ = in.readString();
        this.FKGH = in.readString();
        this.PZHM = in.readString();
        this.FKRQ = in.readString();
        this.FPHM = in.readString();
        this.TYPE = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ZRJE = (Double) in.readValue(Double.class.getClassLoader());
        this.SHHM = in.readString();
        this.PFJE = (Double) in.readValue(Double.class.getClassLoader());
        this.LSJE = (Double) in.readValue(Double.class.getClassLoader());
        this.BZLJ = (Double) in.readValue(Double.class.getClassLoader());
        this.KCSB = (Integer) in.readValue(Integer.class.getClassLoader());
        this.DJFS = (Integer) in.readValue(Integer.class.getClassLoader());
        this.DJGS = in.readString();
        this.FKJE = (Double) in.readValue(Double.class.getClassLoader());
        this.YFJE = (Double) in.readValue(Double.class.getClassLoader());
        this.FKGS = in.readString();
        this.YPKL = (Double) in.readValue(Double.class.getClassLoader());
        this.JBYWBZ = (Integer) in.readValue(Integer.class.getClassLoader());
        this.JHSBXH = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<RuKu02> CREATOR = new Creator<RuKu02>() {
        @Override
        public RuKu02 createFromParcel(Parcel source) {
            return new RuKu02(source);
        }

        @Override
        public RuKu02[] newArray(int size) {
            return new RuKu02[size];
        }
    };
}
