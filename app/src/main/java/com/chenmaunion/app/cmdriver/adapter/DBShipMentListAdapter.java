package com.chenmaunion.app.cmdriver.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.chenmaunion.app.cmdriver.BR;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_FootItem;

import com.chenmaunion.app.cmdriver.viewmodel.DBModel_ShipMent;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/16.
 */
public class DBShipMentListAdapter extends RecyclerView.Adapter<DBShipMentListAdapter.BindingViewHolder> {
    private static final int TYPE_ITEM =0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //底部FootView
    public static final int LOADING_MORE=10; //上拉加载更多
    public static final int NO_MORE=11; //无更多数据
    public int loadState=10;//上拉加载状态值
    public ObservableArrayList<DBModel_ShipMent> shipMents;
    public AdapterView.OnItemClickListener mListener;
    public DBModel_FootItem dbmOdelFootItem;

    public DBShipMentListAdapter(ObservableArrayList<DBModel_ShipMent> shipMents) {
        this.shipMents = shipMents;
    }

    public void setData(ObservableArrayList<DBModel_ShipMent> shipMents){
        this.shipMents = shipMents;
        this.notifyDataSetChanged();
    }
    public void setListener(AdapterView.OnItemClickListener listener) {
        mListener = listener;
    }
    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding=null;
        if (viewType==TYPE_ITEM){
            binding= DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.item_cardview_shipment,parent,false);
        }else {
            binding=DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),R.layout.item_recycler_db_foot,parent,false);
            dbmOdelFootItem=new DBModel_FootItem(View.VISIBLE,parent.getContext().getString(R.string.item_more));
        }
        BindingViewHolder holder=new BindingViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_ITEM){
            DBModel_ShipMent shipMent=shipMents.get(position);
            ViewDataBinding binding=holder.getBinding();
            binding.setVariable(BR.model,shipMent);
            binding.executePendingBindings();
        }else {
            ViewDataBinding footbing=holder.getBinding();
            switch (loadState){
                case LOADING_MORE:
                    dbmOdelFootItem.isVISIBLE.set(View.VISIBLE);
                    dbmOdelFootItem.tv_more.set(MyApplication.getInstance().getString(R.string.item_more));
                    footbing.setVariable(BR.model,dbmOdelFootItem);
                    footbing.executePendingBindings();
                    break;
                case NO_MORE:
                    dbmOdelFootItem.isVISIBLE.set(View.GONE);
                    dbmOdelFootItem.tv_more.set(MyApplication.getInstance().getString(R.string.item_nomore));
                    footbing.setVariable(BR.model,dbmOdelFootItem);
                    footbing.executePendingBindings();
                    break;
                default:
                    dbmOdelFootItem.isVISIBLE.set(View.GONE);
                    footbing.setVariable(BR.model,dbmOdelFootItem);
                    footbing.executePendingBindings();
                    break;
            }
        }




    }

    @Override
    public int getItemCount() {
        return shipMents.size()+1;//预加上底部FootView
    }

    @Override
    public int getItemViewType(int position) {
        if (position+1==getItemCount()){
            return TYPE_FOOTER;
        }else {
            return TYPE_ITEM;
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
