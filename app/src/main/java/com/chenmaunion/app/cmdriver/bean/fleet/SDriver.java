package com.chenmaunion.app.cmdriver.bean.fleet;

import java.io.Serializable;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/4.
 */
public class SDriver implements Serializable {
    private String IDX;//司机Id
    private String USERNAME;//司机名字
    private String USERTYPE;//用户类型
    private String USER_CODE;//联系电话
    private String ADDRESS;
    private String COMPANYNAME;

    public String getIDX() {
        return IDX;
    }

    public void setIDX(String IDX) {
        this.IDX = IDX;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getUSERTYPE() {
        return USERTYPE;
    }

    public void setUSERTYPE(String USERTYPE) {
        this.USERTYPE = USERTYPE;
    }

    public String getUSER_CODE() {
        return USER_CODE;
    }

    public void setUSER_CODE(String USER_CODE) {
        this.USER_CODE = USER_CODE;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getCOMPANYNAME() {
        return COMPANYNAME;
    }

    public void setCOMPANYNAME(String COMPANYNAME) {
        this.COMPANYNAME = COMPANYNAME;
    }

    @Override
    public String toString() {
        return "SDriver{" +
                "IDX='" + IDX + '\'' +
                ", USERNAME='" + USERNAME + '\'' +
                ", USERTYPE='" + USERTYPE + '\'' +
                ", USER_CODE='" + USER_CODE + '\'' +
                ", ADDRESS='" + ADDRESS + '\'' +
                ", COMPANYNAME='" + COMPANYNAME + '\'' +
                '}';
    }
}
