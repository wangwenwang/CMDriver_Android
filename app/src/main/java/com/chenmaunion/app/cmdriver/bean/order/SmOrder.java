package com.chenmaunion.app.cmdriver.bean.order;

import java.util.List;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/3.
 */
public class SmOrder {
    private String IDX;//	ID号
    private String ORD_NO;//订单流水号
    private String TMS_ORD_NO;//	TMS订单号
    private String ORD_NO_CLIENT;//	客户订单号
    private String ORD_NO_CONSIGNEE;//	收货人订单号
    private String ORD_TYPE_CLIENT;//	客户订单类型
    private String ORD_REMARK_CLIENT;//	客户备注
    private String ORD_REMARK_CONSIGNEE;//	收货人备注
    private String  ORD_DATE_ADD;//	创建时间
    private String ORD_DATE_EDIT;//	修改时间
    private String ORD_SHIPMENT_NO;//	客户装运编号
    private String ORD_VEHICLE_TYPE;//	要求车辆类型
    private String ORD_VEHICLE_SIZE;// 要求车辆尺寸
    private String ORD_REQUEST_ISSUE;//	要求发货时间
    private String ORD_REQUEST_DELIVERY;//	要求交付时间
    private String ORD_PROJECTED_DELIVERY;// 预计交付时间
    private String ORD_FROM_NAME;// 	起运点名称
    private String ORD_FROM_ADDRESS;//	起运点地址
    private String ORD_FROM_CNAME;//	起运点联系人
    private String ORD_FROM_CTEL;//	起运点联系电话
    private String ORD_TO_NAME;//	到达点名称
    private String ORD_TO_ADDRESS;//	到达点地址
    private String ORD_TO_CNAME;//	到达点联系人
    private String ORD_TO_CTEL;//	到达点联系电话
    private String ORD_ISSUE_QTY;//	发货总数量
    private String ORD_ISSUE_WEIGHT;//	发货总重量
    private String ORD_ISSUE_VOLUME;//	发货总体积
    private String OMS_REMARK_AUDIT;//	审核备注
    private String OTS_REMARK_DELIVERY;//	到货备注
    private String OTS_REMARK_RETURN;//	回单备注
    private String OTS_DELIVERY_NOTICE;//	通知交付时间
    private String OTS_DELIVERY_ACTUAL;//	交付确认时间
    private String OTS_RETURN_DATE;//	回单确认时间
    private String UPDATE03;//	交付标志


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

    public String getORD_NO_CONSIGNEE() {
        return ORD_NO_CONSIGNEE;
    }

    public void setORD_NO_CONSIGNEE(String ORD_NO_CONSIGNEE) {
        this.ORD_NO_CONSIGNEE = ORD_NO_CONSIGNEE;
    }

    public String getORD_TYPE_CLIENT() {
        return ORD_TYPE_CLIENT;
    }

    public void setORD_TYPE_CLIENT(String ORD_TYPE_CLIENT) {
        this.ORD_TYPE_CLIENT = ORD_TYPE_CLIENT;
    }

    public String getORD_REMARK_CLIENT() {
        return ORD_REMARK_CLIENT;
    }

    public void setORD_REMARK_CLIENT(String ORD_REMARK_CLIENT) {
        this.ORD_REMARK_CLIENT = ORD_REMARK_CLIENT;
    }

    public String getORD_REMARK_CONSIGNEE() {
        return ORD_REMARK_CONSIGNEE;
    }

    public void setORD_REMARK_CONSIGNEE(String ORD_REMARK_CONSIGNEE) {
        this.ORD_REMARK_CONSIGNEE = ORD_REMARK_CONSIGNEE;
    }

    public String getORD_DATE_ADD() {
        return ORD_DATE_ADD;
    }

    public void setORD_DATE_ADD(String ORD_DATE_ADD) {
        this.ORD_DATE_ADD = ORD_DATE_ADD;
    }

    public String getORD_DATE_EDIT() {
        return ORD_DATE_EDIT;
    }

    public void setORD_DATE_EDIT(String ORD_DATE_EDIT) {
        this.ORD_DATE_EDIT = ORD_DATE_EDIT;
    }

    public String getORD_SHIPMENT_NO() {
        return ORD_SHIPMENT_NO;
    }

    public void setORD_SHIPMENT_NO(String ORD_SHIPMENT_NO) {
        this.ORD_SHIPMENT_NO = ORD_SHIPMENT_NO;
    }

    public String getORD_VEHICLE_TYPE() {
        return ORD_VEHICLE_TYPE;
    }

    public void setORD_VEHICLE_TYPE(String ORD_VEHICLE_TYPE) {
        this.ORD_VEHICLE_TYPE = ORD_VEHICLE_TYPE;
    }

    public String getORD_VEHICLE_SIZE() {
        return ORD_VEHICLE_SIZE;
    }

    public void setORD_VEHICLE_SIZE(String ORD_VEHICLE_SIZE) {
        this.ORD_VEHICLE_SIZE = ORD_VEHICLE_SIZE;
    }

    public String getORD_REQUEST_ISSUE() {
        return ORD_REQUEST_ISSUE;
    }

    public void setORD_REQUEST_ISSUE(String ORD_REQUEST_ISSUE) {
        this.ORD_REQUEST_ISSUE = ORD_REQUEST_ISSUE;
    }

    public String getORD_REQUEST_DELIVERY() {
        return ORD_REQUEST_DELIVERY;
    }

    public void setORD_REQUEST_DELIVERY(String ORD_REQUEST_DELIVERY) {
        this.ORD_REQUEST_DELIVERY = ORD_REQUEST_DELIVERY;
    }

    public String getORD_PROJECTED_DELIVERY() {
        return ORD_PROJECTED_DELIVERY;
    }

    public void setORD_PROJECTED_DELIVERY(String ORD_PROJECTED_DELIVERY) {
        this.ORD_PROJECTED_DELIVERY = ORD_PROJECTED_DELIVERY;
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

    public String getOMS_REMARK_AUDIT() {
        return OMS_REMARK_AUDIT;
    }

    public void setOMS_REMARK_AUDIT(String OMS_REMARK_AUDIT) {
        this.OMS_REMARK_AUDIT = OMS_REMARK_AUDIT;
    }

    public String getOTS_REMARK_DELIVERY() {
        return OTS_REMARK_DELIVERY;
    }

    public void setOTS_REMARK_DELIVERY(String OTS_REMARK_DELIVERY) {
        this.OTS_REMARK_DELIVERY = OTS_REMARK_DELIVERY;
    }

    public String getOTS_REMARK_RETURN() {
        return OTS_REMARK_RETURN;
    }

    public void setOTS_REMARK_RETURN(String OTS_REMARK_RETURN) {
        this.OTS_REMARK_RETURN = OTS_REMARK_RETURN;
    }

    public String getOTS_DELIVERY_NOTICE() {
        return OTS_DELIVERY_NOTICE;
    }

    public void setOTS_DELIVERY_NOTICE(String OTS_DELIVERY_NOTICE) {
        this.OTS_DELIVERY_NOTICE = OTS_DELIVERY_NOTICE;
    }

    public String getOTS_DELIVERY_ACTUAL() {
        return OTS_DELIVERY_ACTUAL;
    }

    public void setOTS_DELIVERY_ACTUAL(String OTS_DELIVERY_ACTUAL) {
        this.OTS_DELIVERY_ACTUAL = OTS_DELIVERY_ACTUAL;
    }

    public String getOTS_RETURN_DATE() {
        return OTS_RETURN_DATE;
    }

    public void setOTS_RETURN_DATE(String OTS_RETURN_DATE) {
        this.OTS_RETURN_DATE = OTS_RETURN_DATE;
    }

    public String getUPDATE03() {
        return UPDATE03;
    }

    public void setUPDATE03(String UPDATE03) {
        this.UPDATE03 = UPDATE03;
    }



    @Override
    public String toString() {
        return "SmOrder{" +
                "IDX='" + IDX + '\'' +
                ", ORD_NO='" + ORD_NO + '\'' +
                ", TMS_ORD_NO='" + TMS_ORD_NO + '\'' +
                ", ORD_NO_CLIENT='" + ORD_NO_CLIENT + '\'' +
                ", ORD_NO_CONSIGNEE='" + ORD_NO_CONSIGNEE + '\'' +
                ", ORD_TYPE_CLIENT='" + ORD_TYPE_CLIENT + '\'' +
                ", ORD_REMARK_CLIENT='" + ORD_REMARK_CLIENT + '\'' +
                ", ORD_REMARK_CONSIGNEE='" + ORD_REMARK_CONSIGNEE + '\'' +
                ", ORD_DATE_ADD='" + ORD_DATE_ADD + '\'' +
                ", ORD_DATE_EDIT='" + ORD_DATE_EDIT + '\'' +
                ", ORD_SHIPMENT_NO='" + ORD_SHIPMENT_NO + '\'' +
                ", ORD_VEHICLE_TYPE='" + ORD_VEHICLE_TYPE + '\'' +
                ", ORD_VEHICLE_SIZE='" + ORD_VEHICLE_SIZE + '\'' +
                ", ORD_REQUEST_ISSUE='" + ORD_REQUEST_ISSUE + '\'' +
                ", ORD_REQUEST_DELIVERY='" + ORD_REQUEST_DELIVERY + '\'' +
                ", ORD_PROJECTED_DELIVERY='" + ORD_PROJECTED_DELIVERY + '\'' +
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
                ", OMS_REMARK_AUDIT='" + OMS_REMARK_AUDIT + '\'' +
                ", OTS_REMARK_DELIVERY='" + OTS_REMARK_DELIVERY + '\'' +
                ", OTS_REMARK_RETURN='" + OTS_REMARK_RETURN + '\'' +
                ", OTS_DELIVERY_NOTICE='" + OTS_DELIVERY_NOTICE + '\'' +
                ", OTS_DELIVERY_ACTUAL='" + OTS_DELIVERY_ACTUAL + '\'' +
                ", OTS_RETURN_DATE='" + OTS_RETURN_DATE + '\'' +
                ", UPDATE03='" + UPDATE03 + '\'' +
                '}';
    }


}
