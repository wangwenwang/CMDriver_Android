package com.chenmaunion.app.cmdriver.bean.order;

import java.io.Serializable;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/8.
 */
public class SmInfoOrder implements Serializable {
    private String IDX;// D号
    private String ORD_NO;//	订单流水号
    private String TMS_ORD_NO;//	TMS订单号
    private String ORD_NO_CLIENT;//	客户订单号
    private String ORD_FROM_NAME;//	起运点名称
    private String ORD_FROM_ADDRESS;// 起运点地址
    private String ORD_FROM_CNAME;//	起运点联系人
    private String ORD_FROM_CTEL;//	起运点联系电话
    private String ORD_TO_NAME;//	到达点名称
    private String ORD_TO_ADDRESS;//	到达点地址
    private String ORD_TO_CNAME;//	到达点联系人
    private String ORD_TO_CTEL;//	到达点联系电话
    private String ORD_ISSUE_QTY;//	发货总数量
    private String ORD_ISSUE_WEIGHT;//	发货总重量
    private String ORD_ISSUE_VOLUME;// 发货总体积
    private String UPDATE03;// 交付标志

    public String getUPDATE03() {
        return UPDATE03;
    }

    public void setUPDATE03(String UPDATE03) {
        this.UPDATE03 = UPDATE03;
    }

    public String getIDX() {
        return IDX;
    }

    public void setIDX(String IDX) {
        this.IDX = IDX;
    }

    public String getORD_NO() {
        return ORD_NO;
    }

    public void setORD_NO(String ORD_NO) {
        this.ORD_NO = ORD_NO;
    }

    public String getTMS_ORD_NO() {
        return TMS_ORD_NO;
    }

    public void setTMS_ORD_NO(String TMS_ORD_NO) {
        this.TMS_ORD_NO = TMS_ORD_NO;
    }

    public String getORD_NO_CLIENT() {
        return ORD_NO_CLIENT;
    }

    public void setORD_NO_CLIENT(String ORD_NO_CLIENT) {
        this.ORD_NO_CLIENT = ORD_NO_CLIENT;
    }

    public String getORD_FROM_NAME() {
        return ORD_FROM_NAME;
    }

    public void setORD_FROM_NAME(String ORD_FROM_NAME) {
        this.ORD_FROM_NAME = ORD_FROM_NAME;
    }

    public String getORD_FROM_ADDRESS() {
        return ORD_FROM_ADDRESS;
    }

    public void setORD_FROM_ADDRESS(String ORD_FROM_ADDRESS) {
        this.ORD_FROM_ADDRESS = ORD_FROM_ADDRESS;
    }

    public String getORD_FROM_CNAME() {
        return ORD_FROM_CNAME;
    }

    public void setORD_FROM_CNAME(String ORD_FROM_CNAME) {
        this.ORD_FROM_CNAME = ORD_FROM_CNAME;
    }

    public String getORD_FROM_CTEL() {
        return ORD_FROM_CTEL;
    }

    public void setORD_FROM_CTEL(String ORD_FROM_CTEL) {
        this.ORD_FROM_CTEL = ORD_FROM_CTEL;
    }

    public String getORD_TO_NAME() {
        return ORD_TO_NAME;
    }

    public void setORD_TO_NAME(String ORD_TO_NAME) {
        this.ORD_TO_NAME = ORD_TO_NAME;
    }

    public String getORD_TO_ADDRESS() {
        return ORD_TO_ADDRESS;
    }

    public void setORD_TO_ADDRESS(String ORD_TO_ADDRESS) {
        this.ORD_TO_ADDRESS = ORD_TO_ADDRESS;
    }

    public String getORD_TO_CNAME() {
        return ORD_TO_CNAME;
    }

    public void setORD_TO_CNAME(String ORD_TO_CNAME) {
        this.ORD_TO_CNAME = ORD_TO_CNAME;
    }

    public String getORD_TO_CTEL() {
        return ORD_TO_CTEL;
    }

    public void setORD_TO_CTEL(String ORD_TO_CTEL) {
        this.ORD_TO_CTEL = ORD_TO_CTEL;
    }

    public String getORD_ISSUE_QTY() {
        return ORD_ISSUE_QTY;
    }

    public void setORD_ISSUE_QTY(String ORD_ISSUE_QTY) {
        this.ORD_ISSUE_QTY = ORD_ISSUE_QTY;
    }

    public String getORD_ISSUE_WEIGHT() {
        return ORD_ISSUE_WEIGHT;
    }

    public void setORD_ISSUE_WEIGHT(String ORD_ISSUE_WEIGHT) {
        this.ORD_ISSUE_WEIGHT = ORD_ISSUE_WEIGHT;
    }

    public String getORD_ISSUE_VOLUME() {
        return ORD_ISSUE_VOLUME;
    }

    public void setORD_ISSUE_VOLUME(String ORD_ISSUE_VOLUME) {
        this.ORD_ISSUE_VOLUME = ORD_ISSUE_VOLUME;
    }

    @Override
    public String toString() {
        return "SmInfoOrder{" +
                "IDX='" + IDX + '\'' +
                ", ORD_NO='" + ORD_NO + '\'' +
                ", TMS_ORD_NO='" + TMS_ORD_NO + '\'' +
                ", ORD_NO_CLIENT='" + ORD_NO_CLIENT + '\'' +
                ", ORD_FROM_NAME='" + ORD_FROM_NAME + '\'' +
                ", ORD_FROM_ADDRESS='" + ORD_FROM_ADDRESS + '\'' +
                ", ORD_FROM_CNAME='" + ORD_FROM_CNAME + '\'' +
                ", ORD_FROM_CTEL='" + ORD_FROM_CTEL + '\'' +
                ", ORD_TO_NAME='" + ORD_TO_NAME + '\'' +
                ", ORD_TO_ADDRESS='" + ORD_TO_ADDRESS + '\'' +
                ", ORD_TO_CNAME='" + ORD_TO_CNAME + '\'' +
                ", ORD_TO_CTEL='" + ORD_TO_CTEL + '\'' +
                ", ORD_ISSUE_QTY='" + ORD_ISSUE_QTY + '\'' +
                ", ORD_ISSUE_WEIGHT='" + ORD_ISSUE_WEIGHT + '\'' +
                ", ORD_ISSUE_VOLUME='" + ORD_ISSUE_VOLUME + '\'' +
                ", UPDATE03='" + UPDATE03 + '\'' +
                '}';

    }
}
