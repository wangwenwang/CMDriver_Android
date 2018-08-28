package com.chenmaunion.app.cmdriver.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.fleet.Driver;
import com.chenmaunion.app.cmdriver.bean.fleet.DriverAuthority;
import com.chenmaunion.app.cmdriver.bean.fleet.Truck;
import com.chenmaunion.app.cmdriver.bean.fleet.TruckAuthority;
import com.chenmaunion.app.cmdriver.ui.activity.DriverManageActivity;
import com.chenmaunion.app.cmdriver.ui.activity.TruckDetailActivity;
import com.chenmaunion.app.cmdriver.ui.activity.TruckManageActivity;

import java.util.List;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/23.
 */
public class TrucksAdapter extends RecyclerView.Adapter<TrucksAdapter.MyViewHolder>{
    private List<Truck> trucks;
    private TruckAuthority myauthority;//车辆管理权限类
    private String fleetId;
    private TruckManageActivity mActivity;


    public TrucksAdapter(TruckAuthority authority, List<Truck> trucks, TruckManageActivity activity, String fleetId) {
        myauthority=authority;
        this.trucks = trucks;
        this.mActivity = activity;
        this.fleetId=fleetId;
    }

    public void setData(TruckAuthority authority, List<Truck> trucks){
        this.trucks=trucks;
        myauthority=authority;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_truck,parent,false);
        MyViewHolder holder=new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Truck truck=trucks.get(position);
        holder.tv_PLATE_NUMBER.setText(truck.getPLATE_NUMBER());
        holder.tv_VEHICLE_MODEL.setText(truck.getVEHICLE_MODEL());
        holder.tv_VEHICLE_SIZE.setText(truck.getVEHICLE_SIZE());
        holder.ll_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(mActivity, TruckDetailActivity.class);
                intent.putExtra("strFleetIdx",fleetId);
                intent.putExtra("PLATE_NUMBER",truck.getPLATE_NUMBER());
                mActivity.startActivity(intent);
            }
        });
        if (myauthority.getENABLE_DELETE().equals("Y")){
            holder.iv_delete.setVisibility(View.VISIBLE);
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActivity.deleteDriver(truck);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (trucks!=null&&trucks.size()>0) {
            return trucks.size();
        }else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout ll_driver;
        TextView tv_PLATE_NUMBER,tv_VEHICLE_MODEL,tv_VEHICLE_SIZE;
        ImageView iv_delete;
        public MyViewHolder(View itemView) {
            super(itemView);
            ll_driver= (LinearLayout) itemView.findViewById(R.id.ll_truck);
            tv_PLATE_NUMBER= (TextView) itemView.findViewById(R.id.tv_PLATE_NUMBER);
            tv_VEHICLE_MODEL= (TextView) itemView.findViewById(R.id.tv_VEHICLE_MODEL);
            tv_VEHICLE_SIZE= (TextView) itemView.findViewById(R.id.tv_VEHICLE_SIZE);
            iv_delete= (ImageView) itemView.findViewById(R.id.iv_truck_delete);
        }
    }
}
