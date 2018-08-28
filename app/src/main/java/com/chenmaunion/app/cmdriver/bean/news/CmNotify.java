package com.chenmaunion.app.cmdriver.bean.news;

import java.util.ArrayList;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/6.
 */
public class CmNotify implements java.io.Serializable {
    private String IDX;
    private String USER_ID;
    private String SHIPMENTNO;
    private String SHIPMENTIDX;
    private String TITLE;
    private String MESSAGE;
    private String ADD_DATE;
    private String ISREAD;
    private String TYPE;

    public String getIDX() {
        return IDX;
    }

    public void setIDX(String IDX) {
        this.IDX = IDX;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getSHIPMENTNO() {
        return SHIPMENTNO;
    }

    public void setSHIPMENTNO(String SHIPMENTNO) {
        this.SHIPMENTNO = SHIPMENTNO;
    }

    public String getSHIPMENTIDX() {
        return SHIPMENTIDX;
    }

    public void setSHIPMENTIDX(String SHIPMENTIDX) {
        this.SHIPMENTIDX = SHIPMENTIDX;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getADD_DATE() {
        return ADD_DATE;
    }

    public void setADD_DATE(String ADD_DATE) {
        this.ADD_DATE = ADD_DATE;
    }

    public String getISREAD() {
        return ISREAD;
    }

    public void setISREAD(String ISREAD) {
        this.ISREAD = ISREAD;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
