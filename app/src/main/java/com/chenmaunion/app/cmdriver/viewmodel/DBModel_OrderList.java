package com.chenmaunion.app.cmdriver.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.chenmaunion.app.cmdriver.bean.order.Order;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/11.
 */
public class DBModel_OrderList implements AsyncHttpCallback{
    public ObservableField<String> truckteam;
    public ObservableArrayList<Order>orders;






    @Override
    public void postSuccessMsg(String msg, String request_tag) {

    }
}
