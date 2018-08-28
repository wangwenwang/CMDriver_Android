package com.chenmaunion.app.cmdriver.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.fleet.Driver;
import com.chenmaunion.app.cmdriver.bean.fleet.Truck;

import java.util.List;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/11.
 */
public class SupplyTrucksAdapter extends BaseAdapter {
    List<Truck> trucks;

    public SupplyTrucksAdapter(List<Truck> trucks) {
        this.trucks = trucks;
    }

    @Override
    public int getCount() {
        return trucks.size();
    }

    @Override
    public Object getItem(int i) {
        return trucks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SupplyTrucksAdapter.MyViewHolder holder=null;
        Truck truck=trucks.get(i);
        if (view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_supplytype_truck,viewGroup,false);
            holder=new MyViewHolder();
            holder.tv_PLATE_NUMBER= (TextView) view.findViewById(R.id.tv_PLATE_NUMBER );
            holder.tv_VEHICLE_MODEL= (TextView) view.findViewById(R.id.tv_VEHICLE_MODEL);
            holder.tv_VEHICLE_SIZE= (TextView) view.findViewById(R.id.tv_VEHICLE_SIZE);
            view.setTag(holder);
        }else {
            holder= (MyViewHolder) view.getTag();
        }
        holder.tv_PLATE_NUMBER.setText(truck.getPLATE_NUMBER());
        holder.tv_VEHICLE_MODEL.setText(truck.getVEHICLE_MODEL());
        holder.tv_VEHICLE_SIZE.setText(truck.getVEHICLE_SIZE());
        return view;
    }

    class MyViewHolder{

        public TextView tv_PLATE_NUMBER,tv_VEHICLE_MODEL,tv_VEHICLE_SIZE;
    }
}
