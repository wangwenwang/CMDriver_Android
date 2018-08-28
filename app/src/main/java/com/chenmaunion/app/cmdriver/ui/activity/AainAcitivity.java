package com.chenmaunion.app.cmdriver.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.DBNotifyAdapter;
import com.chenmaunion.app.cmdriver.databinding.ActivityDatabingBinding;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_Main;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/1/3.
 */
public class AainAcitivity extends BaseActivity{
   public DBNotifyAdapter dbNotifyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDatabingBinding binding=DataBindingUtil.setContentView(this,R.layout.activity_databing);
        binding.setModel(new DBModel_Main(this));
        LinearLayoutManager manager=new LinearLayoutManager(AainAcitivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerviewDatabing.setLayoutManager(manager);
        dbNotifyAdapter=new DBNotifyAdapter();
        binding.recyclerviewDatabing.setAdapter(dbNotifyAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
