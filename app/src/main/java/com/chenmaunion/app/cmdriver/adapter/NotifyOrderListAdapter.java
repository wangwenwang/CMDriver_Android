package com.chenmaunion.app.cmdriver.adapter;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.Notify;


/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2016/12/15.
 */
public class NotifyOrderListAdapter extends BaseAdapter {

    private ObservableArrayList<Notify.OrderMap> SHIPMENT_List;
    private Context mContext;

    public NotifyOrderListAdapter( Context mContext,ObservableArrayList<Notify.OrderMap> SHIPMENT_List) {
        this.mContext = mContext;
        this.SHIPMENT_List=SHIPMENT_List;
    }

    @Override
    public int getCount() {
        if (SHIPMENT_List.size()>0){
            return SHIPMENT_List.size();
        }else {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return SHIPMENT_List.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.item_ordermap_listview,null);
            holder.tv_orderNum= (TextView) convertView.findViewById(R.id.tv_orderNum);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv_orderNum.setText(SHIPMENT_List.get(i).getORD_NO());
        return convertView;
    }

    private class ViewHolder {
        TextView tv_orderNum;
    }
}
