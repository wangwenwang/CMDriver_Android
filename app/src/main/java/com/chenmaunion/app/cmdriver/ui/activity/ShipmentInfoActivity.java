package com.chenmaunion.app.cmdriver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.SmInfoOrderListAdapter;
import com.chenmaunion.app.cmdriver.bean.order.Shipment;
import com.chenmaunion.app.cmdriver.bean.order.SmInfoOrder;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.chenmaunion.app.cmdriver.utils.baiduMapUtils.DataUtil;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.widget.MLog;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/3.
 */
public class ShipmentInfoActivity extends BaseActivity implements AsyncHttpCallback {
    private DriverAsyncHttpClient mClient;
    private TextView tv_SHIPMENT_NO,tv_TMS_SHIPMENT_NO,tv_DATE_LOAD,tv_DATE_ISSUE,tv_FLEET_NAME,tv_ORG_NAME,
            tv_SHIPMENT_STATE,tv_PLATE_NUMBER,tv_DRIVER_NAME,tv_DRIVER_TEL,tv_VEHICLE_SIZE,tv_VEHICLE_TYPE,tv_TOTAL_WEIGHT,
            tv_TOTAL_VOLUME,tv_FROM_CITY,tv_TO_CITY;
    private RecyclerView mrecyclerView;
    private String IDX;
    private final String TAG_GetMessageDetils="GetMessageDetils";
    private final String TAG_GetShipmentInfo="GetShipmentInfo";
    private SlidingTitleView titleView;
    private List<SmInfoOrder> smInfoOrders;
    private Shipment shipment;
    private SmInfoOrderListAdapter adapter;
    public static boolean isrefresh=false;
    //20170623 加入每次进入时百度定位一次的功能

    private GeoCoder msearch;
    private OnGetGeoCoderResultListener geoCoderlistener;
    private GeoCodeResult order_ToAdsGeoCodeResult;
    private BDLocation currentlocation;
    public LocationClient mLocationClient;// 百度定位客户端
    public MyLocationListener myListener;// 百度定位监听
    private String tempcoor = "bd09ll";// 百度地图的编码模式
    private RequestQueue mRequestQueue;
    private final static String Tag_Upload_Position = "Tag_Upload_Position";
    //高精度模式
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    private boolean againBoolean=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipmentinfo);
        Intent intent=getIntent();
        if (intent.hasExtra("SHIPMENT_IDX")){
            IDX=intent.getStringExtra("SHIPMENT_IDX");
        }else {finish();}
        mClient=new DriverAsyncHttpClient(this,this);
        if (intent.hasExtra("notify_id")){
            setisReadMessage(intent.getStringExtra("notify_id"));
        }
        initView();
        getdata();
    }

    private void getdata() {
        Map<String,String> params=new HashMap<>();
        params.put("IDX",IDX);
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.GetShipmentInfo,params,TAG_GetShipmentInfo);
    }

    /**
     * 显示界面数据
     */
    private void setdata() {
        tv_SHIPMENT_NO.setText(shipment.getSHIPMENT_NO());
        tv_TMS_SHIPMENT_NO.setText(shipment.getTMS_SHIPMENT_NO());
        tv_DATE_LOAD.setText(shipment.getDATE_LOAD());
        tv_DATE_ISSUE.setText(shipment.getDATE_ISSUE());
        tv_FLEET_NAME.setText(shipment.getFLEET_NAME());
        tv_ORG_NAME.setText(shipment.getORG_NAME());
        tv_SHIPMENT_STATE.setText(toSHIPMENT_STATE(shipment.getSHIPMENT_STATE()));
        tv_PLATE_NUMBER.setText(shipment.getPLATE_NUMBER());
        tv_DRIVER_NAME.setText(shipment.getDRIVER_NAME());
        tv_DRIVER_TEL.setText(shipment.getDRIVER_TEL());
        tv_VEHICLE_SIZE.setText(shipment.getVEHICLE_SIZE());
        tv_VEHICLE_TYPE.setText(shipment.getVEHICLE_TYPE());
        tv_TOTAL_WEIGHT.setText(shipment.getTOTAL_WEIGHT()+"吨");
        tv_TOTAL_VOLUME.setText(shipment.getTOTAL_VOLUME()+"m³");
        tv_FROM_CITY.setText(shipment.getFROM_CITY());
        tv_TO_CITY.setText(shipment.getTO_CITY());
        adapter.setSmInfoOrders(smInfoOrders);
        startLocate();
    }
    private void initView() {
        titleView= (SlidingTitleView) findViewById(R.id.title_shipmentinfo_activtiy);
        titleView.setText("装运信息");
        titleView.setMode(SlidingTitleView.MODE_BACK);
        tv_SHIPMENT_NO= (TextView) findViewById(R.id.tv_SHIPMENT_NO);
        tv_TMS_SHIPMENT_NO= (TextView) findViewById(R.id.tv_TMS_SHIPMENT_NO);
        tv_DATE_LOAD= (TextView) findViewById(R.id.tv_DATE_LOAD);
        tv_DATE_ISSUE= (TextView) findViewById(R.id.tv_DATE_ISSUE);
        tv_FLEET_NAME=(TextView)findViewById(R.id.tv_FLEET_NAME);
        tv_ORG_NAME= (TextView) findViewById(R.id.tv_ORG_NAME);
        tv_SHIPMENT_STATE= (TextView) findViewById(R.id.tv_SHIPMENT_STATE);
        tv_PLATE_NUMBER= (TextView) findViewById(R.id.tv_PLATE_NUMBER);
        tv_DRIVER_NAME= (TextView) findViewById(R.id.tv_DRIVER_NAME);
        tv_DRIVER_TEL= (TextView) findViewById(R.id.tv_DRIVER_TEL);
        tv_VEHICLE_SIZE= (TextView) findViewById(R.id.tv_VEHICLE_SIZE);
        tv_VEHICLE_TYPE= (TextView) findViewById(R.id.tv_VEHICLE_TYPE);
        tv_TOTAL_WEIGHT= (TextView) findViewById(R.id.tv_TOTAL_WEIGHT);
        tv_TOTAL_VOLUME= (TextView) findViewById(R.id.tv_TOTAL_VOLUME);
        tv_FROM_CITY= (TextView) findViewById(R.id.tv_FROM_CITY);
        tv_TO_CITY= (TextView) findViewById(R.id.tv_TO_CITY);
        mrecyclerView= (RecyclerView) findViewById(R.id.recyclerview_orderlist);
        LinearLayoutManager manager=new LinearLayoutManager(ShipmentInfoActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerView.setLayoutManager(manager);
        smInfoOrders=new ArrayList<>();
        adapter=new SmInfoOrderListAdapter(ShipmentInfoActivity.this,smInfoOrders);
        mrecyclerView.setAdapter(adapter);
        mLocationClient=new LocationClient(this);
        myListener=new MyLocationListener();
      //  mLocationClient.registerLocationListener(myListener);
        initLocationClient();
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
     * 仅用于访问服务器，更改消息的读取状态ISREAD
     * @param notify_id
     */
    private void setisReadMessage(String notify_id) {
        Map<String,String> params=new HashMap<>();
        params.put("Id",notify_id);
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.GetMessageDetils,params,TAG_GetMessageDetils,false);
    }

    @Override
    public void onResume() {
        if (isrefresh){
            getdata();
            isrefresh=false;
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mLocationClient!=null){
            mLocationClient.stop();
            mLocationClient=null;
        }
        super.onDestroy();
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            switch (request_tag){
                case TAG_GetMessageDetils:
                    break;
                case TAG_GetShipmentInfo:
                    showToastMsg("网络加载失败，请返回重新加载");
                    break;
                default:
                    break;
            }
        }else if (request_tag.equals(TAG_GetShipmentInfo)){
            JSONObject jos= JSON.parseObject(msg);
            JSONObject jo=JSON.parseObject(jos.getString("result"));
            shipment = JSON.parseObject(jo.getString("Shipment"),Shipment.class);
            smInfoOrders=JSON.parseArray(jo.getString("Order"),SmInfoOrder.class);
            setdata();
        }
    }

    public void startLocate() {

        if (mLocationClient!=null){
            mLocationClient.start();
        }else {
            mLocationClient=new LocationClient(this);
            myListener=new MyLocationListener();
          //  mLocationClient.registerLocationListener(myListener);
            initLocationClient();
            mLocationClient.start();
        }
    }

    /**
     * 初始化定位客户端
     */
    public void initLocationClient() {
        LocationClientOption option = new LocationClientOption();
        option.setProdName(this.getPackageName());
        MLog.w("ProdName:" + this.getPackageName());
        option.setCoorType(tempcoor);// 返回的定位结果是百度经纬度，默认值gcj02
        option.setLocationMode(tempMode);// 设置定位模式
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setIgnoreKillProcess(false);
        option.setTimeOut(10 * 1000); // 网络定位的超时时间
        mLocationClient.setLocOption(option);
    }


    /**
     * 判断是否会定位失败 ，errorCode是百度定位返回的错误代码
     *
     * @param errorCode
     * @return
     */
    public boolean isLocateAvailable(int errorCode) {
        return (errorCode == 161 ||errorCode == 61||errorCode==66||errorCode==65||errorCode==68);
        //2016.08.30 陈翔 注销了65 ： 定位缓存的结果；68 ： 网络连接失败时，查找本地离线定位时对应的返回结果。
        //return (errorCode == 161 ||errorCode == 61||errorCode==66);
    }
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            MLog.i("MyLocationListener:\t"+location.getLocType());
            if (location == null) {
                //定位返回空值时，重新定位
                if (againBoolean){
                    try {
                        Thread.sleep(30*1000);
                        againBoolean=false;
                        int r=mLocationClient.requestLocation();
                        MLog.w("定位返回空值时，重新定位:" + r);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }

            // 判断定位是否失败应该依据error code的值更加可靠, 上传坐标信息
            if (!isLocateAvailable(location.getLocType())) {
                //定位返回错误码时，重新定位
                MLog.w("定位返回错误码"+againBoolean);
                if (againBoolean){
                    try {
                        Thread.sleep(30*1000);
                        againBoolean=false;
                        int j=mLocationClient.requestLocation();
                        MLog.w("定位返回错误码时，重新定位:" + j);
                        return;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                showToast(location.getLocType()+"网络状态不良，请重试", Toast.LENGTH_SHORT);
                return;
            }
            currentlocation=location;
            mLocationClient.stop();
            return;
        }
    }

}
