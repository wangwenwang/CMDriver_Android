package com.chenmaunion.app.cmdriver.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.fleet.Fleet;

import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/10.
 */
public class SupplyFleetsAdapter extends BaseAdapter {

    List<Fleet> fleets;

    public SupplyFleetsAdapter(List<Fleet> fleets) {
        this.fleets = fleets;
    }

    @Override
    public int getCount() {
        return fleets.size();
    }

    @Override
    public Object getItem(int i) {
        return fleets.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SupplyFleetsAdapter.MyViewHolder holder=null;
        Fleet fleet=fleets.get(i);
        if (view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_supplytype_fleet,viewGroup,false);
            holder=new MyViewHolder();
            holder.tv_FLEET_NAME= (TextView) view.findViewById(R.id.tv_FLEET_NAME);
            holder.tv_Fleet_Person_Name= (TextView) view.findViewById(R.id.tv_Fleet_Person_Name);
            holder.tv_Fleet_Person_Tel= (TextView) view.findViewById(R.id.tv_Fleet_Person_Tel);
            view.setTag(holder);
        }else {
            holder= (MyViewHolder) view.getTag();
        }
        holder.tv_FLEET_NAME.setText(fleet.getFLEET_NAME());
        holder.tv_Fleet_Person_Name.setText(fleet.getCONTACT_PERSON());
        holder.tv_Fleet_Person_Tel.setText(fleet.getCONTACT_TEL());
        return view;
    }

    class MyViewHolder{
        public TextView tv_FLEET_NAME,tv_Fleet_Person_Name,tv_Fleet_Person_Tel;
    }

}
