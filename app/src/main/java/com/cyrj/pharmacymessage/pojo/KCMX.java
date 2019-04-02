package com.cyrj.pharmacymessage.pojo;

/**
 * 传给后端的库存明细
 */
public class KCMX {
    private Integer JGID;
    /**
     * 识别序号
     */
    private Integer JBXH;
    /**
     * 药品序号
     */
    private Integer YPXH;
    /**
     * 药品产地
     */
    private Integer YPCD;
    /**
     * 药品批号
     */
    private String YPPH;
    /**
     * 药品效期
     */
    private String YPXQ;
    /**
     * 库存数量
     */
    private Integer KCSL;
    /**
     * 库存性质
     */
    private Integer TYPE;
    /**
     * 进货日期
     */
    private String JHRQ;
    /**
     * 进货价格
     */
    private double JHJG;
    /**
     * 批发价格
     */
    private double PFJG;
    /**
     * 零售价格
     */
    private double LSJG;
    /**
     * 进货金额
     */
    private double JHJE;
    /**
     * 批发金额
     */
    private double PFJE;
    /**
     * 零售金额
     */
    private double LSJE;
    /**
     * 标准零价
     */
    private double BZLJ;
    /**
     * 药库识别
     */
    private double XTSB;
    /**
     *出入库方式
     */
    private Integer CRKFS;
    /**
     *出入库单号
     */
    private Integer CRKDH;
    /**
     *业务类型
     */
    private Integer YWLX;
    /**
     *业务时间
     */
    private String YWSJ;

    public Integer getJGID() {
        return JGID;
    }

    public void setJGID(Integer JGID) {
        this.JGID = JGID;
    }

    public Integer getJBXH() {
        return JBXH;
    }

    public void setJBXH(Integer JBXH) {
        this.JBXH = JBXH;
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

    public Integer getKCSL() {
        return KCSL;
    }

    public void setKCSL(Integer KCSL) {
        this.KCSL = KCSL;
    }

    public Integer getTYPE() {
        return TYPE;
    }

    public void setTYPE(Integer TYPE) {
        this.TYPE = TYPE;
    }

    public String getJHRQ() {
        return JHRQ;
    }

    public void setJHRQ(String JHRQ) {
        this.JHRQ = JHRQ;
    }

    public double getJHJG() {
        return JHJG;
    }

    public void setJHJG(double JHJG) {
        this.JHJG = JHJG;
    }

    public double getPFJG() {
        return PFJG;
    }

    public void setPFJG(double PFJG) {
        this.PFJG = PFJG;
    }

    public double getLSJG() {
        return LSJG;
    }

    public void setLSJG(double LSJG) {
        this.LSJG = LSJG;
    }

    public double getJHJE() {
        return JHJE;
    }

    public void setJHJE(double JHJE) {
        this.JHJE = JHJE;
    }

    public double getPFJE() {
        return PFJE;
    }

    public void setPFJE(double PFJE) {
        this.PFJE = PFJE;
    }

    public double getLSJE() {
        return LSJE;
    }

    public void setLSJE(double LSJE) {
        this.LSJE = LSJE;
    }

    public double getBZLJ() {
        return BZLJ;
    }

    public void setBZLJ(double BZLJ) {
        this.BZLJ = BZLJ;
    }

    public double getXTSB() {
        return XTSB;
    }

    public void setXTSB(double XTSB) {
        this.XTSB = XTSB;
    }

    public Integer getCRKFS() {
        return CRKFS;
    }

    public void setCRKFS(Integer CRKFS) {
        this.CRKFS = CRKFS;
    }

    public Integer getCRKDH() {
        return CRKDH;
    }

    public void setCRKDH(Integer CRKDH) {
        this.CRKDH = CRKDH;
    }

    public Integer getYWLX() {
        return YWLX;
    }

    public void setYWLX(Integer YWLX) {
        this.YWLX = YWLX;
    }

    public String getYWSJ() {
        return YWSJ;
    }

    public void setYWSJ(String YWSJ) {
        this.YWSJ = YWSJ;
    }
}
