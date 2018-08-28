package com.chenmaunion.app.cmdriver.bean.fleet;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/23.
 */
public class TruckAuthority {
    private String ENABLE_ADD;//可添加
    private String ENABLE_DELETE;// 可删除


    public String getENABLE_ADD() {
        return ENABLE_ADD;
    }

    public void setENABLE_ADD(String ENABLE_ADD) {
        this.ENABLE_ADD = ENABLE_ADD;
    }

    public String getENABLE_DELETE() {
        return ENABLE_DELETE;
    }

    public void setENABLE_DELETE(String ENABLE_DELETE) {
        this.ENABLE_DELETE = ENABLE_DELETE;
    }

}
