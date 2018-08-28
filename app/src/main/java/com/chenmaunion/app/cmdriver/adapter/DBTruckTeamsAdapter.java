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
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_Fleet;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/22.
 */
public class DBTruckTeamsAdapter extends RecyclerView.Adapter<DBTruckTeamsAdapter.BindingViewHolder> {
    public ObservableArrayList<DBModel_Fleet> fleets=new ObservableArrayList<>();

    public DBTruckTeamsAdapter() {
        fleets=new ObservableArrayList<>();
    }

    public void setData(ObservableArrayList<DBModel_Fleet> fleets){
        this.fleets = fleets;
        this.notifyDataSetChanged();
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),R.layout.item_recycler_fleet,parent,false);
        BindingViewHolder holder=new BindingViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        DBModel_Fleet model_fleet=fleets.get(position);
        ViewDataBinding binding=holder.getBinding();

        binding.setVariable(BR.model,model_fleet);
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return fleets.size();
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
