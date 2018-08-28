package com.chenmaunion.app.cmdriver.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.Goods.Routes;
import com.kaidongyuan.app.basemodule.utils.nomalutils.NatureFunctionUtils;

import java.util.List;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/10.
 */
public class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.MyViewHolder> {
    private List<Routes> routes;

    public RoutesAdapter(List<Routes> routes) {
        this.routes = routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_routes,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Routes route=routes.get(position);
        holder.tv_ADDRESS_INFO.setText(route.getADDRESS_INFO());
        holder.tv_ADDRESS_NAME.setText(route.getADDRESS_NAME());
        holder.tv_ADDRESS_PERSON.setText(route.getADDRESS_PERSON());
        holder.tv_ADDRESS_TEL.setText(route.getADDRESS_TEL());
        holder.iv_phone_routes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (route.getADDRESS_TEL().isEmpty()){
                    Toast.makeText(view.getContext(),"联系电话为空值",Toast.LENGTH_SHORT).show();
                }else {
                    NatureFunctionUtils.CallPhone(view.getContext(),route.getADDRESS_TEL());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout ll_route;
        ImageView iv_phone_routes;
        TextView tv_ADDRESS_NAME,tv_ADDRESS_PERSON,tv_ADDRESS_TEL,tv_ADDRESS_INFO;

        public MyViewHolder(View itemView) {
            super(itemView);
            ll_route= (LinearLayout) itemView.findViewById(R.id.ll_route);
            iv_phone_routes= (ImageView) itemView.findViewById(R.id.iv_phone_routes);
            tv_ADDRESS_NAME= (TextView) itemView.findViewById(R.id.tv_ADDRESS_NAME);
            tv_ADDRESS_PERSON= (TextView) itemView.findViewById(R.id.tv_ADDRESS_PERSON);
            tv_ADDRESS_TEL= (TextView) itemView.findViewById(R.id.tv_ADDRESS_TEL);
            tv_ADDRESS_INFO= (TextView) itemView.findViewById(R.id.tv_ADDRESS_INFO);
        }

    }
}
