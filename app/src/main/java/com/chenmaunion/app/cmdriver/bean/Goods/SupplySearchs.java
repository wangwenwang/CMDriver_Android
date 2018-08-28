package com.chenmaunion.app.cmdriver.bean.Goods;

import java.io.Serializable;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/1.
 */
public class SupplySearchs implements Serializable {
    private String ORG_IDX;
    private String SUPPLY_VEHICLE_TYPE;
    private String SUPPLY_VEHICLE_SIZE;
    private String ROUTES_CITY;

    public String getORG_IDX() {
        return ORG_IDX;
    }

    public void setORG_IDX(String ORG_IDX) {
        this.ORG_IDX = ORG_IDX;
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

    public String getROUTES_CITY() {
        return ROUTES_CITY;
    }

    public void setROUTES_CITY(String ROUTES_CITY) {
        this.ROUTES_CITY = ROUTES_CITY;
    }

    @Override
    public String toString() {
        return "SupplySearchs{" +
                "ORG_IDX='" + ORG_IDX + '\'' +
                ", SUPPLY_VEHICLE_TYPE='" + SUPPLY_VEHICLE_TYPE + '\'' +
                ", SUPPLY_VEHICLE_SIZE='" + SUPPLY_VEHICLE_SIZE + '\'' +
                ", ROUTES_CITY='" + ROUTES_CITY + '\'' +
                '}';
    }
}
