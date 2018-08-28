package com.chenmaunion.app.cmdriver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/22.
 */
public class TruckTeamActivity extends BaseActivity {
    private SlidingTitleView titleview;
    private TextView tv_truck_manager,tv_driver_manager;
    private String teamName,fleetIdx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truckteam);
        Intent intent=getIntent();
        if (intent.hasExtra("TeamName")&&intent.hasExtra("FleetIdx")){
            teamName=intent.getStringExtra("TeamName");
            fleetIdx=intent.getStringExtra("FleetIdx");
        }else {
            finish();
        }
        initView();

    }

    private void initView() {
        titleview= (SlidingTitleView) findViewById(R.id.slidingtilte_activity_truckteam);
        titleview.setMode(SlidingTitleView.MODE_BACK);
        titleview.setText(teamName);
        tv_driver_manager= (TextView) findViewById(R.id.tv_driver_manager);
        tv_driver_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TruckTeamActivity.this,DriverManageActivity.class);
                intent.putExtra("FleetIdx",fleetIdx);
                startActivity(intent);
            }
        });
        tv_truck_manager= (TextView) findViewById(R.id.tv_truck_manager);
        tv_truck_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TruckTeamActivity.this,TruckManageActivity.class);
                intent.putExtra("FleetIdx",fleetIdx);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
