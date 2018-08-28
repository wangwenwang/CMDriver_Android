package com.chenmaunion.app.cmdriver.viewmodel;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.view.View;
import com.chenmaunion.app.cmdriver.bean.order.Order;
import com.chenmaunion.app.cmdriver.bean.order.StateTack;
import com.chenmaunion.app.cmdriver.ui.activity.OrderDetailActivity;
import com.kaidongyuan.app.basemodule.utils.nomalutils.StringUtils;

import java.text.DecimalFormat;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/13.
 */
public class DBModel_Order  {
    public ObservableField<String> TMS_DATE_LOAD;
    public ObservableField<String> TMS_DATE_ISSUE;
    public ObservableField<String> TMS_SHIPMENT_NO;
    public ObservableField<String> TMS_FLEET_NAME;
    public ObservableField<String> ORD_IDX;
    public ObservableField<String> IDX;
    public ObservableField<String> ORD_NO;
    public ObservableField<String> ORD_TO_NAME;
    public ObservableField<String> PARTY_TYPE;
    public ObservableField<String> ORD_TO_CNAME;
    public ObservableField<String> ORD_TO_ADDRESS;
    public ObservableField<String> ORD_QTY;
    public ObservableField<String> ORD_WEIGHT;
    public ObservableField<String> ORD_VOLUME;
    public ObservableField<String> ORD_ISSUE_QTY;
    public ObservableField<String> ORD_ISSUE_WEIGHT;
    public ObservableField<String> ORD_ISSUE_VOLUME;
    public ObservableField<String> ORD_WORKFLOW;
    public ObservableField<String> SPLIT_TYPE;
    public ObservableField<String> OTS_RETURN_DATE;
    public ObservableField<String> OTS_DELIVERY_ACTUAL;
    public ObservableField<String> OMS_PARENT_NO;
    public ObservableField<String> ORD_STATE;
    public ObservableField<String> ORD_DATE_ADD;
    public ObservableField<String> ADD_CODE;
    public ObservableField<String> ORD_REQUEST_ISSUE;
    public ObservableField<String> ORD_FROM_NAME;
    public ObservableField<String> FROM_COORDINATE;//起始点坐标
    public ObservableField<String> TO_COORDINATE;//到达点坐标
    public ObservableField<String> ORD_REMARK_CLIENT;
    public ObservableField<String> TMS_DRIVER_NAME;//司机姓名
    public ObservableField<String> TMS_PLATE_NUMBER;//车牌号
    public ObservableField<String> TMS_DRIVER_TEL;//司机电话
    public ObservableField<String> PAYMENT_TYPE;
    public ObservableField<Double> ORG_PRICE;
    public ObservableField<Double> ACT_PRICE;
    public ObservableField<Double> MJ_PRICE;
    public ObservableField<String> ORD_REMARK_CONSIGNEE;
    public ObservableField<String> MJ_REMARK;
    public ObservableField<String> DRIVER_PAY;
    public ObservableArrayList<Order.OrderDetails> OrderDetails;
    public ObservableArrayList<StateTack> StateTack;
    private DecimalFormat decimalFormat;

    public void order2DBModelOrder(Order order){
        TMS_DATE_LOAD=new ObservableField<>(order.getTMS_DATE_LOAD());
        TMS_DATE_ISSUE=new ObservableField<>(order.getTMS_DATE_ISSUE());
        TMS_SHIPMENT_NO=new ObservableField<>(order.getTMS_SHIPMENT_NO());
        TMS_FLEET_NAME=new ObservableField<>(order.getTMS_FLEET_NAME());
        ORD_IDX=new ObservableField<>(order.getORD_IDX());
        IDX=new ObservableField<>(order.getIDX());
        ORD_NO=new ObservableField<>(order.getORD_NO());
        ORD_TO_NAME=new ObservableField<>(order.getORD_TO_NAME());
        PARTY_TYPE=new ObservableField<>(order.getPARTY_TYPE());
        ORD_TO_CNAME=new ObservableField<>(order.getORD_TO_CNAME());
        ORD_TO_ADDRESS=new ObservableField<>(StringUtils.subAllTargetCharSequence(order.getORD_TO_ADDRESS(),"*"));//剔除约定的以*代替无法查询行政级别名称的地址字段
        ORD_QTY=new ObservableField<>(order.getORD_QTY());
        ORD_WEIGHT=new ObservableField<>(order.getORD_WEIGHT());
        ORD_VOLUME=new ObservableField<>(order.getORD_VOLUME());
        decimalFormat = new DecimalFormat("0.00");//将double类型转换成String保留两位小数，不四舍五入
        ORD_ISSUE_QTY=new ObservableField<>(decimalFormat.format(Double.parseDouble(order.getORD_ISSUE_QTY()))+ "件");
        ORD_ISSUE_WEIGHT=new ObservableField<>(decimalFormat.format(Double.parseDouble(order.getORD_ISSUE_WEIGHT()))+ "吨");
        ORD_ISSUE_VOLUME=new ObservableField<>(decimalFormat.format(Double.parseDouble(order.getORD_ISSUE_VOLUME()))+ "m³");
        ORD_WORKFLOW=new ObservableField<>(order.getORD_WORKFLOW());
        SPLIT_TYPE=new ObservableField<>(order.getSPLIT_TYPE());
        OTS_RETURN_DATE=new ObservableField<>(order.getOTS_RETURN_DATE());
        OTS_DELIVERY_ACTUAL=new ObservableField<>(order.getOTS_DELIVERY_ACTUAL());
        OMS_PARENT_NO=new ObservableField<>(order.getOMS_PARENT_NO());
        ORD_STATE=new ObservableField<>(order.getORD_STATE());
        ORD_DATE_ADD=new ObservableField<>(order.getORD_DATE_ADD());
        ADD_CODE=new ObservableField<>(order.getADD_CODE());
        ORD_REQUEST_ISSUE=new ObservableField<>(order.getORD_REQUEST_ISSUE());
        ORD_FROM_NAME=new ObservableField<>(order.getORD_FROM_NAME());
        FROM_COORDINATE=new ObservableField<>(order.getFROM_COORDINATE());
        TO_COORDINATE=new ObservableField<>(order.getTO_COORDINATE());
        ORD_REMARK_CLIENT=new ObservableField<>(order.getORD_REMARK_CLIENT());
        TMS_DRIVER_NAME=new ObservableField<>(order.getTMS_DRIVER_NAME());
        TMS_PLATE_NUMBER=new ObservableField<>(order.getTMS_PLATE_NUMBER());
        TMS_DRIVER_TEL=new ObservableField<>(order.getTMS_DRIVER_TEL());
        PAYMENT_TYPE=new ObservableField<>(order.getPAYMENT_TYPE());
        ORG_PRICE=new ObservableField<>(order.getORG_PRICE());
        ACT_PRICE=new ObservableField<>(order.getACT_PRICE());
        MJ_PRICE=new ObservableField<>(order.getMJ_PRICE());
        ORD_REMARK_CONSIGNEE=new ObservableField<>(order.getORD_REMARK_CONSIGNEE());
        MJ_REMARK=new ObservableField<>(order.getMJ_REMARK());
        DRIVER_PAY=new ObservableField<>(order.getDRIVER_PAY());
        OrderDetails=new ObservableArrayList<>();
        OrderDetails.addAll(order.getOrderDetails());
        StateTack=new ObservableArrayList<>();
        StateTack.addAll(order.getStateTack());
    }

    /**
     * 订单CardView点击跳转到查看订单详情
     * @param view
     */
    public void onCardViewClick(View view){
        Intent intent=new Intent(view.getContext(), OrderDetailActivity.class);
        intent.putExtra("order_id",ORD_IDX.get().trim());
        view.getContext().startActivity(intent);
    }

}
