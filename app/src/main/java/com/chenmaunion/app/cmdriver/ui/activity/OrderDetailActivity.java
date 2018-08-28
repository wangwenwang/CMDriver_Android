package com.chenmaunion.app.cmdriver.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.DBOrderDetailSimpleAdapter;
import com.chenmaunion.app.cmdriver.bean.order.Order;
import com.chenmaunion.app.cmdriver.databinding.ActivityOrderdetailBinding;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_Order;
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_OrderDetail;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.ui.fragmentactivity.BaseToastDialogFragmentActivity;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/9.
 */
public class OrderDetailActivity extends BaseActivity {
    private String order_id,ispay;
    public DBOrderDetailSimpleAdapter orderDetailSimpleAdapter;
    public SlidingTitleView titleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        if (intent.hasExtra("ispay")&&intent.hasExtra("order_id")){
            order_id=intent.getStringExtra("order_id");
            ispay=intent.getStringExtra("ispay");
        }else {
            finish();
        }
        ActivityOrderdetailBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_orderdetail);
        binding.setModel(new DBModel_OrderDetail(this,order_id,ispay));
        LinearLayoutManager manager=new LinearLayoutManager(OrderDetailActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerviewDetails.setLayoutManager(manager);
        orderDetailSimpleAdapter=new DBOrderDetailSimpleAdapter();
        binding.recyclerviewDetails.setAdapter(orderDetailSimpleAdapter);
        binding.titleOrderdetailActivtiy.setMode(SlidingTitleView.MODE_BACK);
        binding.titleOrderdetailActivtiy.setText("订单详情");
    }
}
