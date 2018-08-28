package com.chenmaunion.app.cmdriver.viewmodel;

import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import com.chenmaunion.app.cmdriver.bean.order.Shipment;
import com.chenmaunion.app.cmdriver.ui.activity.OrderDetailActivity;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/11.
 */
public class DBModel_ShipMent {
    public ObservableField<String> IDX;
    public ObservableField<String> SHIPMENT_NO;
    public ObservableField<String> ORG_IDX;
    public ObservableField<String> ORG_NAME;
    public ObservableField<String> TMS_IDX;
    public ObservableField<String> TMS_SHIPMENT_NO;
    public ObservableField<String> SUPPLY_IDX;
    public ObservableField<String> DATE_LOAD;
    public ObservableField<String> DATE_CONFIRMED;
    public ObservableField<String> DATE_ISSUE;
    public ObservableField<String> FLEET_IDX;
    public ObservableField<String> FLEET_NAME;
    public ObservableField<String> VEHICLE_IDX;
    public ObservableField<String> PLATE_NUMBER;
    public ObservableField<String> VEHICLE_SIZE;
    public ObservableField<String> VEHICLE_TYPE;
    public ObservableField<String> DRIVER_IDX;
    public ObservableField<String> DRIVER_NAME;
    public ObservableField<String> DRIVER_TEL;
    public ObservableField<String> SHIPMENT_STATE;
    public ObservableField<String> TOTAL_WEIGHT;
    public ObservableField<String> TOTAL_VOLUME;
    public ObservableField<String> FROM_CITY;
    public ObservableField<String> TO_CITY;
    public ObservableField<String> AUDIT_FLAG;
    public ObservableField<String> USER_NAME;
    public ObservableField<String> DATE_ADD;
    public ObservableField<String> DATE_EDIT;
    public ObservableField<String> REFERENCE01;
    public ObservableField<String> REFERENCE02;
    public ObservableField<String> REFERENCE03;
    public ObservableField<String> REFERENCE04;
    public ObservableField<String> REFERENCE05;
    public ObservableField<String> REFERENCE06;

    public DBModel_ShipMent() {
        IDX=new ObservableField<>();
        SHIPMENT_NO=new ObservableField<>();
        ORG_IDX=new ObservableField<>();
        ORG_NAME=new ObservableField<>();
        TMS_IDX=new ObservableField<>();
        TMS_SHIPMENT_NO=new ObservableField<>();
        SUPPLY_IDX=new ObservableField<>();
        DATE_LOAD=new ObservableField<>();
        DATE_CONFIRMED=new ObservableField<>();
        DATE_ISSUE=new ObservableField<>();
        FLEET_IDX=new ObservableField<>();
        FLEET_NAME=new ObservableField<>();
        VEHICLE_IDX=new ObservableField<>();
        PLATE_NUMBER=new ObservableField<>();
        VEHICLE_SIZE=new ObservableField<>();
        VEHICLE_TYPE=new ObservableField<>();
        DRIVER_IDX=new ObservableField<>();
        DRIVER_NAME=new ObservableField<>();
        DRIVER_TEL=new ObservableField<>();
        SHIPMENT_STATE=new ObservableField<>();
        TOTAL_WEIGHT=new ObservableField<>();
        TOTAL_VOLUME=new ObservableField<>();
        FROM_CITY=new ObservableField<>();
        TO_CITY=new ObservableField<>();
        AUDIT_FLAG=new ObservableField<>();
        USER_NAME=new ObservableField<>();
        DATE_ADD=new ObservableField<>();
        DATE_EDIT=new ObservableField<>();
        REFERENCE01=new ObservableField<>();
        REFERENCE02=new ObservableField<>();
        REFERENCE03=new ObservableField<>();
        REFERENCE04=new ObservableField<>();
        REFERENCE05=new ObservableField<>();
        REFERENCE06=new ObservableField<>();
    }
    public void shipment2DBModelShipMent(Shipment shipment) {
        IDX.set(shipment.getIDX());
        SHIPMENT_NO.set(shipment.getSHIPMENT_NO());
        ORG_IDX.set(shipment.getORG_IDX());
        ORG_NAME.set(shipment.getORG_NAME());
        TMS_IDX.set(shipment.getTMS_IDX());
        TMS_SHIPMENT_NO.set(shipment.getTMS_SHIPMENT_NO());
        SUPPLY_IDX.set(shipment.getSUPPLY_IDX());
        DATE_LOAD.set(shipment.getDATE_LOAD());
        DATE_CONFIRMED.set(shipment.getDATE_CONFIRMED());
        DATE_ISSUE.set(shipment.getDATE_ISSUE());
        FLEET_IDX.set(shipment.getFLEET_IDX());
        FLEET_NAME.set(shipment.getFLEET_NAME());
        VEHICLE_IDX.set(shipment.getVEHICLE_IDX());
        PLATE_NUMBER.set(shipment.getPLATE_NUMBER());
        VEHICLE_SIZE.set(shipment.getVEHICLE_SIZE());
        VEHICLE_TYPE.set(shipment.getVEHICLE_TYPE());
        DRIVER_IDX.set(shipment.getDRIVER_IDX());
        DRIVER_NAME.set(shipment.getDRIVER_NAME());
        DRIVER_TEL.set(shipment.getDRIVER_TEL());
        SHIPMENT_STATE.set(toSHIPMENT_STATE(shipment.getSHIPMENT_STATE()));
        TOTAL_WEIGHT.set(shipment.getTOTAL_WEIGHT());
        TOTAL_VOLUME.set(shipment.getTOTAL_VOLUME());
        FROM_CITY.set(shipment.getFROM_CITY());
        TO_CITY.set(shipment.getTO_CITY());
        AUDIT_FLAG.set(shipment.getAUDIT_FLAG());
        USER_NAME.set(shipment.getUSER_NAME());
        DATE_ADD.set(shipment.getDATE_ADD());
        DATE_EDIT.set(shipment.getDATE_EDIT());
        REFERENCE01.set(shipment.getREFERENCE01());
        REFERENCE02.set(shipment.getREFERENCE02());
        REFERENCE03.set(shipment.getREFERENCE03());
        REFERENCE04.set(shipment.getREFERENCE04());
        REFERENCE05.set(shipment.getREFERENCE05());
        REFERENCE06.set(shipment.getREFERENCE06());

    }

    private String toSHIPMENT_STATE(String shipment_state) {
        switch (shipment_state){
            case "NEW":
                return "新建";
            case "INTRANSIT":
                return "已出库";
            case "CONFIRMED":
                return "已确认";
            case "CLOSE":
                return "已关闭";
            default:
                return shipment_state;
        }


    }

    /**
     * 订单CardView点击跳转到查看订单详情
     * @param view
     */
    public void onCardViewClick(View view){
        Intent intent=new Intent("com.chenmaunion.app.cmdriver.ui.activity.ShipmentInfoActivity");
       // intent.putExtra("notify_id",IDX.get());
        intent.putExtra("SHIPMENT_IDX",IDX.get());
        view.getContext().startActivity(intent);
    }
}
