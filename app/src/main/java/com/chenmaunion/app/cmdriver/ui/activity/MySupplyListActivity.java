package com.chenmaunion.app.cmdriver.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.DBShipMentListAdapter;
import com.chenmaunion.app.cmdriver.adapter.SupplyListAdapter;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.bean.Goods.Supply;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.utils.nomalutils.DateUtil;
import com.kaidongyuan.app.basemodule.utils.nomalutils.StringUtils;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/6/21.
 */
public class MySupplyListActivity extends BaseActivity implements AsyncHttpCallback {
    private ArrayList<String> selectedcitys,selectedcompanys,selectedtrucktypes,selectedtrucksizes;
    private final int pageCount=10;
    private List<Supply> supplies;
    private SlidingTitleView titleView;
    private final String TAG_GetSupplyList="GetSupplyList";
    private SimpleDateFormat df;
    private Date startDate,endDate;
    private int page = 1;// 用于存储当前数据的页数
    private DriverAsyncHttpClient mClient;
    private SwipeRefreshLayout mswipeRefreshLayout;
    private RecyclerView mrecyclerview;
    private SupplyListAdapter msupplyListAdapter;
    private LinearLayout ll_no_record;
    private LinearLayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysupplylist);
        mClient=new DriverAsyncHttpClient(this,this);
        selectedtrucksizes=new ArrayList<>();
        selectedtrucktypes=new ArrayList<>();
        selectedcompanys=new ArrayList<>();
        selectedcitys=new ArrayList<>();
        initView();
        initdata();
    }

    private void initdata() {
        page=1;
        getGoods(page);
    }

    private void getGoods(int page) {
        if (page==1&&supplies!=null&&supplies.size()>0){
            supplies=new ArrayList<>();
            msupplyListAdapter.loadState= DBShipMentListAdapter.LOADING_MORE;
        }
        Map<String,String> params=new HashMap<>();
        params.put("USER_IDX","");
        params.put("DRIVER_IDX", MyApplication.getInstance().getUser().getIDX());
        params.put("strJson",getstrJson(selectedcitys,selectedcompanys,selectedtrucktypes,selectedtrucksizes));
//        params.put("ROUTES_CITY", (String) tabLayout.getTabAt(0).getTag());
//        params.put("ORG_IDX", (String) tabLayout.getTabAt(1).getTag());
//        params.put("SUPPLY_VEHICLE_SIZE", (String) tabLayout.getTabAt(2).getTag());
//        params.put("SUPPLY_VEHICLE_TYPE", (String) tabLayout.getTabAt(3).getTag());
        endDate= DateUtil.getDateTime(System.currentTimeMillis()+1*24*60*60*1000L);
        startDate=DateUtil.getDateTime(System.currentTimeMillis()-365*24*60*60*1000L);
        params.put("START_DATE",df.format(startDate));
        params.put("END_DATE",df.format(endDate));
        params.put("SUPPLY_STATE","OPEN");
        params.put("SUPPLY_WOKERFLOW","");
        params.put("PAGE",page+"");
        params.put("PAGE_COUNT",pageCount+"");
        params.put("strLicense", "");
        mClient.setShowToast(false);
        mClient.sendRequest(Constants.URL.GetSupplyList,params,TAG_GetSupplyList);
    }

    private void initView() {
        titleView= (SlidingTitleView) this.findViewById(R.id.slidingtilte_acitivity_mysupplylist);
        titleView.setMode(SlidingTitleView.MODE_BACK);
        titleView.setText("已接货源列表");
        supplies=new ArrayList<>();
        df = new SimpleDateFormat("yyyy-MM-dd");
        mrecyclerview= (RecyclerView) this.findViewById(R.id.recyclerview_acitivity_mysupplylist);
        manager = new LinearLayoutManager(getMContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerview.setLayoutManager(manager);
        msupplyListAdapter=new SupplyListAdapter(supplies,this);
        mrecyclerview.setAdapter(msupplyListAdapter);
        mrecyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&(manager.findLastVisibleItemPosition()+1)==msupplyListAdapter.getItemCount()){
                    if (msupplyListAdapter.loadState!= DBShipMentListAdapter.NO_MORE){
                        msupplyListAdapter.loadState=DBShipMentListAdapter.LOADING_MORE;
                        getGoods(page);
                    }
                }
            }
        });
        mswipeRefreshLayout= (SwipeRefreshLayout) this.findViewById(R.id.swiperefresh_acitivity_mysupplylist);
        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                getGoods(page);
            }
        });
        mswipeRefreshLayout.setVisibility(View.VISIBLE);
        ll_no_record= (LinearLayout) this.findViewById(R.id.ll_no_record);
        ll_no_record.setVisibility(View.GONE);
    }

    private String getstrJson(List<String> citylist, List<String> companylist, List<String> trucktypelist, List<String> trucksizelist) {
        JSONArray arr = new JSONArray();
        JSONObject obj = new JSONObject();
        String  ORG_IDX="",SUPPLY_VEHICLE_TYPE="",SUPPLY_VEHICLE_SIZE="",ROUTES_CITY="";
        for (int i=0;i<citylist.size();i++){
            ROUTES_CITY=ROUTES_CITY+citylist.get(i)+",";
        }
        for (int j=0;j<companylist.size();j++){
            if (getString(R.string.person_goods).equals(companylist.get(j))){
                ORG_IDX=ORG_IDX+"null"+",";
                continue;//仅结束本次循环，但不影响循环继续运行
            }else if (getString(R.string.other).equals(companylist.get(j))){
                ORG_IDX=ORG_IDX+"dislike"+",";
            }
            ORG_IDX=ORG_IDX+companylist.get(j)+",";
        }
        for (int k=0;k<trucktypelist.size();k++){
            SUPPLY_VEHICLE_TYPE=SUPPLY_VEHICLE_TYPE+trucktypelist.get(k)+",";
        }
        for (int l=0;l<trucksizelist.size();l++){
            SUPPLY_VEHICLE_SIZE=SUPPLY_VEHICLE_SIZE+trucksizelist.get(l)+",";
        }
        ORG_IDX= StringUtils.trimLastChar(ORG_IDX);
        SUPPLY_VEHICLE_TYPE=StringUtils.trimLastChar(SUPPLY_VEHICLE_TYPE);
        SUPPLY_VEHICLE_SIZE=StringUtils.trimLastChar(SUPPLY_VEHICLE_SIZE);
        ROUTES_CITY=StringUtils.trimLastChar(ROUTES_CITY);
        obj.put("ORG_IDX", ORG_IDX);
        obj.put("SUPPLY_VEHICLE_TYPE", SUPPLY_VEHICLE_TYPE);
        obj.put("SUPPLY_VEHICLE_SIZE", SUPPLY_VEHICLE_SIZE);
        obj.put("ROUTES_CITY", ROUTES_CITY);
        arr.add(0, obj);
        JSONObject objs = new JSONObject();
        objs.put("result", arr);
        return objs.toString();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        mswipeRefreshLayout.setRefreshing(false);
        if (msg.equals("error")){
            switch (request_tag){
                case TAG_GetSupplyList:
                    mClient.cancleRequest(TAG_GetSupplyList);
                    showToastMsg("没有请求到货源数据");
                    if (page==1){
                        if (supplies==null||supplies.size()<=0){
                            mswipeRefreshLayout.setVisibility(View.GONE);
                            ll_no_record.setVisibility(View.VISIBLE);
                            ll_no_record.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    getGoods(page);
                                }
                            });
                        }else {
                            mswipeRefreshLayout.setVisibility(View.VISIBLE);
                            ll_no_record.setVisibility(View.GONE);
                        }
                    }else {
                        msupplyListAdapter.loadState= DBShipMentListAdapter.NO_MORE;
                    }
                    return;
                default:
                    return;
            }

        }else if (request_tag.equals(TAG_GetSupplyList)){
            mClient.cancleRequest(TAG_GetSupplyList);
            JSONObject jos= JSON.parseObject(msg);
            JSONObject jo=JSON.parseObject(jos.getString("result"));
            List<Supply> tmsSupplies =JSON.parseArray(jo.getString("Supply"),Supply.class);
            if (supplies!=null){
                for (int i=0;i<tmsSupplies.size();i++){
                    if (!supplies.contains(tmsSupplies.get(i))){
                        supplies.add(tmsSupplies.get(i));
                    }
                }

                if (tmsSupplies.size()<pageCount){
                    msupplyListAdapter.loadState= DBShipMentListAdapter.NO_MORE;
                }
                page++;

                if (mswipeRefreshLayout.getVisibility()==View.GONE){
                    mswipeRefreshLayout.setVisibility(View.VISIBLE);
                    ll_no_record.setVisibility(View.GONE);
                }
                msupplyListAdapter.setData(supplies);
            }
        }
    }
}
