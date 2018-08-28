package com.chenmaunion.app.cmdriver.bean.fleet;

import java.io.Serializable;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/23.
 */
public class DriverAuthority implements Serializable {
    private String ENABLE_ADD;//可添加
    private String ENABLE_DELETE;// 可删除
    private String ENABLE_INFO;// 可授权

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

    public String getENABLE_INFO() {
        return ENABLE_INFO;
    }

    public void setENABLE_INFO(String ENABLE_INFO) {
        this.ENABLE_INFO = ENABLE_INFO;
    }

    @Override
    public String toString() {
        return "DriverAuthority{" +
                "ENABLE_ADD='" + ENABLE_ADD + '\'' +
                ", ENABLE_DELETE='" + ENABLE_DELETE + '\'' +
                ", ENABLE_INFO='" + ENABLE_INFO + '\'' +
                '}';
    }
}
