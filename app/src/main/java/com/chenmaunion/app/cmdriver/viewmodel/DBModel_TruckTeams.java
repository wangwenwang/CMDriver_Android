package com.chenmaunion.app.cmdriver.viewmodel;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.view.View;

import com.chenmaunion.app.cmdriver.ui.activity.FleetAddActivity;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/22.
 */
public class DBModel_TruckTeams {
    public ObservableField<Boolean> noRecord_visiable;
    private Intent intent;


    public DBModel_TruckTeams() {
        noRecord_visiable=new ObservableField<>(false);
    }


}
