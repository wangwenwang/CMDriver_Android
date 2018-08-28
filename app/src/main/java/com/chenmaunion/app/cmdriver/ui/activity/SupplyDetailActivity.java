package com.chenmaunion.app.cmdriver.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.RoutesAdapter;
import com.chenmaunion.app.cmdriver.adapter.SupplyDriversAdapter;
import com.chenmaunion.app.cmdriver.adapter.SupplyFleetsAdapter;
import com.chenmaunion.app.cmdriver.adapter.SupplyTrucksAdapter;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.bean.Goods.Routes;
import com.chenmaunion.app.cmdriver.bean.Goods.SupplyInfo;
import com.chenmaunion.app.cmdriver.bean.fleet.Driver;
import com.chenmaunion.app.cmdriver.bean.fleet.Fleet;
import com.chenmaunion.app.cmdriver.bean.fleet.SDriver;
import com.chenmaunion.app.cmdriver.bean.fleet.Truck;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.ui.fragment.GoodsFragment;
import com.chenmaunion.app.cmdriver.ui.widget.DividerListItemDecoration;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.chenmaunion.app.cmdriver.utils.baiduMapUtils.DataUtil;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.utils.nomalutils.StringUtils;
import com.kaidongyuan.app.basemodule.widget.MLog;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/2.
 */
public class SupplyDetailActivity extends BaseActivity  implements AsyncHttpCallback{
    private DriverAsyncHttpClient mClient;
    private final String TAG_GetSupplyInfo="GetSupplyInfo";
    private final String TAG_GetFleetList="GetFleetList";
    private final String TAG_GetDriverList="GetDriverList";
    private final String TAG_GetVehicleList="GetVehicleList";
    private final String TAG_ReceivingSupply="ReceivingSupply";
    private Dialog choosefleetDialog,choosedriverDialog,choosetruckDialog;
    private ListView lv_chooseFleet,lv_chooseDriver,lv_choiceTruck;
    private TextView tv_Fleet,tv_Driver,tv_Truck;
    private Fleet fleet;
    private Driver driver;
    private Truck truck;
    private SupplyFleetsAdapter fleetsAdapter;
    private SupplyDriversAdapter driversAdapter;
    private SupplyTrucksAdapter trucksAdapter;
    private SlidingTitleView titleview;
    private TextView tv_SUPPLY_NO,tv_TMS_SHIPMENT_NO,tv_REQUEST_WAREHOUSE,tv_REQUEST_ISSUE,tv_DISTRIBUTION_EXPERIENCE,tv_ORG_NAME,tv_ROUTES_CITY,
            tv_SUPPLY_TYPE,tv_TOTAL_AMOUNT,tv_IS_RETURN,tv_HANDLING_DEGREE,tv_IS_HANDLING,tv_HAVE_LOAD,tv_HAVE_UNLOAD,tv_SUPPLY_VEHICLE_SIZE,
            tv_SUPPLY_VEHICLE_TYPE,tv_IS_RETURN_STORE,tv_TOTAL_DROP,tv_TOTAL_ROUTES,tv_TOTAL_VOLUME,tv_TOTAL_WEIGHT,tv_TOTAL_QTY,tv_TASK_DESCRIPTION,
            tv_TASK_DESCRIPTION_OTHER;
    private Button bt_supply_accept,bt_cancel_chooseFleetDialog,bt_cancel_chooseDriverDialog,bt_cancel_choiceTruckDialog;
    private RecyclerView recyclerview_supplydetail_routes;
    private String supply_idx;
    private List<Routes> routes;
    private SupplyInfo supplyInfo;
    private RoutesAdapter routesadtapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplydetail);
        mClient=new DriverAsyncHttpClient(this,this);
        mClient.setShowToast(false);
        if (getIntent().hasExtra("IDX")){
            supply_idx=getIntent().getStringExtra("IDX");
        }
        initView();
        getData();
    }

    private void initView() {
        titleview= (SlidingTitleView) findViewById(R.id.title_supplydetail_activtiy);
        titleview.setMode(SlidingTitleView.MODE_BACK);
        titleview.setText("货源详情");
        tv_SUPPLY_NO= (TextView) findViewById(R.id.tv_SUPPLY_NO);
        tv_TMS_SHIPMENT_NO= (TextView) findViewById(R.id.tv_TMS_SHIPMENT_NO);
        tv_REQUEST_WAREHOUSE= (TextView) findViewById(R.id.tv_REQUEST_WAREHOUSE);
        tv_REQUEST_ISSUE= (TextView) findViewById(R.id.tv_REQUEST_ISSUE);
        tv_DISTRIBUTION_EXPERIENCE= (TextView) findViewById(R.id.tv_DISTRIBUTION_EXPERIENCE);
        tv_ORG_NAME= (TextView) findViewById(R.id.tv_ORG_NAME);
        tv_ROUTES_CITY= (TextView) findViewById(R.id.tv_ROUTES_CITY);
        tv_SUPPLY_TYPE= (TextView) findViewById(R.id.tv_SUPPLY_TYPE);
        tv_TOTAL_AMOUNT= (TextView) findViewById(R.id.tv_TOTAL_AMOUNT);
        tv_IS_RETURN= (TextView) findViewById(R.id.tv_IS_RETURN);
        tv_HANDLING_DEGREE= (TextView) findViewById(R.id.tv_HANDLING_DEGREE);
        tv_IS_HANDLING= (TextView) findViewById(R.id.tv_IS_HANDLING);
        tv_HAVE_LOAD= (TextView) findViewById(R.id.tv_HAVE_LOAD);
        tv_HAVE_UNLOAD= (TextView) findViewById(R.id.tv_HAVE_UNLOAD);
        tv_SUPPLY_VEHICLE_SIZE= (TextView) findViewById(R.id.tv_SUPPLY_VEHICLE_SIZE);
        tv_SUPPLY_VEHICLE_TYPE= (TextView) findViewById(R.id.tv_SUPPLY_VEHICLE_TYPE);
        tv_IS_RETURN_STORE= (TextView) findViewById(R.id.tv_IS_RETURN_STORE);
        tv_TOTAL_DROP= (TextView) findViewById(R.id.tv_TOTAL_DROP);
        tv_TOTAL_ROUTES= (TextView) findViewById(R.id.tv_TOTAL_ROUTES);
        tv_TOTAL_VOLUME= (TextView) findViewById(R.id.tv_TOTAL_VOLUME);
        tv_TOTAL_WEIGHT= (TextView) findViewById(R.id.tv_TOTAL_WEIGHT);
        tv_TOTAL_QTY= (TextView) findViewById(R.id.tv_TOTAL_QTY);
        tv_TASK_DESCRIPTION= (TextView) findViewById(R.id.tv_TASK_DESCRIPTION);
        tv_TASK_DESCRIPTION_OTHER= (TextView) findViewById(R.id.tv_TASK_DESCRIPTION_OTHER);
        bt_supply_accept= (Button) findViewById(R.id.bt_supply_accept);
        recyclerview_supplydetail_routes= (RecyclerView) findViewById(R.id.recyclerview_supplydetail_routes);
        routes=new ArrayList<>();
        routesadtapter=new RoutesAdapter(routes);
        LinearLayoutManager manager=new LinearLayoutManager(SupplyDetailActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerview_supplydetail_routes.setLayoutManager(manager);
        recyclerview_supplydetail_routes.addItemDecoration(new DividerListItemDecoration(getMContext(),DividerListItemDecoration.VERTICAL_LIST));
        recyclerview_supplydetail_routes.setAdapter(routesadtapter);

    }

    private void getData(){
        Map<String,String> params=new HashMap<>();
        params.put("IDX",supply_idx);
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.GetSupplyInfo,params,TAG_GetSupplyInfo);
    }

    private void setData() {
        routesadtapter.setRoutes(routes);
        tv_SUPPLY_NO.setText(supplyInfo.getSUPPLY_NO());
        tv_TMS_SHIPMENT_NO.setText(supplyInfo.getTMS_SHIPMENT_NO());
        tv_REQUEST_WAREHOUSE.setText(supplyInfo.getREQUEST_ISSUE());
        tv_REQUEST_ISSUE.setText(supplyInfo.getREQUEST_ISSUE());
        tv_DISTRIBUTION_EXPERIENCE.setText(supplyInfo.getDISTRIBUTION_EXPERIENCE());
        tv_ORG_NAME.setText(supplyInfo.getORG_NAME());
        tv_ROUTES_CITY.setText(supplyInfo.getROUTES_CITY());
        tv_SUPPLY_TYPE.setText(supplyInfo.getSUPPLY_TYPE());
        tv_TOTAL_AMOUNT.setText(supplyInfo.getTOTAL_AMOUNT());
        tv_IS_RETURN.setText(supplyInfo.getIS_RETURN());
        tv_HANDLING_DEGREE.setText(supplyInfo.getHANDLING_DEGREE());
        tv_IS_HANDLING.setText(supplyInfo.getIS_HANDLING());
        tv_HAVE_LOAD.setText(supplyInfo.getHAVE_LOAD());
        tv_HAVE_UNLOAD.setText(supplyInfo.getHAVE_UNLOAD());
        tv_SUPPLY_VEHICLE_SIZE.setText(supplyInfo.getSUPPLY_VEHICLE_SIZE());
        tv_SUPPLY_VEHICLE_TYPE.setText(supplyInfo.getSUPPLY_VEHICLE_TYPE());
        tv_IS_RETURN_STORE.setText(supplyInfo.getIS_RETURN_STORE());
        tv_TOTAL_DROP.setText(supplyInfo.getTOTAL_DROP());
        tv_TOTAL_ROUTES.setText(supplyInfo.getTOTAL_ROUTES());
        tv_TOTAL_VOLUME.setText(supplyInfo.getTOTAL_VOLUME());
        tv_TOTAL_WEIGHT.setText(supplyInfo.getTOTAL_WEIGHT());
        tv_TOTAL_QTY.setText(supplyInfo.getTOTAL_QTY());
        tv_TASK_DESCRIPTION.setText(supplyInfo.getTASK_DESCRIPTION());
        tv_TASK_DESCRIPTION_OTHER.setText(supplyInfo.getTASK_DESCRIPTION_OTHER());
        bt_supply_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String>params=new HashMap<>();
                params.put("UserId", SharedPreferencesUtils.getUserId());
                params.put("strLicense","");
                mClient.sendRequest(Constants.URL.GetFleetList,params,TAG_GetFleetList);
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

    /**
     * 创建选择车队列表 Dialog
     */
    private void chooseFleetDialog(final List<Fleet>fleets) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(SupplyDetailActivity.this);
            choosefleetDialog = builder.create();
            choosefleetDialog.setCanceledOnTouchOutside(false);
            choosefleetDialog.show();
            Window window = choosefleetDialog.getWindow();
            window.setContentView(R.layout.dialog_supplytypes_choice);
            lv_chooseFleet= (ListView) window.findViewById(R.id.lv_supplytypes_choice);
            fleetsAdapter=new SupplyFleetsAdapter(fleets);
            lv_chooseFleet.setAdapter(fleetsAdapter);
            lv_chooseFleet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    fleet=fleets.get(i);
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("UserId", SharedPreferencesUtils.getUserId());
                    params.put("FleetIdx",fleet.getIDX());
                    params.put("strLicense","");
                    mClient.sendRequest(Constants.URL.GetDriverList,params,TAG_GetDriverList);
                }
            });
            tv_Fleet= (TextView) window.findViewById(R.id.tv_types_text);
            tv_Fleet.setText("车队选择：");
            bt_cancel_chooseFleetDialog= (Button) window.findViewById(R.id.button_cancel);
            bt_cancel_chooseFleetDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        fleet=null;
                        choosefleetDialog.cancel();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建选择司机列表 Dialog
     */
    private void chooseDriverDialog(final List<Driver>drivers) {

            AlertDialog.Builder builder = new AlertDialog.Builder(SupplyDetailActivity.this);
            choosedriverDialog = builder.create();
            choosedriverDialog.setCanceledOnTouchOutside(false);
            choosedriverDialog.show();
            Window window = choosedriverDialog.getWindow();
            window.setContentView(R.layout.dialog_supplytypes_choice);
            lv_chooseDriver= (ListView) window.findViewById(R.id.lv_supplytypes_choice);
            driversAdapter=new SupplyDriversAdapter(drivers);
            lv_chooseDriver.setAdapter(driversAdapter);
            lv_chooseDriver.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    driver=drivers.get(i);
                    Map<String,String> params=new HashMap<String, String>();
                    params.put("UserId", SharedPreferencesUtils.getUserId());
                    params.put("FleetIdx",fleet.getIDX());
                    params.put("strLicense","");
                    mClient.sendRequest(Constants.URL.GetVehicleList,params,TAG_GetVehicleList);
                }
            });
            tv_Driver= (TextView) window.findViewById(R.id.tv_types_text);
            tv_Driver.setText("司机选择：");
            bt_cancel_chooseDriverDialog= (Button) window.findViewById(R.id.button_cancel);
            bt_cancel_chooseDriverDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    driver=null;
                    choosedriverDialog.cancel();
                }
            });

    }

    /**
     * 创建选择车辆列表 Dialog
     */
    private void chooseTruckDialog(final List<Truck>trucks) {

        AlertDialog.Builder builder = new AlertDialog.Builder(SupplyDetailActivity.this);
        choosetruckDialog = builder.create();
        choosetruckDialog.setCanceledOnTouchOutside(false);
        choosetruckDialog.show();
        Window window = choosetruckDialog.getWindow();
        window.setContentView(R.layout.dialog_supplytypes_choice);
        lv_choiceTruck= (ListView) window.findViewById(R.id.lv_supplytypes_choice);
        trucksAdapter=new SupplyTrucksAdapter(trucks);
        lv_choiceTruck.setAdapter(trucksAdapter);
        lv_choiceTruck.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                truck=trucks.get(i);
                if (fleet==null||driver==null||truck==null){
                    showToastMsg("车队、司机、车辆选择失败，请重新确认提交~");
                    choosetruckDialog.cancel();
                    return;
                }
                Map<String,String> params=new HashMap<String, String>();
                params.put("IDX", supplyInfo.getIDX());
                params.put("RECEIVING_IDX",MyApplication.getInstance().getUser().getIDX());
                params.put("USER_TEL", MyApplication.getInstance().getUser().getUSERCODE());
                params.put("FLEET_IDX",fleet.getIDX());
                params.put("FLEET_NAME",fleet.getFLEET_NAME());
                params.put("VEHICLE_IDX",truck.getIDX());
                params.put("PLATE_NUMBER",truck.getPLATE_NUMBER());
                params.put("VEHICLE_TYPE",truck.getVEHICLE_MODEL());
                params.put("VEHICLE_SIZE",truck.getVEHICLE_SIZE());
                params.put("BRAND_MODEL","");
                params.put("DRIVER_IDX",driver.getIDX());
                params.put("DRIVER_NAME",driver.getDRIVER_NAME());
                params.put("DRIVER_TEL",driver.getCONTACT_TEL());
                params.put("VERSION_NUMBER","");//暂时传空，20170311
                params.put("strLicense","");
               mClient.sendRequest(Constants.URL.ReceivingSupply,params,TAG_ReceivingSupply);
            }
        });
        tv_Truck= (TextView) window.findViewById(R.id.tv_types_text);
        tv_Truck.setText("车辆选择：");
        bt_cancel_choiceTruckDialog= (Button) window.findViewById(R.id.button_cancel);
        bt_cancel_choiceTruckDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driver=null;
                choosetruckDialog.cancel();
            }
        });

    }



    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            switch (request_tag){
                case TAG_GetSupplyInfo:showToastMsg("网络加载失败，请返回重进");
                    break;
                case TAG_GetFleetList:showToastMsg("网络加载失败，请重新确认接单");
                    break;
                case TAG_GetDriverList:showToastMsg("网络加载失败，请重选车队");
                    break;
                case TAG_GetVehicleList:showToastMsg("网络加载失败，请重选司机");
                    break;
                case TAG_ReceivingSupply:showToastMsg("网络上传失败，请重新选择接单");
                    choosetruckDialog.cancel();
                    break;
                default:
                    break;
            }
            return;
        }else if (request_tag.equals(TAG_GetSupplyInfo)){
            try {
                JSONObject jos= JSON.parseObject(msg);
                JSONObject jo=JSON.parseObject(jos.getString("result"));
                routes=JSON.parseArray(jo.getString("Routes"),Routes.class);
                supplyInfo=JSON.parseObject(jo.getString("Supply"),SupplyInfo.class);
                setData();

            }catch (JSONException ex){
                ex.printStackTrace();
            }
            mClient.cancleRequest(TAG_GetSupplyInfo);
        }else if (request_tag.equals(TAG_GetFleetList)){
           try{
               JSONObject jos= JSON.parseObject(msg);
               JSONObject jo=jos.getJSONObject("result");
               List<Fleet> fleets=JSON.parseArray(jo.getString("Fleet"),Fleet.class);
               chooseFleetDialog(fleets);
           }catch (JSONException ex){
               ex.printStackTrace();

           }
            mClient.cancleRequest(TAG_GetFleetList);
        }else if (request_tag.equals(TAG_GetDriverList)){
           try {
               choosefleetDialog.cancel();
               JSONObject jos= JSON.parseObject(msg);
               JSONObject jo=jos.getJSONObject("result");
               List<Driver>drivers=JSON.parseArray(jo.getString("Driver"),Driver.class);
               chooseDriverDialog(drivers);
           }catch (JSONException ex){
               ex.printStackTrace();
           }
            mClient.cancleRequest(TAG_GetDriverList);
        }else if (request_tag.equals(TAG_GetVehicleList)){
            try {
                choosedriverDialog.cancel();
                JSONObject jos= JSON.parseObject(msg);
                JSONObject jo=jos.getJSONObject("result");
                List<Truck> trucks=JSON.parseArray(jo.getString("Vehicle"),Truck.class);
                chooseTruckDialog(trucks);
            }catch (JSONException ex){
                ex.printStackTrace();
            }
            mClient.cancleRequest(TAG_GetVehicleList);
        }else if (request_tag.equals(TAG_ReceivingSupply)){
            try {
                JSONObject jo=JSON.parseObject(msg);
                int type=jo.getInteger("type");
                if (type==1){
                    showToastMsg("成功推入已接货源，等待货主确认!");
                }else {
                    showToastMsg(jo.getString("msg"));
                }
                GoodsFragment.isrefresh=true;
                SupplyDetailActivity.this.finish();
            }catch (JSONException ex){
                ex.printStackTrace();
            }
            mClient.cancleRequest(TAG_ReceivingSupply);
        }
    }



}
