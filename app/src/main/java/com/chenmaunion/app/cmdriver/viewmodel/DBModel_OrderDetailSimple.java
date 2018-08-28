package com.chenmaunion.app.cmdriver.viewmodel;

import android.databinding.ObservableField;

import com.chenmaunion.app.cmdriver.bean.order.Order;
import com.chenmaunion.app.cmdriver.bean.order.SmOrderDetails;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/20.
 */
public class DBModel_OrderDetailSimple {
    public ObservableField<String> PRODUCT_NO;
    public ObservableField<String> PRODUCT_NAME;
   // public ObservableField<String> ORDER_UOM;
    public ObservableField<String> ISSUE_QTY;
    public ObservableField<String> ISSUE_WEIGHT;
    public ObservableField<String> ISSUE_VOLUME;
    public ObservableField<String> REMARK_SUPPLIER;
    public ObservableField<String> PRODUCT_DESC;

    public void orderdetail2DBOrderDetailSimple(SmOrderDetails orderDetails){
            PRODUCT_NO=new ObservableField<>(orderDetails.getPRODUCT_NO());
            PRODUCT_NAME=new ObservableField<>(orderDetails.getPRODUCT_NAME());
           // ORDER_UOM=new ObservableField<>(orderDetails.getORDER_UOM());
            ISSUE_QTY=new ObservableField<>(orderDetails.getISSUE_QTY());
            ISSUE_WEIGHT=new ObservableField<>(orderDetails.getISSUE_WEIGHT());
            ISSUE_VOLUME=new ObservableField<>(orderDetails.getISSUE_VOLUME());
            REMARK_SUPPLIER=new ObservableField<>(orderDetails.getREMARK_SUPPLIER());
          PRODUCT_DESC=new ObservableField<>(orderDetails.getPRODUCT_DESC());
    }

}
