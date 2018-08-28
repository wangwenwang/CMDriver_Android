package com.chenmaunion.app.cmdriver.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.DBTruckTeamsAdapter;
import com.chenmaunion.app.cmdriver.bean.fleet.Fleet;
import com.chenmaunion.app.cmdriver.constants.Constants;

import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_Fleet;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/22.
 */
public class TruckTeamListActivity extends BaseActivity implements AsyncHttpCallback,View.OnClickListener{
    private RecyclerView recyclerView;
    private DriverAsyncHttpClient mClient;
    private SlidingTitleView titleView;
    private LinearLayout ll_no_record;
    private final String TAG_GetFleetList="GetFleetList";
    private DBTruckTeamsAdapter mAdapter;
    private ObservableArrayList<DBModel_Fleet> model_fleets;
    private DBModel_Fleet fleet;
    private Button btn_addfleet;
    public static boolean isrefresh=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truckteams);
        mClient=new DriverAsyncHttpClient(this,this);
        initView();
        isrefresh=false;
    }

    private void initView() {
        titleView= (SlidingTitleView) findViewById(R.id.slidingtilte_activity_truckteams);
        titleView.setMode(SlidingTitleView.MODE_BACK);
        titleView.setText(getResources().getString(R.string.mine_truckteams));
        ll_no_record= (LinearLayout)findViewById(R.id.ll_no_record);
        ll_no_record.setOnClickListener(this);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview_truckteams);
        LinearLayoutManager manager=new LinearLayoutManager(TruckTeamListActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        mAdapter=new DBTruckTeamsAdapter();
        recyclerView.setAdapter(mAdapter);
        getData();
        btn_addfleet= (Button) findViewById(R.id.btn_activity_truckteams);
        btn_addfleet.setOnClickListener(this);
    }

    private void getData() {
        Map<String,String>params=new HashMap<>();
        params.put("UserId", SharedPreferencesUtils.getUserId());
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.GetFleetList,params,TAG_GetFleetList);
    }



    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        if (isrefresh){
            getData();
            isrefresh=false;
        }
        super.onResume();
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            switch (request_tag){
                case TAG_GetFleetList:
                    if (mAdapter.getItemCount()<=0) {
                        recyclerView.setVisibility(View.GONE);
                        ll_no_record.setVisibility(View.VISIBLE);
                    }else {
                        recyclerView.setVisibility(View.VISIBLE);
                        ll_no_record.setVisibility(View.GONE);
                    }
                    return;
                default:
                    return;
            }
        }else if (request_tag.equals(TAG_GetFleetList)){
            JSONObject jos= JSON.parseObject(msg);
            JSONObject jo=jos.getJSONObject("result");
            List<Fleet> ja=JSON.parseArray(jo.getString("Fleet"),Fleet.class);
            model_fleets=new ObservableArrayList<>();
            for (int i=0;i<ja.size();i++){
                fleet=new DBModel_Fleet();
                fleet.fleet2DBModelFleet(ja.get(i));
                model_fleets.add(fleet);
            }
            if (mAdapter.getItemCount()<=0&&model_fleets.size()<=0){
                recyclerView.setVisibility(View.GONE);
                ll_no_record.setVisibility(View.VISIBLE);
                return;
            }
            if (recyclerView.getVisibility()==View.GONE){
                recyclerView.setVisibility(View.VISIBLE);
                ll_no_record.setVisibility(View.GONE);
            }
            mAdapter.setData(model_fleets);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_no_record:
                getData();
                break;
            case R.id.btn_activity_truckteams:
                // 点击添加车队按钮
                Intent intent=new Intent(this, FleetAddActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}




