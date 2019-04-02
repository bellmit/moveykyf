package com.cyrj.pharmacymessage.bean;

/**
 * 出库历史
 */
public class CKLS {
    private Integer CKFS;//出库方式

    private Integer CKDH;//出库单号

    private Integer YFSB;//药房识别

    private Integer CKPB;//出库判别

    private String CKRQ;//出库日期

    private Integer SQTJ;

    private String YFMC;//出库名称
    private String CKFSMC;//出库方式名称

    public String getYFMC() {
        return YFMC;
    }

    public void setYFMC(String CKMC) {
        this.YFMC = CKMC;
    }

    public String getCKFSMC() {
        return CKFSMC;
    }

    public void setCKFSMC(String CKFSMC) {
        this.CKFSMC = CKFSMC;
    }

    public Integer getCKFS() {
        return CKFS;
    }

    public void setCKFS(Integer CKFS) {
        this.CKFS = CKFS;
    }

    public Integer getCKDH() {
        return CKDH;
    }

    public void setCKDH(Integer CKDH) {
        this.CKDH = CKDH;
    }

    public Integer getYFSB() {
        return YFSB;
    }

    public void setYFSB(Integer YFSB) {
        this.YFSB = YFSB;
    }

    public Integer getCKPB() {
        return CKPB;
    }

    public void setCKPB(Integer CKPB) {
        this.CKPB = CKPB;
    }

    public String getCKRQ() {
        return CKRQ;
    }

    public void setCKRQ(String CKRQ) {
        this.CKRQ = CKRQ;
    }

    public Integer getSQTJ() {
        return SQTJ;
    }

    public void setSQTJ(Integer SQTJ) {
        this.SQTJ = SQTJ;
    }
}
