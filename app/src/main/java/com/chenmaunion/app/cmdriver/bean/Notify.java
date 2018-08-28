package com.chenmaunion.app.cmdriver.bean;

import java.util.ArrayList;

public class Notify implements java.io.Serializable {
    private String IDX;
    private String SHIPMENTNO;
    private String SHIPMENTIDX;
    private ArrayList<OrderMap> SHIPMENT_List;
    private String USER_ID;
    private String ORD_NO;
    private String ORD_IDX;
    private String TITLE;
    private String MESSAGE;
    private String ADD_DATE;
    private String ISREAD;
    private String TYPE;
    public Notify() {
    }

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

    public String getORD_NO() {
        return ORD_NO;
    }

    public void setORD_NO(String ORD_NO) {
        this.ORD_NO = ORD_NO;
    }

    public String getORD_IDX() {
        return ORD_IDX;
    }

    public void setORD_IDX(String ORD_IDX) {
        this.ORD_IDX = ORD_IDX;
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

    public ArrayList<OrderMap> getSHIPMENT_List() {
        return SHIPMENT_List;
    }

    public void setSHIPMENT_List(ArrayList<OrderMap> SHIPMENT_List) {
        this.SHIPMENT_List = SHIPMENT_List;
    }

    public static class OrderMap {
        private String ORD_NO;
        private String ORD_IDX;

        public String getORD_NO() {
            return ORD_NO;
        }

        public void setORD_NO(String ORD_NO) {
            this.ORD_NO = ORD_NO;
        }

        public String getORD_IDX() {
            return ORD_IDX;
        }

        public void setORD_IDX(String ORD_IDX) {
            this.ORD_IDX = ORD_IDX;
        }
    }
}
