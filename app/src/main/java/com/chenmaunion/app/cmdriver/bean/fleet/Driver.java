package com.chenmaunion.app.cmdriver.bean.fleet;

import java.io.Serializable;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/23.
 */
public class Driver implements Serializable{
    private String IDX;//司机Id
    private String DRIVER_NAME;//司机名字
    private String CONTACT_TEL;//联系电话
    private DriverAuthority Authority;//权限类

    public String getIDX() {
        return IDX;
    }

    public void setIDX(String IDX) {
        this.IDX = IDX;
    }

    public String getDRIVER_NAME() {
        return DRIVER_NAME;
    }

    public void setDRIVER_NAME(String DRIVER_NAME) {
        this.DRIVER_NAME = DRIVER_NAME;
    }

    public String getCONTACT_TEL() {
        return CONTACT_TEL;
    }

    public void setCONTACT_TEL(String CONTACT_TEL) {
        this.CONTACT_TEL = CONTACT_TEL;
    }

    public DriverAuthority getAuthority() {
        return Authority;
    }

    public void setAuthority(DriverAuthority authority) {
        Authority = authority;
    }



}
