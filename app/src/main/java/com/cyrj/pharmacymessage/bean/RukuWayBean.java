package com.cyrj.pharmacymessage.bean;

/**
 * 入库方式
 */
public class RukuWayBean {
    /**
     * 入库方式
     */
    private Integer RKFS;
    /**
     * 方式名称
     */
    private String FSMC;

    /**
     * 定价方式
     */
    private Integer DJFS;

    /**
     * 定价公式
     */
    private String DJGS;
    /**
     * 入库单号
     */
    private Integer RKDH;

    public Integer getRKDH() {
        return RKDH;
    }

    public void setRKDH(Integer RKDH) {
        this.RKDH = RKDH;
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

    public Integer getRKFS() {
        return RKFS;
    }

    public void setRKFS(Integer RKFS) {
        this.RKFS = RKFS;
    }

    public String getFSMC() {
        return FSMC;
    }

    public void setFSMC(String FSMC) {
        this.FSMC = FSMC;
    }
}
