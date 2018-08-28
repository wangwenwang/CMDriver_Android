package com.chenmaunion.app.cmdriver.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.DriversAdapter;
import com.chenmaunion.app.cmdriver.bean.fleet.DriverAuthority;
import com.chenmaunion.app.cmdriver.bean.fleet.Driver;
import com.chenmaunion.app.cmdriver.bean.fleet.SDriver;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.widget.MLog;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/23.
 */
public class DriverManageActivity extends BaseActivity implements AsyncHttpCallback{
    private SlidingTitleView titleView;
    private RecyclerView recyclerView;
    private String FleetIdx;
    private DriverAsyncHttpClient mClient;
    private final String TAG_GetDriverList="GetDriverList";
    private final String TAG_DeleteDriver="DeleteDriver";
    private final String TAG_AddDriver="AddDriver";
    private final String TAG_InfoDriver="InfoDriver";
    private final String TAG_GetDriverInfo="GetDriverInfo";
    private DriversAdapter mAdapter;
    private List<Driver> drivers;
    private DriverAuthority authority;
    private TextView management;
    private Dialog driverAddDialog;
    private EditText ed_driver_search;
    private ImageButton bt_driverSearch;
    private Button bt_dialog_cancel;
    private TextView tv_driver_name,tv_driver_tel;
    private LinearLayout ll_driver_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivermanage);
        Intent intent=getIntent();
        if (intent.hasExtra("FleetIdx")){
            FleetIdx=intent.getStringExtra("FleetIdx");
        }else {
            finish();
        }
        mClient=new DriverAsyncHttpClient(this,this);
        initView();
        getData();
    }

    private void getData() {
        Map<String,String> params=new HashMap<>();
        params.put("UserId", SharedPreferencesUtils.getUserId());
        params.put("FleetIdx",FleetIdx);
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.GetDriverList,params,TAG_GetDriverList);


    }

    private void initView() {
        titleView= (SlidingTitleView) findViewById(R.id.slidingtilte_activity_drivermanage);
        titleView.setMode(SlidingTitleView.MODE_BACK);
        titleView.setText("司机管理");
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview_drivers);
        LinearLayoutManager manager=new LinearLayoutManager(DriverManageActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        drivers=new ArrayList<>();
        authority=new DriverAuthority();
        mAdapter=new DriversAdapter(authority,drivers,DriverManageActivity.this,FleetIdx);
        recyclerView.setAdapter(mAdapter);
    }

    public void deleteDriver(Driver driver) {
        Map<String,String> params=new HashMap<>();
        params.put("USER_IDX", SharedPreferencesUtils.getUserId());
        params.put("FLEET_ID",FleetIdx);
        params.put("DRIVER_IDX",driver.getIDX());
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.DeleteDriver,params,TAG_DeleteDriver);
    }

    public void addDriver(SDriver driver){
        Map<String,String> params=new HashMap<>();
        params.put("USER_IDX", SharedPreferencesUtils.getUserId());
        params.put("FLEET_IDX",FleetIdx);
        params.put("ADD_USER_IDX",driver.getIDX());
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.AddDriver,params,TAG_AddDriver);
    }

    public void infoDriver(Driver driver){
        Map<String,String> params=new HashMap<>();
        params.put("USER_IDX", SharedPreferencesUtils.getUserId());
        params.put("FLEET_ID",FleetIdx);
        params.put("INFO_USER_IDX",driver.getIDX());
        params.put("INFO_TYPE","ADMIN");
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.InfoDriver,params,TAG_InfoDriver);
    }

    /**
     * 创建选择添加司机 Dialog
     */
    private void driverAddDialog() {
        try {
//            AlertDialog.Builder builder = new AlertDialog.Builder(DriverManageActivity.this);
//            driverAddDialog = builder.create();
            driverAddDialog=new Dialog(DriverManageActivity.this);
            driverAddDialog.setCanceledOnTouchOutside(false);
            driverAddDialog.show();
            Window window = driverAddDialog.getWindow();
            window.setContentView(R.layout.dialog_driver_search);
            ll_driver_search= (LinearLayout) window.findViewById(R.id.ll_driver_search);
            ll_driver_search.setVisibility(View.GONE);
            ll_driver_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ll_driver_search.getTag()!=null) {
                        addDriver((SDriver) ll_driver_search.getTag());
                    }else {
                        showToastMsg("司机搜索异常，请取消后重新添加");
                    }
                }
            });
            tv_driver_name= (TextView) window.findViewById(R.id.tv_driver_name);
            tv_driver_tel= (TextView) window.findViewById(R.id.tv_driver_tel);
            ed_driver_search= (EditText) window.findViewById(R.id.ed_driver_search);
            ed_driver_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            if (imm!=null){
                                ed_driver_search.requestFocus();
                                Boolean isFocusable=ed_driver_search.isFocusable();
                                ed_driver_search.requestFocusFromTouch();
                                Boolean isShow=imm.showSoftInput(ed_driver_search,InputMethodManager.SHOW_IMPLICIT);
                                MLog.e("isFocusable:"+isFocusable.toString()+"\tisShow:"+isShow.toString());
                            }
                        }
                    },500);
                }
            });
            bt_driverSearch= (ImageButton) window.findViewById(R.id.bt_driver_search);
            bt_driverSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 //   getDriverInfo("13800138001");
                    if (ed_driver_search.getText().toString().trim().length()>=7){
                        getDriverInfo(ed_driver_search.getText().toString().trim());
                    }else {
                        showToastMsg("请填写正确的手机号~");
                    }
                }
            });

            bt_dialog_cancel = (Button) window.findViewById(R.id.bt_dialog_cancel);
            bt_dialog_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    driverAddDialog.cancel();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDriverInfo(String driver_tel) {
        Map<String,String> params=new HashMap<>();
        params.put("DRIVER_TEL",driver_tel);
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.GetDriverInfo,params,TAG_GetDriverInfo);

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
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            switch (request_tag){
                case TAG_GetDriverList:
                    showToastMsg("加载失败，请稍后再试");
                    break;
                case TAG_DeleteDriver:
                    showToastMsg("删除失败，请稍后再试");
                    break;
                default:
                    break;
            }
            return;
        }else if (request_tag.equals(TAG_GetDriverList)){
            try {
                JSONObject jos= JSON.parseObject(msg);
                JSONObject jo=jos.getJSONObject("result");
                drivers=JSON.parseArray(jo.getString("Driver"),Driver.class);
                authority=JSON.parseObject(jo.getString("Authority"),DriverAuthority.class);
                //authority=JSON.parseArray(jo.getString("Authority"),DriverAuthority.class).get(0);
                mAdapter.setData(drivers,authority);
                if (authority.getENABLE_ADD().equals("Y")){
                    management=titleView.getManagment();
                    management.setText("司机");
                    //在TextView中设置角标图片
                    Drawable dra=getResources().getDrawable(R.drawable.item_add);
                    dra.setBounds(0,0,dra.getMinimumWidth(),dra.getMinimumHeight());
                    management.setCompoundDrawables(dra,null,null,null);
                    management.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        //    showToastMsg("添加司机");
                            driverAddDialog();
                        }
                    });
                }
                if(driverAddDialog!=null&&driverAddDialog.isShowing()){
                    driverAddDialog.dismiss();
                }


            }catch (JSONException ex){
                ex.printStackTrace();
            }

        }else if (request_tag.equals(TAG_DeleteDriver)){
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
        }else if (request_tag.equals(TAG_InfoDriver)){
            try {
                JSONObject jo=JSON.parseObject(msg);
                int type=jo.getInteger("type");
                if (type==1){
                    showToastMsg("授权管理员成功");
                    getData();
                }else {
                    showToastMsg(jo.getString("msg"));
                }
            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }else if (request_tag.equals(TAG_AddDriver)){
            try {
                JSONObject jo=JSON.parseObject(msg);
                int type=jo.getInteger("type");
                if (type==1){
                    showToastMsg("添加成功");
                    getData();
                }else {
                    showToastMsg(jo.getString("msg"));
                }
            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }else if (request_tag.equals(TAG_GetDriverInfo)){
            try {
                JSONObject jos=JSON.parseObject(msg);
                JSONObject jo=jos.getJSONObject("result");
                SDriver driver=JSON.parseObject(jo.getString("Driver"),SDriver.class);
                ll_driver_search.setVisibility(View.VISIBLE);
                tv_driver_name.setText(driver.getUSERNAME());
                tv_driver_tel.setText(driver.getUSER_CODE());
                ll_driver_search.setTag(driver);

            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }

    }



}
