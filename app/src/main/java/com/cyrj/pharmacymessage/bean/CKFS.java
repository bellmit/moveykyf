package com.cyrj.pharmacymessage.bean;

/**
 * 出库方式
 */
public class CKFS {
    //药库识别
    private Integer XTSB;
    //出库方式
    private Integer CKFS;

    private Integer FSMC;

    public Integer getXTSB() {
        return XTSB;
    }

    public void setXTSB(Integer XTSB) {
        this.XTSB = XTSB;
    }

    public Integer getCKFS() {
        return CKFS;
    }

    public void setCKFS(Integer CKFS) {
        this.CKFS = CKFS;
    }

    public Integer getFSMC() {
        return FSMC;
    }

    public void setFSMC(Integer FSMC) {
        this.FSMC = FSMC;
    }
}
