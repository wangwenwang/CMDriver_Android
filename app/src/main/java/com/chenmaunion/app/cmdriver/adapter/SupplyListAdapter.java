package com.chenmaunion.app.cmdriver.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.BR;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.bean.Goods.Supply;
import com.chenmaunion.app.cmdriver.bean.fleet.Truck;
import com.chenmaunion.app.cmdriver.ui.activity.MySupplyListActivity;
import com.chenmaunion.app.cmdriver.ui.activity.SupplyDetailActivity;
import com.chenmaunion.app.cmdriver.ui.activity.TruckManageActivity;
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_FootItem;

import java.util.List;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/2.
 */
public class SupplyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_ITEM =0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //底部FootView
    public static final int LOADING_MORE=10; //上拉加载更多
    public static final int NO_MORE=11; //无更多数据
    public int loadState=10;//上拉加载状态值
    private List<Supply> supplies;
    private Context mContext;
    public DBModel_FootItem dbmOdelFootItem;
    public SupplyListAdapter(List<Supply> supplies, Context mContext) {
        this.supplies = supplies;
        this.mContext = mContext;
    }

    public void setData( List<Supply> supplies){
        this.supplies=supplies;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_ITEM){
            View itemView= LayoutInflater.from(mContext).inflate(R.layout.item_cardview_goods,parent,false);
            MyViewHolder holder=new MyViewHolder(itemView);
            return holder;
        }else {
            ViewDataBinding binding=null;
            binding= DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.item_recycler_db_foot,parent,false);
            dbmOdelFootItem=new DBModel_FootItem(View.VISIBLE,mContext.getString(R.string.item_more));
            BindingViewHolder holder=new BindingViewHolder(binding.getRoot());
            holder.setBinding(binding);
            return holder;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_ITEM){
            final Supply supply=supplies.get(position);
            MyViewHolder myholder= (MyViewHolder) holder;
            if (supply.getSUPPLY_STATE()==null||supply.getSUPPLY_WOKERFLOW()==null){
                myholder.iv_supply_state.setVisibility(View.GONE);
            }else if (supply.getSUPPLY_WOKERFLOW().equals("CANCEL")){
                myholder.iv_supply_state.setImageResource(R.drawable.icon_supply_cancel);
            }else if (supply.getSUPPLY_WOKERFLOW().equals("新建")){
                if (mContext instanceof MySupplyListActivity){
                    myholder.iv_supply_state.setImageResource(R.drawable.icon_supply_return);
                }else {
                myholder.iv_supply_state.setImageResource(R.drawable.icon_supply_acceptable);
                }
            }else if (supply.getSUPPLY_WOKERFLOW().equals("已接单")){
                    myholder.iv_supply_state.setImageResource(R.drawable.icon_supply_accepted);
            }else if (supply.getSUPPLY_WOKERFLOW().equals("已确认")){
                myholder.iv_supply_state.setImageResource(R.drawable.icon_supply_confirm);
            }else if (supply.getSUPPLY_WOKERFLOW().equals("已发运")){
                myholder.iv_supply_state.setImageResource(R.drawable.icon_supply_inway);
            }else if (supply.getSUPPLY_WOKERFLOW().equals("已完成")){
                myholder.iv_supply_state.setImageResource(R.drawable.icon_supply_finished);
            }
            //   myholder.tv_SUPPLY_NO.setText(supply.getSUPPLY_NO());
            myholder.tv_ADD_DATE.setText(supply.getADD_DATE());
            myholder.tv_TOTAL_AMOUNT.setText(supply.getTOTAL_AMOUNT());
            myholder.tv_SUPPLY_VEHICLE_TYPE.setText(supply.getSUPPLY_VEHICLE_TYPE());
            myholder.tv_SUPPLY_VEHICLE_SIZE.setText(supply.getSUPPLY_VEHICLE_SIZE());
            myholder.tv_ORG_NAME.setText(getSupplyORG(supply.getORG_NAME()));
            myholder.tv_ROUTES_CITY.setText(supply.getROUTES_CITY());
            myholder.tv_SUPPLY_WOKERFLOW.setText(supply.getSUPPLY_WOKERFLOW());
            myholder.tv_SUPPLY_STATE.setText(toSupply_State(supply.getSUPPLY_STATE()));
            myholder.tv_TOTAL_QTY.setText(supply.getTOTAL_QTY());
            myholder.tv_TOTAL_WEIGHT.setText(supply.getTOTAL_WEIGHT());
            myholder.tv_TOTAL_VOLUME.setText(supply.getTOTAL_VOLUME());
            myholder.cd_supply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mContext, SupplyDetailActivity.class);
                    intent.putExtra("IDX",supply.getIDX());
                    mContext.startActivity(intent);
                }
            });
        }else {
            BindingViewHolder bindingViewHolder= (BindingViewHolder) holder;
            ViewDataBinding footbing = bindingViewHolder.getBinding();
            switch (loadState) {
                case LOADING_MORE:
                    dbmOdelFootItem.isVISIBLE.set(View.VISIBLE);
                    dbmOdelFootItem.tv_more.set(MyApplication.getInstance().getString(R.string.item_more));
                    footbing.setVariable(BR.model, dbmOdelFootItem);
                    footbing.executePendingBindings();
                    break;
                case NO_MORE:
                    dbmOdelFootItem.isVISIBLE.set(View.GONE);
                    dbmOdelFootItem.tv_more.set(MyApplication.getInstance().getString(R.string.item_nomore));
                    footbing.setVariable(BR.model, dbmOdelFootItem);
                    footbing.executePendingBindings();
                    break;
                default:
                    dbmOdelFootItem.isVISIBLE.set(View.GONE);
                    footbing.setVariable(BR.model, dbmOdelFootItem);
                    footbing.executePendingBindings();
                    break;
            }
        }
    }

    private CharSequence getSupplyORG(String org_name) {
        if (org_name==null||org_name.isEmpty()){
            return "个人货源";
        }else {
            return org_name;
        }
    }

    private String toSupply_State(String supply_state) {

        if (supply_state.equals("OPEN")){
            return "可接货单";
        }else {
            return "已被接单:"+supply_state;
        }
    }

    @Override
    public int getItemCount() {
        return supplies.size()+1;//预加上底部FootView
    }
    @Override
    public int getItemViewType(int position) {
        if (position+1==getItemCount()){
            return TYPE_FOOTER;
        }else {
            return TYPE_ITEM;
        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_ADD_DATE,tv_TOTAL_AMOUNT,tv_SUPPLY_VEHICLE_TYPE,
                tv_SUPPLY_VEHICLE_SIZE,tv_ORG_NAME,tv_ROUTES_CITY,tv_SUPPLY_WOKERFLOW,
                tv_SUPPLY_STATE,tv_TOTAL_QTY,tv_TOTAL_WEIGHT,tv_TOTAL_VOLUME;
        CardView cd_supply;
        ImageView iv_supply_state;
        public MyViewHolder(View itemView) {
            super(itemView);
            cd_supply= (CardView) itemView.findViewById(R.id.cardView_supply);
            iv_supply_state= (ImageView) itemView.findViewById(R.id.iv_supply_state);
            //  tv_SUPPLY_NO= (TextView) itemView.findViewById(R.id.tv_SUPPLY_NO);
            tv_ADD_DATE= (TextView) itemView.findViewById(R.id.tv_ADD_DATE);
            tv_TOTAL_AMOUNT= (TextView) itemView.findViewById(R.id.tv_TOTAL_AMOUNT);
            tv_SUPPLY_VEHICLE_TYPE= (TextView) itemView.findViewById(R.id.tv_SUPPLY_VEHICLE_TYPE);
            tv_SUPPLY_VEHICLE_SIZE= (TextView) itemView.findViewById(R.id.tv_SUPPLY_VEHICLE_SIZE);
            tv_ORG_NAME= (TextView) itemView.findViewById(R.id.tv_ORG_NAME);
            tv_ROUTES_CITY= (TextView) itemView.findViewById(R.id.tv_ROUTES_CITY);
            tv_SUPPLY_WOKERFLOW= (TextView) itemView.findViewById(R.id.tv_SUPPLY_WOKERFLOW);
            tv_SUPPLY_STATE= (TextView) itemView.findViewById(R.id.tv_SUPPLY_STATE);
            tv_TOTAL_QTY= (TextView) itemView.findViewById(R.id.tv_TOTAL_QTY);
            tv_TOTAL_WEIGHT= (TextView) itemView.findViewById(R.id.tv_TOTAL_WEIGHT);
            tv_TOTAL_VOLUME= (TextView) itemView.findViewById(R.id.tv_TOTAL_VOLUME);

        }
    }


    public class BindingViewHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding binding;

        public BindingViewHolder(View itemView) {
            super(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding){
            this.binding=binding;
        }
    }


}
