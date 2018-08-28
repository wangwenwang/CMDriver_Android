package com.chenmaunion.app.cmdriver.bean.order;

import java.io.Serializable;
import java.util.List;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/3.
 */
public class ShipmentInfo {
    private String IDX;
    private String SHIPMENT_NO;//装运流水号
    private String ORG_IDX;//	发布公司ID
    private String ORG_NAME;//	发布公司
    private String TMS_IDX;	//	TMSID
    private String TMS_SHIPMENT_NO;//TMS装运编号
    private String SUPPLY_IDX;//货源ID
    private String DATE_LOAD;//	装运时间
    private String DATE_CONFIRMED;// 确认时间
    private String DATE_ISSUE;//	出库时间
    private String FLEET_IDX;//	车队ID
    private String FLEET_NAME;// 车队名
    private String VEHICLE_IDX;//	车辆ID
    private String PLATE_NUMBER;//	车牌号
    private String VEHICLE_SIZE;//	车辆尺寸
    private String VEHICLE_TYPE;//	车辆类型
    private String DRIVER_IDX;// 司机ID
    private String DRIVER_NAME;//	司机姓名
    private String DRIVER_TEL;//	司机联系电话
    private String SHIPMENT_STATE;// 装运状态
    private String TOTAL_WEIGHT;// 总重量
    private String TOTAL_VOLUME;//	总体积
    private String FROM_CITY;//	起始城市
    private String TO_CITY;// 终点城市
    private String AUDIT_FLAG;// 审核标志
    private String USER_NAME;//	操作人
    private String DATE_ADD;//	创建时间
    private String DATE_EDIT;//	修改时间
    private String REFERENCE01;// 自定义01
    private String REFERENCE02;// 自定义02
    private String REFERENCE03;// 自定义03
    private String REFERENCE04;// 自定义04
    private String REFERENCE05;// 自定义05
    private String REFERENCE06;// 自定义06
    private List<SmInfoOrder> smInfoOrders;
    public String getIDX() {
        return IDX;
    }

    public void setIDX(String IDX) {
        this.IDX = IDX;
    }

    public String getSHIPMENT_NO() {
        return SHIPMENT_NO;
    }

    public void setSHIPMENT_NO(String SHIPMENT_NO) {
        this.SHIPMENT_NO = SHIPMENT_NO;
    }

    public String getORG_IDX() {
        return ORG_IDX;
    }

    public void setORG_IDX(String ORG_IDX) {
        this.ORG_IDX = ORG_IDX;
    }

    public String getORG_NAME() {
        return ORG_NAME;
    }

    public void setORG_NAME(String ORG_NAME) {
        this.ORG_NAME = ORG_NAME;
    }

    public String getTMS_IDX() {
        return TMS_IDX;
    }

    public void setTMS_IDX(String TMS_IDX) {
        this.TMS_IDX = TMS_IDX;
    }

    public String getTMS_SHIPMENT_NO() {
        return TMS_SHIPMENT_NO;
    }

    public void setTMS_SHIPMENT_NO(String TMS_SHIPMENT_NO) {
        this.TMS_SHIPMENT_NO = TMS_SHIPMENT_NO;
    }

    public String getSUPPLY_IDX() {
        return SUPPLY_IDX;
    }

    public void setSUPPLY_IDX(String SUPPLY_IDX) {
        this.SUPPLY_IDX = SUPPLY_IDX;
    }

    public String getDATE_LOAD() {
        return DATE_LOAD;
    }

    public void setDATE_LOAD(String DATE_LOAD) {
        this.DATE_LOAD = DATE_LOAD;
    }

    public String getDATE_CONFIRMED() {
        return DATE_CONFIRMED;
    }

    public void setDATE_CONFIRMED(String DATE_CONFIRMED) {
        this.DATE_CONFIRMED = DATE_CONFIRMED;
    }

    public String getDATE_ISSUE() {
        return DATE_ISSUE;
    }

    public void setDATE_ISSUE(String DATE_ISSUE) {
        this.DATE_ISSUE = DATE_ISSUE;
    }

    public String getFLEET_IDX() {
        return FLEET_IDX;
    }

    public void setFLEET_IDX(String FLEET_IDX) {
        this.FLEET_IDX = FLEET_IDX;
    }

    public String getFLEET_NAME() {
        return FLEET_NAME;
    }

    public void setFLEET_NAME(String FLEET_NAME) {
        this.FLEET_NAME = FLEET_NAME;
    }

    public String getVEHICLE_IDX() {
        return VEHICLE_IDX;
    }

    public void setVEHICLE_IDX(String VEHICLE_IDX) {
        this.VEHICLE_IDX = VEHICLE_IDX;
    }

    public String getPLATE_NUMBER() {
        return PLATE_NUMBER;
    }

    public void setPLATE_NUMBER(String PLATE_NUMBER) {
        this.PLATE_NUMBER = PLATE_NUMBER;
    }

    public String getVEHICLE_SIZE() {
        return VEHICLE_SIZE;
    }

    public void setVEHICLE_SIZE(String VEHICLE_SIZE) {
        this.VEHICLE_SIZE = VEHICLE_SIZE;
    }

    public String getVEHICLE_TYPE() {
        return VEHICLE_TYPE;
    }

    public void setVEHICLE_TYPE(String VEHICLE_TYPE) {
        this.VEHICLE_TYPE = VEHICLE_TYPE;
    }

    public String getDRIVER_IDX() {
        return DRIVER_IDX;
    }

    public void setDRIVER_IDX(String DRIVER_IDX) {
        this.DRIVER_IDX = DRIVER_IDX;
    }

    public String getDRIVER_NAME() {
        return DRIVER_NAME;
    }

    public void setDRIVER_NAME(String DRIVER_NAME) {
        this.DRIVER_NAME = DRIVER_NAME;
    }

    public String getDRIVER_TEL() {
        return DRIVER_TEL;
    }

    public void setDRIVER_TEL(String DRIVER_TEL) {
        this.DRIVER_TEL = DRIVER_TEL;
    }

    public String getSHIPMENT_STATE() {
        return SHIPMENT_STATE;
    }

    public void setSHIPMENT_STATE(String SHIPMENT_STATE) {
        this.SHIPMENT_STATE = SHIPMENT_STATE;
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

    public String getFROM_CITY() {
        return FROM_CITY;
    }

    public void setFROM_CITY(String FROM_CITY) {
        this.FROM_CITY = FROM_CITY;
    }

    public String getTO_CITY() {
        return TO_CITY;
    }

    public void setTO_CITY(String TO_CITY) {
        this.TO_CITY = TO_CITY;
    }

    public String getAUDIT_FLAG() {
        return AUDIT_FLAG;
    }

    public void setAUDIT_FLAG(String AUDIT_FLAG) {
        this.AUDIT_FLAG = AUDIT_FLAG;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getDATE_ADD() {
        return DATE_ADD;
    }

    public void setDATE_ADD(String DATE_ADD) {
        this.DATE_ADD = DATE_ADD;
    }

    public String getDATE_EDIT() {
        return DATE_EDIT;
    }

    public void setDATE_EDIT(String DATE_EDIT) {
        this.DATE_EDIT = DATE_EDIT;
    }

    public String getREFERENCE01() {
        return REFERENCE01;
    }

    public void setREFERENCE01(String REFERENCE01) {
        this.REFERENCE01 = REFERENCE01;
    }

    public String getREFERENCE02() {
        return REFERENCE02;
    }

    public void setREFERENCE02(String REFERENCE02) {
        this.REFERENCE02 = REFERENCE02;
    }

    public String getREFERENCE03() {
        return REFERENCE03;
    }

    public void setREFERENCE03(String REFERENCE03) {
        this.REFERENCE03 = REFERENCE03;
    }

    public String getREFERENCE04() {
        return REFERENCE04;
    }

    public void setREFERENCE04(String REFERENCE04) {
        this.REFERENCE04 = REFERENCE04;
    }

    public String getREFERENCE05() {
        return REFERENCE05;
    }

    public void setREFERENCE05(String REFERENCE05) {
        this.REFERENCE05 = REFERENCE05;
    }

    public String getREFERENCE06() {
        return REFERENCE06;
    }

    public void setREFERENCE06(String REFERENCE06) {
        this.REFERENCE06 = REFERENCE06;
    }

    public List<SmInfoOrder> getSmInfoOrders() {
        return smInfoOrders;
    }

    public void setSmInfoOrders(List<SmInfoOrder> smInfoOrders) {
        this.smInfoOrders = smInfoOrders;
    }

    @Override
    public String toString() {
        return "ShipmentInfo{" +
                "IDX='" + IDX + '\'' +
                ", SHIPMENT_NO='" + SHIPMENT_NO + '\'' +
                ", ORG_IDX='" + ORG_IDX + '\'' +
                ", ORG_NAME='" + ORG_NAME + '\'' +
                ", TMS_IDX='" + TMS_IDX + '\'' +
                ", TMS_SHIPMENT_NO='" + TMS_SHIPMENT_NO + '\'' +
                ", SUPPLY_IDX='" + SUPPLY_IDX + '\'' +
                ", DATE_LOAD='" + DATE_LOAD + '\'' +
                ", DATE_CONFIRMED='" + DATE_CONFIRMED + '\'' +
                ", DATE_ISSUE='" + DATE_ISSUE + '\'' +
                ", FLEET_IDX='" + FLEET_IDX + '\'' +
                ", FLEET_NAME='" + FLEET_NAME + '\'' +
                ", VEHICLE_IDX='" + VEHICLE_IDX + '\'' +
                ", PLATE_NUMBER='" + PLATE_NUMBER + '\'' +
                ", VEHICLE_SIZE='" + VEHICLE_SIZE + '\'' +
                ", VEHICLE_TYPE='" + VEHICLE_TYPE + '\'' +
                ", DRIVER_IDX='" + DRIVER_IDX + '\'' +
                ", DRIVER_NAME='" + DRIVER_NAME + '\'' +
                ", DRIVER_TEL='" + DRIVER_TEL + '\'' +
                ", SHIPMENT_STATE='" + SHIPMENT_STATE + '\'' +
                ", TOTAL_WEIGHT='" + TOTAL_WEIGHT + '\'' +
                ", TOTAL_VOLUME='" + TOTAL_VOLUME + '\'' +
                ", FROM_CITY='" + FROM_CITY + '\'' +
                ", TO_CITY='" + TO_CITY + '\'' +
                ", AUDIT_FLAG='" + AUDIT_FLAG + '\'' +
                ", USER_NAME='" + USER_NAME + '\'' +
                ", DATE_ADD='" + DATE_ADD + '\'' +
                ", DATE_EDIT='" + DATE_EDIT + '\'' +
                ", REFERENCE01='" + REFERENCE01 + '\'' +
                ", REFERENCE02='" + REFERENCE02 + '\'' +
                ", REFERENCE03='" + REFERENCE03 + '\'' +
                ", REFERENCE04='" + REFERENCE04 + '\'' +
                ", REFERENCE05='" + REFERENCE05 + '\'' +
                ", REFERENCE06='" + REFERENCE06 + '\'' +
                ", smInfoOrders=" + smInfoOrders +
                '}';
    }



}
