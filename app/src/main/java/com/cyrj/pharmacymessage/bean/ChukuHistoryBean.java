package com.cyrj.pharmacymessage.bean;

import java.util.List;

/**
 * 出库历史
 */
public class ChukuHistoryBean {
    private List<CKLS> cklsList;
    private List<YFLB>yflbList;
    private List<CKFS>ckfsList;

    public List<CKLS> getCklsList() {
        return cklsList;
    }

    public void setCklsList(List<CKLS> cklsList) {
        this.cklsList = cklsList;
    }

    public List<YFLB> getYflbList() {
        return yflbList;
    }

    public void setYflbList(List<YFLB> yflbList) {
        this.yflbList = yflbList;
    }

    public List<CKFS> getCkfsList() {
        return ckfsList;
    }

    public void setCkfsList(List<CKFS> ckfsList) {
        this.ckfsList = ckfsList;
    }
}
