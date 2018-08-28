package com.chenmaunion.app.cmdriver.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.DBShipMentListAdapter;
import com.chenmaunion.app.cmdriver.adapter.GoodTabListAdapter;
import com.chenmaunion.app.cmdriver.adapter.SupplyListAdapter;
import com.chenmaunion.app.cmdriver.bean.Goods.Supply;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.activity.MySupplyListActivity;
import com.chenmaunion.app.cmdriver.ui.activity.TabChoiceActivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.ui.fragment.BaseLifecyclePrintFragment;
import com.kaidongyuan.app.basemodule.utils.nomalutils.DateUtil;
import com.kaidongyuan.app.basemodule.utils.nomalutils.StringUtils;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/7.
 */
public class GoodsFragment extends BaseLifecyclePrintFragment implements AsyncHttpCallback,View.OnClickListener {
    private View parent;
    private TabLayout tabLayout;
    private ArrayList<String> tablist,citylist,companylist,trucktypelist,trucksizelist,
                        selectedcitys,selectedcompanys,selectedtrucktypes,selectedtrucksizes;
    private final int pageCount=10;
    private List<Supply> supplies;
    private SlidingTitleView titleView;
    private TextView title_manager;
    private final String TAG_GetTmsOrgList="GetTmsOrgList";
    private final String TAG_GetItemList="GetItemList";
    private final String TAG_City="线路城市";
    private final String TAG_Company="发布公司";
    private final String TAG_TruckType="车辆类型";
    private final String TAG_TruckSize="车辆尺寸";
    private final int Requestcode=1001;
    private final String TAG_GetSupplyList="GetSupplyList";
    private Dialog mTabsDialog;// 弹窗条件选择 Dialog
    private ListView mListViewTabMap;//条件列表的 ListView
    private Button mButtonCancel;//取消条件选择弹窗
    private SimpleDateFormat df;
    private Date startDate,endDate;
    private GoodTabListAdapter mgoodTabListAdapter;
    private int page = 1;// 用于存储当前数据的页数
    private DriverAsyncHttpClient mClient;
    private SwipeRefreshLayout mswipeRefreshLayout;
    private RecyclerView mrecyclerview;
    private SupplyListAdapter msupplyListAdapter;
    public static boolean isrefresh=false;
    private LinearLayout ll_no_record;
    private LinearLayoutManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        parent=inflater.inflate(R.layout.fragment_goods,container,false);
        mClient=new DriverAsyncHttpClient(this,this);
        citylist=new ArrayList<>();
        companylist=new ArrayList<>();
        trucktypelist=new ArrayList<>();
        trucksizelist=new ArrayList<>();
        selectedtrucksizes=new ArrayList<>();
        selectedtrucktypes=new ArrayList<>();
        selectedcompanys=new ArrayList<>();
        selectedcitys=new ArrayList<>();
        page=1;
        initView();
        getGoods(page);
        initdata();
        return parent;
    }

    private void initdata() {
        Map<String,String>params0=new HashMap<>();
        params0.put("UserID", SharedPreferencesUtils.getUserId());
        params0.put("strLicense","");
        mClient.sendRequest(Constants.URL.GetTmsOrgList,params0,TAG_GetTmsOrgList);
        Map<String,String>params1=new HashMap<>();
        params1.put("UserID", SharedPreferencesUtils.getUserId());
        params1.put("strLicense","");
        mClient.sendRequest(Constants.URL.GetItemList,params1,TAG_GetItemList);
    }

    private void initView() {
        titleView= (SlidingTitleView) parent.findViewById(R.id.slidingtilte_fragment_goods);
        titleView.setMode(SlidingTitleView.MODE_NULL);
        titleView.setText(getString(R.string.goods_page));
        title_manager=titleView.getManagment();
        title_manager.setText("已接货源");
        title_manager.setOnClickListener(this);
        tabLayout= (TabLayout) parent.findViewById(R.id.tablayout_fragment_goods);
        supplies=new ArrayList<>();
        tablist=new ArrayList<>();
      //  tablist.add(TAG_City);
        tablist.add(TAG_Company);
        tablist.add(TAG_TruckType);
        tablist.add(TAG_TruckSize);
        for (int i=0;i<tablist.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(tablist.get(i)));
        }
        for (int j=0;j<tabLayout.getTabCount();j++){
            TabLayout.Tab tab=tabLayout.getTabAt(j);
            tab.setCustomView(getTabView(tablist.get(j)));
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //choiceTabMapDialog(tab);
                Intent intent=new Intent(getMContext(), TabChoiceActivity.class);
                Bundle bundle=new Bundle();
                bundle.putStringArrayList("list",tabdatas(tab));
                bundle.putString("tabtext", (String) tab.getText());
                intent.putExtra("datas",bundle);
                startActivityForResult(intent,Requestcode);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Intent intent=new Intent(getMContext(), TabChoiceActivity.class);
                Bundle bundle=new Bundle();
                bundle.putStringArrayList("list",tabdatas(tab));
                bundle.putString("tabtext", (String) tab.getText());
                intent.putExtra("datas",bundle);
                startActivityForResult(intent,Requestcode);
            }
        });
        df = new SimpleDateFormat("yyyy-MM-dd");
        mrecyclerview= (RecyclerView) parent.findViewById(R.id.recyclerview_goods);
        manager = new LinearLayoutManager(getMContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mrecyclerview.setLayoutManager(manager);
        msupplyListAdapter=new SupplyListAdapter(supplies,getMContext());
        mrecyclerview.setAdapter(msupplyListAdapter);
        mrecyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&(manager.findLastVisibleItemPosition()+1)==msupplyListAdapter.getItemCount()){
                    if (msupplyListAdapter.loadState!=DBShipMentListAdapter.NO_MORE){
                        msupplyListAdapter.loadState=DBShipMentListAdapter.LOADING_MORE;
                        getGoods(page);
                    }
                }
            }
        });
        mswipeRefreshLayout= (SwipeRefreshLayout) parent.findViewById(R.id.swiperefresh_goodsfragment);
        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                getGoods(page);
            }
        });
        mswipeRefreshLayout.setVisibility(View.VISIBLE);
        ll_no_record= (LinearLayout) parent.findViewById(R.id.ll_no_record);
        ll_no_record.setVisibility(View.GONE);
    }

    public View getTabView(String title){
        View view= LayoutInflater.from(getMContext()).inflate(R.layout.tab_goods,null);
        TextView textView= (TextView) view.findViewById(R.id.tv_tab_goods);
        textView.setText(title);
        return view;
    }

    /**
     * 创建选择TAB条件的列表 Dialog
     */
//    private void choiceTabMapDialog(TabLayout.Tab tab) {
//        List<String> datas=tabdatas(tab);
//        if (datas==null&&datas.size()<=0){
//            return;
//        }else {
//            try {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getMContext());
//                mTabsDialog = builder.create();
//                mTabsDialog.setCanceledOnTouchOutside(false);
//                mTabsDialog.show();
//                Window window = mTabsDialog.getWindow();
//                window.setContentView(R.layout.dialog_goods_choice);
//                mListViewTabMap = (ListView) window.findViewById(R.id.listView_tabMaps);
//                mListViewTabMap.setOnItemClickListener(new InnerOnItemClickListener(datas, tab));
//                mListViewTabMap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });
//                mButtonCancel = (Button) window.findViewById(R.id.button_cancel);
//                mButtonCancel.setOnClickListener(this);
//                mgoodTabListAdapter = new GoodTabListAdapter(datas, getMContext());
//                mListViewTabMap.setAdapter(mgoodTabListAdapter);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private ArrayList<String> tabdatas(TabLayout.Tab tab) {
        String str= (String) tab.getText();
        switch (str){
            case TAG_City:
                return citylist;
            case TAG_Company:
                return companylist;
            case TAG_TruckType:
                return trucktypelist;
            case TAG_TruckSize:
                return trucksizelist;
            default:
                return null;
        }

    }

    private  void getGoods(int page){
        if (page==1&&supplies!=null&&supplies.size()>0){
            supplies=new ArrayList<>();
            msupplyListAdapter.loadState= DBShipMentListAdapter.LOADING_MORE;
        }
        Map<String,String> params=new HashMap<>();
        params.put("USER_IDX","");
        params.put("DRIVER_IDX","");
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
        params.put("SUPPLY_WOKERFLOW","新建");
        params.put("PAGE",page+"");
        params.put("PAGE_COUNT",pageCount+"");
        params.put("strLicense", "");
        mClient.setShowToast(false);
        mClient.sendRequest(Constants.URL.GetSupplyList,params,TAG_GetSupplyList);
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
                continue;//仅结束本次循环，但不影响循环继续运行
            }
                ORG_IDX=ORG_IDX+companylist.get(j)+",";
        }
        for (int k=0;k<trucktypelist.size();k++){
            SUPPLY_VEHICLE_TYPE=SUPPLY_VEHICLE_TYPE+trucktypelist.get(k)+",";
        }
        for (int l=0;l<trucksizelist.size();l++){
            SUPPLY_VEHICLE_SIZE=SUPPLY_VEHICLE_SIZE+trucksizelist.get(l)+",";
        }
        ORG_IDX=StringUtils.trimLastChar(ORG_IDX);
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
    public void onResume() {
        super.onResume();
        if (isrefresh){
            page=1;
            getGoods(page);
            isrefresh=false;
        }
    }

    @Override
    public void onDestroyView() {
        mClient.cancleRequest(TAG_GetSupplyList);
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_cancel:
                mTabsDialog.cancel();
                break;
            case R.id.managment:
                Intent intent=new Intent(getMContext(), MySupplyListActivity.class);
                startActivity(intent);
                break;
            default:
                return;
        }
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
                case TAG_GetTmsOrgList:
                    return;
                case TAG_GetItemList:
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

        }else if (request_tag.equals(TAG_GetTmsOrgList)){
            JSONObject jos=JSON.parseObject(msg);
            JSONObject jo=JSON.parseObject(jos.getString("result"));
            String strorgs=jo.getString("ORG_IDX");
            companylist=StringUtils.subbysp(strorgs,",");
            companylist.add(getString(R.string.person_goods));
            companylist.add(getString(R.string.other));
            selectedcompanys=companylist;

        }else if (request_tag.equals(TAG_GetItemList)){
            JSONObject jos=JSON.parseObject(msg);
            JSONObject jo=JSON.parseObject(jos.getString("result"));
            String strtrucksizes=jo.getString("VehicleSize");
            String strtructypes=jo.getString("VehicleType");
            trucktypelist=StringUtils.subbysp(strtructypes,",");
            trucksizelist=StringUtils.subbysp(strtrucksizes,",");
            selectedtrucktypes=trucktypelist;
            selectedtrucksizes=trucksizelist;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==Activity.RESULT_OK){
            switch (requestCode){
                case Requestcode:
                    Bundle bundle=data.getBundleExtra("datas");
                    ArrayList<String> datas=bundle.getStringArrayList("selectedlist");
                    String tabtext=bundle.getString("tabtext");
                    page=1;
                    switch (tabtext){
                        case TAG_City:
                            selectedcitys=datas;
                            getGoods(page);
                            break;
                        case TAG_Company:
                            selectedcompanys=datas;
                            getGoods(page);
                            break;
                        case TAG_TruckType:
                            selectedtrucktypes=datas;
                            getGoods(page);
                            break;
                        case TAG_TruckSize:
                            selectedtrucksizes=datas;
                            getGoods(page);
                            break;
                        default:
                            break;
                    }
            }


        }
    }

    /**
     * 用户业务列表 Item 点击监听实现类
     */
//    public  class InnerOnItemClickListener implements AdapterView.OnItemClickListener {
//        private ArrayList<String> datas;
//        private String tabtext;
//        private Map<Integer,Boolean> selectedMap;
//        public InnerOnItemClickListener(ArrayList<String> datas,Map<Integer,Boolean> selectedMap, String tabtext) {
//            this.datas = datas;
//            this.tabtext=tabtext;
//            this.selectedMap=selectedMap;
//        }
//
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            mTabsDialog.cancel();
////            TextView tv= (TextView) tab.getCustomView().findViewById(R.id.tv_tab_goods);
////            tv.setText(datas.get(position));
//        //    tab.setTag(datas.get(position));//修改tab条件的参数值
//        //    String str= (String) tab.getText();
//            page=1;
//            Iterator iterator=selectedMap.entrySet().iterator();
//           switch (tabtext){
//               case TAG_City:
//                   selectedcitys=new ArrayList<>();
//
//                   while (iterator.hasNext()){
//                       Map.Entry entry= (Map.Entry) iterator.next();
//                       if ((Boolean) entry.getValue()){
//                           selectedcitys.add(datas.get((Integer) entry.getKey()));
//                       }
//                   }
//                   getGoods(page);
//                   break;
//               case TAG_Company:
//                   selectedcompanys=new ArrayList<>();
//                   while (iterator.hasNext()){
//                       Map.Entry entry= (Map.Entry) iterator.next();
//                       if ((Boolean) entry.getValue()){
//                           selectedcompanys.add(datas.get((Integer) entry.getKey()));
//                       }
//                   }
//                   getGoods(page);
//               case TAG_TruckType:
//                   selectedtrucktypes=new ArrayList<>();
//                   while (iterator.hasNext()){
//                       Map.Entry entry= (Map.Entry) iterator.next();
//                       if ((Boolean) entry.getValue()){
//                           selectedtrucktypes.add(datas.get((Integer) entry.getKey()));
//                       }
//                   }
//                   getGoods(page);
//               case TAG_TruckSize:
//                   selectedtrucksizes=new ArrayList<>();
//                   while (iterator.hasNext()){
//                       Map.Entry entry= (Map.Entry) iterator.next();
//                       if ((Boolean) entry.getValue()){
//                           selectedtrucksizes.add(datas.get((Integer) entry.getKey()));
//                       }
//                   }
//                   getGoods(page);
//                default:
//                    break;
//           }
//        }
//    }


}
