package com.chenmaunion.app.cmdriver.ui.fragment;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.R;

import com.chenmaunion.app.cmdriver.adapter.DBShipMentListAdapter;
import com.chenmaunion.app.cmdriver.bean.Goods.Supply;
import com.chenmaunion.app.cmdriver.bean.order.Order;
import com.chenmaunion.app.cmdriver.bean.order.Shipment;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.interfaces.OrderFragmentListener;
import com.chenmaunion.app.cmdriver.ui.widget.DividerListItemDecoration;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_Order;
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_ShipMent;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.ui.fragment.BaseLifecyclePrintFragment;
import com.kaidongyuan.app.basemodule.utils.nomalutils.DateUtil;
import com.kaidongyuan.app.basemodule.widget.MLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/11.
 */
public class OrderListFragment extends BaseLifecyclePrintFragment implements OrderFragmentListener,AsyncHttpCallback {
    private  String TAG_OrderType,truckTeam;
    private View parent;
    private Context mContext;
    private SwipeRefreshLayout mswipeRefreshLayout;
    private RecyclerView mrecyclerview;
    private ObservableArrayList<DBModel_ShipMent> shipMents;
    private DBShipMentListAdapter adapter;
    private int page = 1;// 用于存储当前数据的页数

    private SimpleDateFormat df;
    private Date startDate,endDate;
   private final int pageSize =10;// 每页的数据条数
    private DriverAsyncHttpClient client;
    private Map<String,String> params;
    private LinearLayout ll_no_record;
    public static boolean isrefresh=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        parent=inflater.inflate(R.layout.fragment_orderlist,container,false);
        Bundle bundle=getArguments();
        if (bundle!=null){
            TAG_OrderType=bundle.getString("TAG_OrderType");
            truckTeam=bundle.getString("TruckTeam");
        }else {
            showToastMsg("订单分类失败，默认加载全部订单");
            TAG_OrderType="";
            truckTeam="";
        }
        page=1;
        initView();
        loadShipMents(page);
        return parent;
    }

    private void loadShipMents(int page) {
        if (page==1&&shipMents!=null&&shipMents.size()>0){
            shipMents=new ObservableArrayList<>();
            adapter.loadState= DBShipMentListAdapter.LOADING_MORE;
        }
        params=new HashMap<String, String>();
        params.put("UserIdx", SharedPreferencesUtils.getUserId());//20170612 用于司机版查看装运情况
        params.put("AppUserIdx","");//20170612 用于客户版查看客户自己发布的装运
        params.put("SHIPMENT_STATE", TAG_OrderType);//分类装运状态
       // params.put("strTruckTeam",truckTeam);
        params.put("PAGE", page + "");
        params.put("PAGE_COUNT",pageSize+"");
//        params.put("strPageCount", pageSize +"");
        endDate= DateUtil.getDateTime(System.currentTimeMillis()+1*24*60*60*1000L);
        startDate=DateUtil.getDateTime(System.currentTimeMillis()-365*24*60*60*1000L);
        params.put("START_DATE",df.format(startDate));
        params.put("END_DATE",df.format(endDate));
        params.put("strLicense", "");
        client.setShowToast(false);
        client.sendRequest(Constants.URL.GetShipmentList,params,TAG_OrderType);
    }

    private void initView() {
        mContext=getMContext();
        mswipeRefreshLayout= (SwipeRefreshLayout) parent.findViewById(R.id.swiperefresh_orderlistfragment);
        mswipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_orange_light, android.R.color.holo_red_light, android.R.color.holo_green_light);
        mrecyclerview= (RecyclerView) parent.findViewById(R.id.recyclerview_orderlistfragment);
        shipMents=new ObservableArrayList<>();
        adapter=new DBShipMentListAdapter(shipMents);
        final LinearLayoutManager manager=new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerview.setLayoutManager(manager);
        mrecyclerview.addItemDecoration(new DividerListItemDecoration(mContext,DividerListItemDecoration.VERTICAL_LIST));
        client=new DriverAsyncHttpClient(this,this);
        ll_no_record= (LinearLayout) parent.findViewById(R.id.ll_no_record);
        mrecyclerview.setAdapter(adapter);
        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                loadShipMents(page);
            }
        });
        mrecyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&(manager.findLastVisibleItemPosition()+1)==adapter.getItemCount()){
                    if (adapter.loadState!=DBShipMentListAdapter.NO_MORE){
                        adapter.loadState=DBShipMentListAdapter.LOADING_MORE;
                        loadShipMents(page);
                    }
                }
            }
        });
        df = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public void onResume() {
        //如果有订单提交或车队类型改变则通过改变isrefresh值为true来刷新orderlist数据
        if (isrefresh==true){
            page=1;
            loadShipMents(page);
            isrefresh=false;
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 按车队类型来查找的接口
     * @param truckTeam
     */
    @Override
    public void OnTruckteamSelect(String truckTeam) {
        this.truckTeam=truckTeam;
        if (OrderListFragment.this.isResumed()){
            page=1;
            loadShipMents(page);
        }else {
            isrefresh=true;
        }
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        mswipeRefreshLayout.setRefreshing(false);
        if (msg.equals("error")){
            MLog.e("postSuccessMsg error");
            client.cancleRequest(TAG_OrderType);
            if (page==1){
                if (shipMents==null||shipMents.size()<=0){
                    mswipeRefreshLayout.setVisibility(View.GONE);
                    ll_no_record.setVisibility(View.VISIBLE);
                    ll_no_record.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            loadShipMents(page);
                        }
                    });
                }else {
                    mswipeRefreshLayout.setVisibility(View.VISIBLE);
                    ll_no_record.setVisibility(View.GONE);
                }
            }
        }
        if (request_tag.equals(TAG_OrderType)){
            try {
                JSONObject jos= JSON.parseObject(msg);
                JSONObject jo=JSON.parseObject(jos.getString("result"));
                List<Shipment> tmsorderlist=JSON.parseArray(jo.getString("Shipment"),Shipment.class);
                if (shipMents!=null){
                    for (int i=0;i<tmsorderlist.size();i++){
                        DBModel_ShipMent shipMent=new DBModel_ShipMent();
                        shipMent.shipment2DBModelShipMent(tmsorderlist.get(i));
                        shipMents.add(shipMent);
                    }
                    if (tmsorderlist.size()<pageSize){
                        adapter.loadState=DBShipMentListAdapter.NO_MORE;
                    }
                    page++;

                    if (mswipeRefreshLayout.getVisibility()==View.GONE){
                        mswipeRefreshLayout.setVisibility(View.VISIBLE);
                        ll_no_record.setVisibility(View.GONE);
                    }
                    adapter.setData(shipMents);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}
