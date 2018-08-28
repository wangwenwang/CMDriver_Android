package com.chenmaunion.app.cmdriver.bean.fleet;

import java.io.Serializable;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/22.
 */
public class Fleet implements Serializable {
    private String IDX;//订单Id
    private String FLEET_PROPERTY;//车队类型
    private String TMS_NAME;//公司名称
    private String FLEET_NAME;//车队名
    private String FLEET_DESC;//车队描述
    private String CONTACT_PERSON;//车队负责人
    private String CONTACT_TEL;//联系电话

    public String getIDX() {
        return IDX;
    }

    public void setIDX(String IDX) {
        this.IDX = IDX;
    }

    public String getFLEET_PROPERTY() {
        return FLEET_PROPERTY;
    }

    public void setFLEET_PROPERTY(String FLEET_PROPERTY) {
        this.FLEET_PROPERTY = FLEET_PROPERTY;
    }

    public String getTMS_NAME() {
        return TMS_NAME;
    }

    public void setTMS_NAME(String TMS_NAME) {
        this.TMS_NAME = TMS_NAME;
    }

    public String getFLEET_NAME() {
        return FLEET_NAME;
    }

    public void setFLEET_NAME(String FLEET_NAME) {
        this.FLEET_NAME = FLEET_NAME;
    }

    public String getFLEET_DESC() {
        return FLEET_DESC;
    }

    public void setFLEET_DESC(String FLEET_DESC) {
        this.FLEET_DESC = FLEET_DESC;
    }

    public String getCONTACT_PERSON() {
        return CONTACT_PERSON;
    }

    public void setCONTACT_PERSON(String CONTACT_PERSON) {
        this.CONTACT_PERSON = CONTACT_PERSON;
    }

    public String getCONTACT_TEL() {
        return CONTACT_TEL;
    }

    public void setCONTACT_TEL(String CONTACT_TEL) {
        this.CONTACT_TEL = CONTACT_TEL;
    }

    @Override
    public String toString() {
        return "Fleet{" +
                "IDX='" + IDX + '\'' +
                ", FLEET_PROPERTY='" + FLEET_PROPERTY + '\'' +
                ", TMS_NAME='" + TMS_NAME + '\'' +
                ", FLEET_NAME='" + FLEET_NAME + '\'' +
                ", FLEET_DESC='" + FLEET_DESC + '\'' +
                ", CONTACT_PERSON='" + CONTACT_PERSON + '\'' +
                ", CONTACT_TEL='" + CONTACT_TEL + '\'' +
                '}';
    }
}
