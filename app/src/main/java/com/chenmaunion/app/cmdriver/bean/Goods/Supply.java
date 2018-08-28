package com.chenmaunion.app.cmdriver.bean.Goods;

import java.io.Serializable;

/**
 * ${PEOJECT_NAME}
 * 货源列表，简单货源信息基类
 * Created by Administrator on 2017/2/24.
 */
public class Supply implements Serializable {
    private String IDX;
    private String SUPPLY_NO;
    private String ORG_NAME;
    private String ROUTES_CITY;
    private String SUPPLY_VEHICLE_TYPE;
    private String SUPPLY_VEHICLE_SIZE;
    private String TOTAL_WEIGHT;
    private String TOTAL_VOLUME;
    private String TOTAL_QTY;
    private String TOTAL_AMOUNT;
    private String SUPPLY_STATE;
    private String SUPPLY_WOKERFLOW;
    private String ADD_DATE;

    public String getIDX() {
        return IDX;
    }

    public void setIDX(String IDX) {
        this.IDX = IDX;
    }

    public String getSUPPLY_NO() {
        return SUPPLY_NO;
    }

    public void setSUPPLY_NO(String SUPPLY_NO) {
        this.SUPPLY_NO = SUPPLY_NO;
    }

    public String getORG_NAME() {
        return ORG_NAME;
    }

    public void setORG_NAME(String ORG_NAME) {
        this.ORG_NAME = ORG_NAME;
    }

    public String getROUTES_CITY() {
        return ROUTES_CITY;
    }

    public void setROUTES_CITY(String ROUTES_CITY) {
        this.ROUTES_CITY = ROUTES_CITY;
    }

    public String getSUPPLY_VEHICLE_TYPE() {
        return SUPPLY_VEHICLE_TYPE;
    }

    public void setSUPPLY_VEHICLE_TYPE(String SUPPLY_VEHICLE_TYPE) {
        this.SUPPLY_VEHICLE_TYPE = SUPPLY_VEHICLE_TYPE;
    }

    public String getSUPPLY_VEHICLE_SIZE() {
        return SUPPLY_VEHICLE_SIZE;
    }

    public void setSUPPLY_VEHICLE_SIZE(String SUPPLY_VEHICLE_SIZE) {
        this.SUPPLY_VEHICLE_SIZE = SUPPLY_VEHICLE_SIZE;
    }

    public String getTOTAL_WEIGHT() {
        return TOTAL_WEIGHT;
    }

    public void setTOTAL_WEIGHT(String TOTAL_WEIGHT) {
        this.TOTAL_WEIGHT = TOTAL_WEIGHT;
    }

    public String getTOTAL_VOLUME() {
        return TOTAL_VOLUME;
    }

    public void setTOTAL_VOLUME(String TOTAL_VOLUME) {
        this.TOTAL_VOLUME = TOTAL_VOLUME;
    }

    public String getTOTAL_QTY() {
        return TOTAL_QTY;
    }

    public void setTOTAL_QTY(String TOTAL_QTY) {
        this.TOTAL_QTY = TOTAL_QTY;
    }

    public String getTOTAL_AMOUNT() {
        return TOTAL_AMOUNT;
    }

    public void setTOTAL_AMOUNT(String TOTAL_AMOUNT) {
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
    }

    public String getSUPPLY_STATE() {
        return SUPPLY_STATE;
    }

    public void setSUPPLY_STATE(String SUPPLY_STATE) {
        this.SUPPLY_STATE = SUPPLY_STATE;
    }

    public String getSUPPLY_WOKERFLOW() {
        return SUPPLY_WOKERFLOW;
    }

    public void setSUPPLY_WOKERFLOW(String SUPPLY_WOKERFLOW) {
        this.SUPPLY_WOKERFLOW = SUPPLY_WOKERFLOW;
    }

    public String getADD_DATE() {
        return ADD_DATE;
    }

    public void setADD_DATE(String ADD_DATE) {
        this.ADD_DATE = ADD_DATE;
    }

    @Override
    public String toString() {
        return "Supply{" +
                "IDX='" + IDX + '\'' +
                ", SUPPLY_NO='" + SUPPLY_NO + '\'' +
                ", ORG_NAME='" + ORG_NAME + '\'' +
                ", ROUTES_CITY='" + ROUTES_CITY + '\'' +
                ", SUPPLY_VEHICLE_TYPE='" + SUPPLY_VEHICLE_TYPE + '\'' +
                ", SUPPLY_VEHICLE_SIZE='" + SUPPLY_VEHICLE_SIZE + '\'' +
                ", TOTAL_WEIGHT='" + TOTAL_WEIGHT + '\'' +
                ", TOTAL_VOLUME='" + TOTAL_VOLUME + '\'' +
                ", TOTAL_QTY='" + TOTAL_QTY + '\'' +
                ", TOTAL_AMOUNT='" + TOTAL_AMOUNT + '\'' +
                ", SUPPLY_STATE='" + SUPPLY_STATE + '\'' +
                ", SUPPLY_WOKERFLOW='" + SUPPLY_WOKERFLOW + '\'' +
                ", ADD_DATE='" + ADD_DATE + '\'' +
                '}';
    }

}
