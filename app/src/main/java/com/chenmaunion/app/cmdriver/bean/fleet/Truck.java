package com.chenmaunion.app.cmdriver.bean.fleet;

import com.chenmaunion.app.cmdriver.adapter.SupplyTrucksAdapter;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/23.
 */
public class Truck {
    private String IDX;//司机Id
    private String PLATE_NUMBER;//车牌号
    private String VEHICLE_MODEL;//车辆类型
    private String VEHICLE_SIZE;//车辆尺寸
    private String BRAND_MODEL;//车辆品牌

    public String getIDX() {
        return IDX;
    }

    public void setIDX(String IDX) {
        this.IDX = IDX;
    }

    public String getPLATE_NUMBER() {
        return PLATE_NUMBER;
    }

    public void setPLATE_NUMBER(String PLATE_NUMBER) {
        this.PLATE_NUMBER = PLATE_NUMBER;
    }

    public String getVEHICLE_MODEL() {
        return VEHICLE_MODEL;
    }

    public void setVEHICLE_MODEL(String VEHICLE_MODEL) {
        this.VEHICLE_MODEL = VEHICLE_MODEL;
    }

    public String getVEHICLE_SIZE() {
        return VEHICLE_SIZE;
    }

    public void setVEHICLE_SIZE(String VEHICLE_SIZE) {
        this.VEHICLE_SIZE = VEHICLE_SIZE;
    }

    public String getBRAND_MODEL() {
        return BRAND_MODEL;
    }

    public void setBRAND_MODEL(String BRAND_MODEL) {
        this.BRAND_MODEL = BRAND_MODEL;
    }
}
