package com.chenmaunion.app.cmdriver.bean.order;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/8.
 */
public class SmOrderDetails implements java.io.Serializable {
    private String IDX;//	ID号
    private String ENT_IDX;//	企业IDX
    private String ORDER_IDX;//	订单头ID号
    private String PRODUCT_NO;//	产品代码
    private String PRODUCT_NAME;//	产品名称
    private String PRODUCT_DESC;//	产品说明
    private String ISSUE_QTY;//	发货数量
    private String ISSUE_WEIGHT;//	发货重量
    private String ISSUE_VOLUME;//	发货体积
    private String ISSUE_UOM;//	发货单位
    private String REMARK_SUPPLIER;// 订单备注

    public String getIDX() {
        return IDX;
    }

    public void setIDX(String IDX) {
        this.IDX = IDX;
    }

    public String getENT_IDX() {
        return ENT_IDX;
    }

    public void setENT_IDX(String ENT_IDX) {
        this.ENT_IDX = ENT_IDX;
    }

    public String getORDER_IDX() {
        return ORDER_IDX;
    }

    public void setORDER_IDX(String ORDER_IDX) {
        this.ORDER_IDX = ORDER_IDX;
    }

    public String getPRODUCT_NO() {
        return PRODUCT_NO;
    }

    public void setPRODUCT_NO(String PRODUCT_NO) {
        this.PRODUCT_NO = PRODUCT_NO;
    }

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public String getPRODUCT_DESC() {
        return PRODUCT_DESC;
    }

    public void setPRODUCT_DESC(String PRODUCT_DESC) {
        this.PRODUCT_DESC = PRODUCT_DESC;
    }

    public String getISSUE_QTY() {
        return ISSUE_QTY;
    }

    public void setISSUE_QTY(String ISSUE_QTY) {
        this.ISSUE_QTY = ISSUE_QTY;
    }

    public String getISSUE_WEIGHT() {
        return ISSUE_WEIGHT;
    }

    public void setISSUE_WEIGHT(String ISSUE_WEIGHT) {
        this.ISSUE_WEIGHT = ISSUE_WEIGHT;
    }

    public String getISSUE_VOLUME() {
        return ISSUE_VOLUME;
    }

    public void setISSUE_VOLUME(String ISSUE_VOLUME) {
        this.ISSUE_VOLUME = ISSUE_VOLUME;
    }

    public String getISSUE_UOM() {
        return ISSUE_UOM;
    }

    public void setISSUE_UOM(String ISSUE_UOM) {
        this.ISSUE_UOM = ISSUE_UOM;
    }

    public String getREMARK_SUPPLIER() {
        return REMARK_SUPPLIER;
    }

    public void setREMARK_SUPPLIER(String REMARK_SUPPLIER) {
        this.REMARK_SUPPLIER = REMARK_SUPPLIER;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "IDX='" + IDX + '\'' +
                ", ENT_IDX='" + ENT_IDX + '\'' +
                ", ORDER_IDX='" + ORDER_IDX + '\'' +
                ", PRODUCT_NO='" + PRODUCT_NO + '\'' +
                ", PRODUCT_NAME='" + PRODUCT_NAME + '\'' +
                ", PRODUCT_DESC='" + PRODUCT_DESC + '\'' +
                ", ISSUE_QTY='" + ISSUE_QTY + '\'' +
                ", ISSUE_WEIGHT='" + ISSUE_WEIGHT + '\'' +
                ", ISSUE_VOLUME='" + ISSUE_VOLUME + '\'' +
                ", ISSUE_UOM='" + ISSUE_UOM + '\'' +
                ", REMARK_SUPPLIER='" + REMARK_SUPPLIER + '\'' +
                '}';
    }


}
