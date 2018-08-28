package com.chenmaunion.app.cmdriver.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.order.Location;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.utils.baiduMapUtils.DrivingRouteOverlay;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.widget.MLog;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/15.
 */
public class OrderTrackActivity extends BaseActivity implements AsyncHttpCallback {
    private SlidingTitleView titleView;
    private MapView mMapView;
    private DriverAsyncHttpClient mClient;
    BaiduMap mBaiduMap = null;
    public double distance=0;
    public  double distance0=0;
    private int agains=10;//可以重新请求规划路段的次数
    private RoutePlanSearch mSearch;
    private final String TAG_GetOrderLocaltion="GetOrderLocaltion";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordertrack);
        mMapView= (MapView) findViewById(R.id.mapView_orderTrack);
        mBaiduMap=mMapView.getMap();
        mClient=new DriverAsyncHttpClient(this,this);
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
        initview();
        getPath();
    }

    @Override
    public void initWindow() {
        //重写为空，针对满屏页面取消沉浸式状态栏
    }

    private void initview() {
        titleView= (SlidingTitleView) findViewById(R.id.slidingtitelView_OrderTrackActivity);
        titleView.setMode(SlidingTitleView.MODE_BACK);
        titleView.setText("正规划路线");
    }

    private void getPath() {
        Intent intent = getIntent();
        String orderId = intent.getStringExtra("order_id");
        if (orderId == null || orderId.equals("")) {
            showToastMsg("发货订单号有误，请返回重新加载");
            finish();
            return;
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("OrderId", orderId);
        params.put("strLicense", "");
//        params.put("roleNames", tv_select_role.getText()+"");
        mClient.setShowToast(false);
        mClient.sendRequest(Constants.URL.GetOrderLocaltion, params, TAG_GetOrderLocaltion);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMapView!=null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapView!=null){
            mMapView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMapView!=null){
            mMapView.onDestroy();
        }
    }

    private double getDistance(List<Location> locationlist) {
        double distance=0;
        int size=locationlist.size()-1;
        for (int i=1;i<size;i++){
            Location nowLocation=locationlist.get(i);
            Location perviousLocation = locationlist.get(i-1);
            LatLng nowLatLng = new LatLng(nowLocation.CORDINATEY, nowLocation.CORDINATEX);
            LatLng perviousLatLng = new LatLng(perviousLocation.CORDINATEY, perviousLocation.CORDINATEX);
            distance += DistanceUtil.getDistance(nowLatLng , perviousLatLng);
        }
        return distance;
    }

    /**
     *  根据Location坐标集合来规划线路
     * @param locationListfd Location坐标集合
     * @param i  是否为最后一段需要规划的路线，是：1/否：0
     */
    private void searchInMap(final List<Location> locationListfd, int i) {
        final int again=i;
        agains=agains-1;
        MLog.e("进入搜索线路方法");
        if (mBaiduMap==null)return;
        LatLng stlatlng=new LatLng(locationListfd.get(0).CORDINATEY,locationListfd.get(0).CORDINATEX);
        LatLng endlatlng=new LatLng(locationListfd.get(locationListfd.size()-1).CORDINATEY,locationListfd.get(locationListfd.size()-1).CORDINATEX);
        mSearch=RoutePlanSearch.newInstance();
        PlanNode stNode=PlanNode.withLocation(stlatlng);
        PlanNode endNode=PlanNode.withLocation(endlatlng);
        List<PlanNode>passBy=new ArrayList<>();
        for (int j=1;j<locationListfd.size()-2;j++){
            passBy.add(PlanNode.withLocation(new LatLng(locationListfd.get(j).CORDINATEY,locationListfd.get(j).CORDINATEX)));
        }
        DrivingRoutePlanOption drivingRoutePlanOption=new DrivingRoutePlanOption().from(stNode).passBy(passBy).to(endNode).policy(DrivingRoutePlanOption.DrivingPolicy.ECAR_DIS_FIRST);
        OnGetRoutePlanResultListener listener=new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                //获取步行线路规划结果
            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
                //获取公交换乘路径规划结果
            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
                //获取驾车线路规划结果
                MLog.e("驾车线路规划结果:onGetDrivingRouteResult");
                if (drivingRouteResult==null||drivingRouteResult.error!= SearchResult.ERRORNO.NO_ERROR){
                    MLog.e("error:" + "抱歉，未找到结果");
                    if (!OrderTrackActivity.this.isFinishing()&&agains>0){
                        searchInMap(locationListfd,again);
                        titleView.setText("查询路线中");
                    }
                    if (again==1){
                        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    }
                    return;
                }
                if (drivingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                    MLog.e("error:" + drivingRouteResult.getSuggestAddrInfo().toString());
                    if (again==1){
                        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    }
                    return;
                }  else if (drivingRouteResult.error==SearchResult.ERRORNO.NO_ERROR){
                    MLog.d("onGetDrivingRouteResult no error");
                    DrivingRouteOverlay overlay=new MyDrivingRouteOverlay(mBaiduMap);
                    distance+=drivingRouteResult.getRouteLines().get(0).getDistance();
                    titleView.setText(distance0>distance?titleView.getText():("公里数："+distance/1000+"公里"));
                    MLog.e("驾车规划出来的数据："+distance/1000 + "公里");
                    overlay.setData(drivingRouteResult.getRouteLines().get(0));
                    overlay.addToMap();
                    if (again==1){
                        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    }
                    overlay.zoomToSpan();
                    mSearch.destroy();
                    return;
                }
                if (again==1){
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                }
            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
                //获取自行车线路规划结果
            }
        };
        mSearch.setOnGetRoutePlanResultListener(listener);
        //移动节点至起点
        MLog.e(" 移动节点至起点" + "总计点数" + locationListfd.size());
        titleView.setText(mSearch.drivingSearch(drivingRoutePlanOption)? "正绘制路线":"路线有误，请重新查看");
    }
    //定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {
        private  boolean useDefaultST=false;
        private  boolean useDefaultEN=false;
        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }
        public  MyDrivingRouteOverlay(BaiduMap baiduMap,boolean stIcon,boolean enIcon){
            super(baiduMap);
            useDefaultST=stIcon;
            useDefaultEN=enIcon;
        }

        @Override
        public BitmapDescriptor getStartMarker() {
//            if (useDefaultST){
//                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
//            }
            return  BitmapDescriptorFactory.fromResource(R.drawable.chose_cardwhite);
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
//            if (useDefaultEN) {
//                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
//            }
            return  BitmapDescriptorFactory.fromResource(R.drawable.chose_cardwhite);
        }

        @Override
        public List<BitmapDescriptor> getCustomTextureList() {
            return super.getCustomTextureList();
        }
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")) {
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            showToastMsg("网络加载失败，请退出重进~");
            return;
        }else {
            JSONObject jo= JSON.parseObject(msg);
            List<Location> locationlist=JSON.parseArray(jo.getString("result"),Location.class);
            if (locationlist==null||locationlist.size()<0){
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                return;
            }
            //绘制路线
            if(locationlist.size()%100>0){
                for (int i=0;i<locationlist.size()/100;i++){
                    List<Location>locationListfd=locationlist.subList(i*100,i*100+101);
                    searchInMap(locationListfd,0);
                }
                List<Location>locationListmr=locationlist.subList(locationlist.size() - locationlist.size() % 100, locationlist.size());
                searchInMap(locationListmr,1);
            }else {
                //当定位点集合元素为100的整数倍时
                for (int i=0;i<(locationlist.size()/100)-1;i++){
                    List<Location>locationListfd=locationlist.subList(i*100,i*100+101);
                    searchInMap(locationListfd,0);
                }
                List<Location>locationListfy=locationlist.subList(locationlist.size()-100,locationlist.size());
                searchInMap(locationListfy,1);
            }
            //计算路程，并显示到界面上
            distance0 = getDistance(locationlist);
            MLog.e("直接加两点距离出来的数据：" + distance0);
            //绘制起点和终点的图标
            Location startLocation = locationlist.get(0);
            Location endLocation = locationlist.get(locationlist.size()-1);
            LatLng stLatLng = new LatLng(startLocation.CORDINATEY, startLocation.CORDINATEX);
            LatLng enLatLng = new LatLng(endLocation.CORDINATEY, endLocation.CORDINATEX);
            BitmapDescriptor stbitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            BitmapDescriptor enbitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_car);
            MarkerOptions stOption = new MarkerOptions().position(stLatLng).icon(stbitmap).zIndex(5);
            MarkerOptions enOption = new MarkerOptions().position(enLatLng).icon(enbitmap).zIndex(5);
            mBaiduMap.addOverlay(stOption);
            mBaiduMap.addOverlay(enOption);
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(stLatLng));
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(16).build()));
        }
    }
}
