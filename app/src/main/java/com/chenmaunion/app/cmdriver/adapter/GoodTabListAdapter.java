package com.chenmaunion.app.cmdriver.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.R;

import java.util.List;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/24.
 */
public class GoodTabListAdapter extends BaseAdapter{
    private List<String> datas;
    private Context mContext;

    public GoodTabListAdapter(List<String> datas, Context mContext) {
        this.datas = datas;
        this.mContext = mContext;
    }



    @Override
    public int getCount() {
        if (datas!=null&&datas.size()>0){
            return datas.size();
        }else {
            return 0;
        }

    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=View.inflate(mContext, R.layout.item_goodtabs_listview,null);
            holder.tv_data= (TextView) view.findViewById(R.id.tv_data);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        holder.tv_data.setText(datas.get(i));
        return view;
    }

    private class ViewHolder {
        TextView tv_data;
    }

}
