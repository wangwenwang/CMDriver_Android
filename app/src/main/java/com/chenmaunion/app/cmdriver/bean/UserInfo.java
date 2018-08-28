package com.chenmaunion.app.cmdriver.bean;

import java.io.Serializable;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/11.
 */
public class UserInfo implements Serializable{
    private String USER_NAME;//用户姓名
    private String USER_TYPE;//用户类型
    private String USER_CODE;//用户手机号码
    //    private String IDX;
    private String UserId;
    private String COMPANYNAME;//公司名称
    private String FLEETTYPE;//所属车队
    private String ADDRESS;//用户常住地
    private String RECOMNAME;//推荐人
    private String Avatar;//头像图片链接
    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_TYPE() {
        return USER_TYPE;
    }

    public void setUSER_TYPE(String USER_TYPE) {
        this.USER_TYPE = USER_TYPE;
    }

    public String getUSER_CODE() {
        return USER_CODE;
    }

    public void setUSER_CODE(String USER_CODE) {
        this.USER_CODE = USER_CODE;
    }

//    public String getIDX() {
//        return IDX;
//    }
//
//    public void setIDX(String IDX) {
//        this.IDX = IDX;
//    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getCOMPANYNAME() {
        return COMPANYNAME;
    }

    public void setCOMPANYNAME(String COMPANYNAME) {
        this.COMPANYNAME = COMPANYNAME;
    }

    public String getFLEETTYPE() {
        return FLEETTYPE;
    }

    public void setFLEETTYPE(String FLEETTYPE) {
        this.FLEETTYPE = FLEETTYPE;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getRECOMNAME() {
        return RECOMNAME;
    }

    public void setRECOMNAME(String RECOMNAME) {
        this.RECOMNAME = RECOMNAME;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }
}
