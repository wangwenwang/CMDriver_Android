package com.chenmaunion.app.cmdriver.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.order.SmInfoOrder;
import com.chenmaunion.app.cmdriver.ui.activity.OrderDetailActivity;

import java.util.List;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/8.
 */
public class SmInfoOrderListAdapter extends RecyclerView.Adapter<SmInfoOrderListAdapter.MyViewHolder> {

    private List<SmInfoOrder> smInfoOrders;
    private Context mContext;

    public SmInfoOrderListAdapter(Context mContext, List<SmInfoOrder> smInfoOrders) {
        this.mContext = mContext;
        this.smInfoOrders = smInfoOrders;
    }

    public void setSmInfoOrders(List<SmInfoOrder> smInfoOrders) {
        this.smInfoOrders = smInfoOrders;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_sminfo_order,parent,false);
        MyViewHolder holder=new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final SmInfoOrder smInfoOrder=smInfoOrders.get(position);
        holder.tv_TMS_ORD_NO.setText(smInfoOrder.getTMS_ORD_NO());
        holder.tv_ORD_FROM_NAME.setText(smInfoOrder.getORD_FROM_NAME());
        holder.tv_UPDATE03.setText(smInfoOrder.getUPDATE03());
        holder.tv_ORD_TO_NAME.setText(smInfoOrder.getORD_TO_NAME());
        holder.tv_ORD_TO_ADDRESS.setText(smInfoOrder.getORD_TO_ADDRESS());
        holder.ll_sminfo_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra("ispay",smInfoOrder.getUPDATE03());//订单交付标志,Y/N
                intent.putExtra("order_id",smInfoOrder.getIDX());//订单id
                mContext.startActivity(intent);
            }
        });
        if (smInfoOrder.getUPDATE03().equals("Y")){
            //   holder.ll_sminfo_order.setBackgroundColor(mContext.getResources().getColor(R.color.track_text));

            holder.tv_UPDATE03.setText("已交付");
        }else {
            holder.ll_sminfo_order.setBackground(mContext.getResources().getDrawable(R.drawable.rounded_yellow_box_bg));
            holder.tv_UPDATE03.setText("未交付");
        }
    }

    @Override
    public int getItemCount() {
        if (smInfoOrders!=null&&smInfoOrders.size()>0) {
            return smInfoOrders.size();
        }else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout ll_sminfo_order;
        private TextView tv_TMS_ORD_NO,tv_ORD_FROM_NAME,tv_UPDATE03,tv_ORD_TO_NAME,tv_ORD_TO_ADDRESS;

        public MyViewHolder(View itemView) {
            super(itemView);
            ll_sminfo_order= (LinearLayout) itemView.findViewById(R.id.ll_sminfo_order);
            tv_TMS_ORD_NO= (TextView) itemView.findViewById(R.id.tv_TMS_ORD_NO);
            tv_ORD_FROM_NAME= (TextView) itemView.findViewById(R.id.tv_ORD_FROM_NAME);
            tv_UPDATE03= (TextView) itemView.findViewById(R.id.tv_UPDATE03);
            tv_ORD_TO_NAME= (TextView) itemView.findViewById(R.id.tv_ORD_TO_NAME);
            tv_ORD_TO_ADDRESS= (TextView) itemView.findViewById(R.id.tv_ORD_TO_ADDRESS);
        }
    }
}
