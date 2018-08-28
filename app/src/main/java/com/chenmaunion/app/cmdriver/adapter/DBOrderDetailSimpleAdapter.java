package com.chenmaunion.app.cmdriver.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenmaunion.app.cmdriver.BR;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_OrderDetailSimple;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/20.
 */
public class DBOrderDetailSimpleAdapter extends RecyclerView.Adapter<DBOrderDetailSimpleAdapter.BindingViewHolder>{
    public ObservableArrayList<DBModel_OrderDetailSimple> orderDetailSimples;

    public DBOrderDetailSimpleAdapter() {
        orderDetailSimples=new ObservableArrayList<>();
    }

    public void setData(ObservableArrayList<DBModel_OrderDetailSimple> orderDetailSimples){
        this.orderDetailSimples=orderDetailSimples;
        this.notifyDataSetChanged();
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding=null;
        binding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),R.layout.item_recycler_orderdetailsimple,parent,false);
        BindingViewHolder holder=new BindingViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        DBModel_OrderDetailSimple orderDetailSimple=orderDetailSimples.get(position);
        ViewDataBinding contentbinding=holder.getBinding();
        contentbinding.setVariable(BR.model,orderDetailSimple);
        contentbinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return orderDetailSimples.size();
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
