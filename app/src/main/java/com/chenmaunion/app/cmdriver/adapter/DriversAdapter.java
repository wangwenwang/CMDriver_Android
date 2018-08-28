package com.chenmaunion.app.cmdriver.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.fleet.DriverAuthority;
import com.chenmaunion.app.cmdriver.bean.fleet.Driver;
import com.chenmaunion.app.cmdriver.ui.activity.DriverManageActivity;
import com.kaidongyuan.app.basemodule.widget.MLog;

import java.util.List;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/23.
 */
public class DriversAdapter extends RecyclerView.Adapter<DriversAdapter.MyViewHolder> {
    private List<Driver>drivers;
    private DriverAuthority myauthority;//司机管理权限类
    private String fleetId;
    private DriverManageActivity mActivity;
    private AlertDialog mUpdataVersionDialog;
    //private final int AddDriver=1;
//    private final int DeleteDriver=0;
//    private final int InfoDriver=2;

    public DriversAdapter(DriverAuthority authority, List<Driver> drivers, DriverManageActivity activity, String fleetId) {
        myauthority=authority;
        this.drivers = drivers;
        this.mActivity = activity;
        this.fleetId=fleetId;
    }

    public void setData(List<Driver> drivers, DriverAuthority authority){
        this.drivers=drivers;
        myauthority=authority;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_driver,parent,false);
        MyViewHolder holder=new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Driver driver=drivers.get(position);
        if (driver.getAuthority().getENABLE_INFO()!=null&&driver.getAuthority().getENABLE_INFO().equals("Y")){
            holder.tv_driver_type.setText("队长:");
        }else if (driver.getAuthority().getENABLE_DELETE()!=null&&driver.getAuthority().getENABLE_DELETE().equals("Y")){
          //  MLog.e("管理员"+driver.getDRIVER_NAME()+":"+driver.getAuthority().toString());
            holder.tv_driver_type.setText("管理:");
        }else {
            holder.tv_driver_type.setText("司机:");
        }

        holder.tv_driver_phone.setText(driver.getCONTACT_TEL());
        holder.tv_driver_name.setText(driver.getDRIVER_NAME());
//        holder.ll_driver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        //操作人拥有可授权的权限且对方无增加司机的权限时，可授权对方增删的权限
        if (myauthority.getENABLE_INFO()!=null&&myauthority.getENABLE_INFO().equals("Y")
                &&driver.getAuthority().getENABLE_ADD()!=null&&!driver.getAuthority().getENABLE_ADD().equals("Y")){
            holder.iv_info.setVisibility(View.VISIBLE);
            holder.iv_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActivity.infoDriver(driver);
                }
            });
        }else {
            holder.iv_info.setVisibility(View.GONE);
        }
        //操作人拥有可删除的权限且对方为普通司机的权限时，可删除对方;操作人为可授权的车队拥有者且对方不是车队拥有者时，可删除对方
        if (myauthority.getENABLE_DELETE()!=null&&myauthority.getENABLE_DELETE().equals("Y")
                &&driver.getAuthority().getENABLE_DELETE()!=null&&driver.getAuthority().getENABLE_DELETE().equals("N")){
            holder.iv_delete.setVisibility(View.VISIBLE);
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActivity.deleteDriver(driver);
                }
            });
        }else if (myauthority.getENABLE_DELETE()!=null&&myauthority.getENABLE_DELETE().equals("Y")
                &&myauthority.getENABLE_INFO()!=null&&myauthority.getENABLE_INFO().equals("Y")
                &&driver.getAuthority().getENABLE_INFO()!=null&&!driver.getAuthority().getENABLE_INFO().equals("Y")){
            holder.iv_delete.setVisibility(View.VISIBLE);
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActivity.deleteDriver(driver);
                }
            });
        }else {
            holder.iv_delete.setVisibility(View.GONE);
        }
    }



    @Override
    public int getItemCount() {
        if (drivers==null||drivers.size()<=0){
            return 0;
        }
        return drivers.size();
    }

    /**
     * 司机管理对话框
     * @param type 操作类型
     * @param msg 对话框提问内容
     * @param driver 所指定司机Driver
     */
    public void createUpdateDialog(final int type, String msg, final Driver driver) {
        if (mUpdataVersionDialog==null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setMessage(msg+"");
            builder.setCancelable(false);
            builder.setTitle("司机管理");
            builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mUpdataVersionDialog.cancel();
                }
            });
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mUpdataVersionDialog.cancel();
                    MLog.w("driver.name:" + driver.getDRIVER_NAME());
                    switch (type){
                        case 0: mActivity.deleteDriver(driver);
                            break;
                        case 2:mActivity.infoDriver(driver);
                            break;
                        default:
                            break;
                    }

                }
            });
            mUpdataVersionDialog = builder.create();
        }
        mUpdataVersionDialog.show();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout ll_driver;
        TextView tv_driver_type,tv_driver_name,tv_driver_phone;
        ImageView iv_info,iv_delete;
        public MyViewHolder(View itemView) {
            super(itemView);
            ll_driver= (LinearLayout) itemView.findViewById(R.id.ll_driver);
            tv_driver_type= (TextView) itemView.findViewById(R.id.tv_driver_type);
            tv_driver_name= (TextView) itemView.findViewById(R.id.tv_driver_name);
            tv_driver_phone= (TextView) itemView.findViewById(R.id.tv_driver_phone);
            iv_info= (ImageView) itemView.findViewById(R.id.iv_info);
            iv_delete= (ImageView) itemView.findViewById(R.id.iv_delete);
        }
    }
}
