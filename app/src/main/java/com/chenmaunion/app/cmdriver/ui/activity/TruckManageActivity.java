package com.chenmaunion.app.cmdriver.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.DriversAdapter;
import com.chenmaunion.app.cmdriver.adapter.TrucksAdapter;
import com.chenmaunion.app.cmdriver.bean.fleet.Driver;
import com.chenmaunion.app.cmdriver.bean.fleet.DriverAuthority;
import com.chenmaunion.app.cmdriver.bean.fleet.Truck;
import com.chenmaunion.app.cmdriver.bean.fleet.TruckAuthority;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆管理
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/23.
 */
public class TruckManageActivity extends BaseActivity implements AsyncHttpCallback {
    private SlidingTitleView titleView;
    private RecyclerView recyclerView;
    private String FleetIdx;
    private DriverAsyncHttpClient mClient;
    private final String TAG_GetVehicleList="GetVehicleList";
    private final String TAG_DeleteVehicle="DeleteVehicle";
    private final String TAG_AddVehicle="AddVehicle";
    private TrucksAdapter mAdapter;
    private List<Truck> trucks;
    private TruckAuthority authority;
    private TextView management;
    public static boolean isrefresh=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turckmanage);
        Intent intent=getIntent();
        if (intent.hasExtra("FleetIdx")){
            FleetIdx=intent.getStringExtra("FleetIdx");
        }else {
            finish();
        }
        mClient=new DriverAsyncHttpClient(this,this);
        initView();
        getData();
        isrefresh=false;
    }

    @Override
    public void onResume() {
        if (isrefresh){
            getData();
            isrefresh=false;
        }
        super.onResume();
    }

    private void getData() {
        Map<String,String> params=new HashMap<>();
        params.put("UserId", SharedPreferencesUtils.getUserId());
        params.put("FleetIdx",FleetIdx);
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.GetVehicleList,params,TAG_GetVehicleList);

    }

    public void deleteDriver(Truck truck) {
        Map<String,String> params=new HashMap<>();
        params.put("USER_IDX", SharedPreferencesUtils.getUserId());
        params.put("FLEET_ID",FleetIdx);
        params.put("VEHICLE_IDX",truck.getIDX());
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.DeleteVehicle,params,TAG_DeleteVehicle);
    }


    private void initView() {
        titleView= (SlidingTitleView) findViewById(R.id.slidingtilte_activity_truckmanage);
        titleView.setMode(SlidingTitleView.MODE_BACK);
        titleView.setText("车辆管理");
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview_trucks);
        LinearLayoutManager manager=new LinearLayoutManager(TruckManageActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        trucks=new ArrayList<>();
        authority=new TruckAuthority();
        mAdapter=new TrucksAdapter(authority,trucks,TruckManageActivity.this,FleetIdx);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            switch (request_tag){
                case TAG_GetVehicleList:
                    showToastMsg("加载失败，请稍后再试");
                    break;
                case TAG_DeleteVehicle:
                    showToastMsg("删除失败，请稍后再试");
                    break;
                default:
                    break;
            }
            return;
        }else if (request_tag.equals(TAG_GetVehicleList)){
            try {
                JSONObject jos= JSON.parseObject(msg);
                JSONObject jo=jos.getJSONObject("result");
                trucks=JSON.parseArray(jo.getString("Vehicle"),Truck.class);
                authority=JSON.parseObject(jo.getString("Authority"),TruckAuthority.class);
                mAdapter.setData(authority,trucks);
                if (authority.getENABLE_ADD().equals("Y")){
                    management=titleView.getManagment();
                    management.setText("车辆");
                    //在TextView中设置角标图片
                    Drawable dra=getResources().getDrawable(R.drawable.item_add);
                    dra.setBounds(0,0,dra.getMinimumWidth(),dra.getMinimumHeight());
                    management.setCompoundDrawables(dra,null,null,null);
                    management.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(TruckManageActivity.this,AddTruckActivity.class);
                            intent.putExtra("FleetIdx",FleetIdx);
                            startActivity(intent);
                        }
                    });
                }
            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }else if (request_tag.equals(TAG_DeleteVehicle)){
            try {
                    JSONObject jo=JSON.parseObject(msg);
                    int type=jo.getInteger("type");
                if (type==1){
                    showToastMsg("成功删除");
                    getData();
                }else {
                    showToastMsg(jo.getString("msg"));
                }
            }catch (JSONException ex){
                ex.printStackTrace();
            }


        }

    }
}
