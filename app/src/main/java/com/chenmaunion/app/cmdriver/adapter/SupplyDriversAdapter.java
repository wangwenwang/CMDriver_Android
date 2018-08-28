package com.chenmaunion.app.cmdriver.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.fleet.Driver;
import com.chenmaunion.app.cmdriver.bean.fleet.Fleet;

import java.util.List;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/11.
 */
public class SupplyDriversAdapter extends BaseAdapter {

    List<Driver> drivers;

    public SupplyDriversAdapter(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public int getCount() {
        return drivers.size();
    }

    @Override
    public Object getItem(int i) {
        return drivers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SupplyDriversAdapter.MyViewHolder holder=null;
        Driver driver=drivers.get(i);
        if (view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_supplytype_driver,viewGroup,false);
            holder=new MyViewHolder();
            holder.tv_Driver_Name= (TextView) view.findViewById(R.id.tv_Driver_Name);
            holder.tv_Driver_Tel= (TextView) view.findViewById(R.id.tv_Driver_Tel);
            view.setTag(holder);
        }else {
            holder= (MyViewHolder) view.getTag();
        }
        holder.tv_Driver_Name.setText(driver.getDRIVER_NAME());
        holder.tv_Driver_Tel.setText(driver.getCONTACT_TEL());
        return view;
    }

    class MyViewHolder{
        public TextView tv_Driver_Name,tv_Driver_Tel;
    }

}
