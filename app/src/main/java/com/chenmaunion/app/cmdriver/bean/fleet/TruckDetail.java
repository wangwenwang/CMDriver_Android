package com.chenmaunion.app.cmdriver.bean.fleet;

import java.io.Serializable;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/5/22.
 */
public class TruckDetail implements Serializable {
    private String CREATE_ID;//创建人Id
    private String FLEET_ID;//车队id
    private String PLATE_NUMBER;//车牌号
    private String VEHICLE_MODEL;//车辆类型
    private String VEHICLE_SIZE;//车辆尺寸
    private String BRAND_MODEL;//车辆品牌
    private String VEHICLE_COLOR;//车辆颜色
    private String MAX_WEIGHT;//最大载重量
    private String MAX_VOLUME;//最大装载体积
    private String PictureFile1;//行驶证正面
    private String PictureFile2;//行驶证副页
    private String AutographFile;//车头照片

    public String getCREATE_ID() {
        return CREATE_ID;
    }

    public void setCREATE_ID(String CREATE_ID) {
        this.CREATE_ID = CREATE_ID;
    }

    public String getFLEET_ID() {
        return FLEET_ID;
    }

    public void setFLEET_ID(String FLEET_ID) {
        this.FLEET_ID = FLEET_ID;
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

    public String getVEHICLE_COLOR() {
        return VEHICLE_COLOR;
    }

    public void setVEHICLE_COLOR(String VEHICLE_COLOR) {
        this.VEHICLE_COLOR = VEHICLE_COLOR;
    }

    public String getMAX_WEIGHT() {
        return MAX_WEIGHT;
    }

    public void setMAX_WEIGHT(String MAX_WEIGHT) {
        this.MAX_WEIGHT = MAX_WEIGHT;
    }

    public String getMAX_VOLUME() {
        return MAX_VOLUME;
    }

    public void setMAX_VOLUME(String MAX_VOLUME) {
        this.MAX_VOLUME = MAX_VOLUME;
    }

    public String getPictureFile1() {
        return PictureFile1;
    }

    public void setPictureFile1(String pictureFile1) {
        PictureFile1 = pictureFile1;
    }

    public String getPictureFile2() {
        return PictureFile2;
    }

    public void setPictureFile2(String pictureFile2) {
        PictureFile2 = pictureFile2;
    }

    public String getAutographFile() {
        return AutographFile;
    }

    public void setAutographFile(String autographFile) {
        AutographFile = autographFile;
    }
}
