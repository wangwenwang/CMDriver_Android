package com.chenmaunion.app.cmdriver.viewmodel;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.view.View;

import com.chenmaunion.app.cmdriver.bean.fleet.Fleet;
import com.chenmaunion.app.cmdriver.ui.activity.FleetAddActivity;
import com.chenmaunion.app.cmdriver.ui.activity.TruckTeamActivity;
import com.chenmaunion.app.cmdriver.ui.activity.TruckTeamListActivity;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/22.
 */
public class DBModel_Fleet {
    public ObservableField<String> IDX;
    public ObservableField<String> FLEET_PROPERTY;
    public ObservableField<String> TMS_NAME;
    public ObservableField<String> FLEET_NAME;
    public ObservableField<String> FLEET_DESC;
    public ObservableField<String> CONTACT_PERSON;
    public ObservableField<String> CONTACT_TEL;
    public ObservableField<Boolean> Fleet_Bg;
    private Intent intent;
    public DBModel_Fleet() {
        IDX=new ObservableField<>();
        FLEET_PROPERTY=new ObservableField<>();
        TMS_NAME=new ObservableField<>();
        FLEET_NAME=new ObservableField<>();
        FLEET_DESC=new ObservableField<>();
        CONTACT_PERSON=new ObservableField<>();
        CONTACT_TEL=new ObservableField<>();
        Fleet_Bg=new ObservableField<>();

    }

    public void fleet2DBModelFleet(Fleet fleet){
        IDX=new ObservableField<>(fleet.getIDX());
        FLEET_PROPERTY=new ObservableField<>(fleet.getFLEET_PROPERTY());
        if (FLEET_PROPERTY.get().equals("INDIVIDUAL")){
            //个人车队的条目设置对应样式
            Fleet_Bg=new ObservableField<>(true);
        }else {
            Fleet_Bg=new ObservableField<>(false);
        }
        TMS_NAME=new ObservableField<>(fleet.getTMS_NAME());
        FLEET_NAME=new ObservableField<>(fleet.getFLEET_NAME());
        FLEET_DESC=new ObservableField<>(fleet.getFLEET_DESC());
        CONTACT_PERSON=new ObservableField<>(fleet.getCONTACT_PERSON());
        CONTACT_TEL=new ObservableField<>(fleet.getCONTACT_TEL());
    }

    /**
     * 点击车队条目跳转到所选车队页面
     * @param view
     */
    public void onFleetClick(View view){
        intent=new Intent("com.chenmaunion.app.cmdriver.ui.activity.TruckTeamActivity");
        intent.putExtra("FleetIdx",IDX.get());
        intent.putExtra("TeamName",FLEET_NAME.get());
        view.getContext().startActivity(intent);
    }



}
