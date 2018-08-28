package com.chenmaunion.app.cmdriver.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.OrderFMViewpageAdapter;
import com.chenmaunion.app.cmdriver.ui.widget.IsScrollableViewPager;
import com.kaidongyuan.app.basemodule.ui.fragment.BaseLifecyclePrintFragment;
import com.kaidongyuan.app.basemodule.widget.MLog;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/7.
 */
public class OrdersFragment extends BaseLifecyclePrintFragment {
    private SlidingTitleView titleView;
    private IsScrollableViewPager viewPager;
    private TabLayout tabLayout;
    private View parent;
    private List<Fragment> fragments;
    private List<String>tablist;
    private String truckteam="";//车队代号，默认为空
    private OrderListFragment orderListFragmentN;
    private OrderListFragment orderListFragmentY;
    private OrderFMViewpageAdapter orderFMViewpageAdapter;
       private OrderListFragment orderListFragmentAll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        parent=inflater.inflate(R.layout.fragment_orders,container,false);
        initView();
        return parent;
    }

    private void initView() {
        titleView= (SlidingTitleView) parent.findViewById(R.id.title_orders_fragment);
        titleView.setMode(SlidingTitleView.MODE_NULL);
        titleView.setText("装运列表");
        //titleView.getManagment().setText(R.string.all＿truckteam);
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"按车队查询功能，待开发",Toast.LENGTH_SHORT).show();
                orderListFragmentN.OnTruckteamSelect(truckteam);
                orderListFragmentAll.OnTruckteamSelect(truckteam);
                orderListFragmentY.OnTruckteamSelect(truckteam);
            }
        });
        viewPager= (IsScrollableViewPager) parent.findViewById(R.id.viewPager_ordersFragment);
        viewPager.setisScroll(true);
        tabLayout= (TabLayout) parent.findViewById(R.id.tablayout_ordersFragment);
        tablist=new ArrayList<>();
        tablist.add("未完成");
        tablist.add("已完成");
        tablist.add("全部");
        fragments=new ArrayList<>();
        orderListFragmentN = new OrderListFragment();
        Bundle bundleN=new Bundle();
        bundleN.putString("TAG_OrderType","UNFINISH");
        bundleN.putString("TruckTeam",truckteam);
        orderListFragmentN.setArguments(bundleN);
        fragments.add(orderListFragmentN);
        orderListFragmentY = new OrderListFragment();
        Bundle bundleY=new Bundle();
        bundleY.putString("TAG_OrderType","FINISH");
        bundleN.putString("TruckTeam",truckteam);
        orderListFragmentY.setArguments(bundleY);
        fragments.add(orderListFragmentY);
        orderListFragmentAll = new OrderListFragment();
        Bundle bundleAll=new Bundle();
        bundleAll.putString("TAG_OrderType","");
        bundleN.putString("TruckTeam",truckteam);
        orderListFragmentAll.setArguments(bundleAll);
        fragments.add(orderListFragmentAll);
        for (int i=0;i<tablist.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(tablist.get(i)));
        }
        orderFMViewpageAdapter = new OrderFMViewpageAdapter(getFragmentManager(),fragments,tablist,getActivity());
        viewPager.setAdapter(orderFMViewpageAdapter);
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);
        for (int j=0;j<tabLayout.getTabCount();j++){
            TabLayout.Tab tab=tabLayout.getTabAt(j);
            tab.setCustomView(orderFMViewpageAdapter.getTabView(tablist.get(j)));
        }
        viewPager.setCurrentItem(0);
    }


    @Override
    public void onDestroyView() {
        FragmentTransaction trans =getFragmentManager().beginTransaction();
        trans.remove(orderListFragmentN);
        trans.remove(orderListFragmentY);
        trans.remove(orderListFragmentAll);
        trans.commit();
        super.onDestroyView();

    }
}
