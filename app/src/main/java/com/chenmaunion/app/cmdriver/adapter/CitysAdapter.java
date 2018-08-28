package com.chenmaunion.app.cmdriver.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.app.MyApplication;


/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/6/1.
 */
public class CitysAdapter extends BaseAdapter {
    String[] citys= MyApplication.getInstance().getResources().getStringArray(R.array.def);

    public void setCitys(String[] citys) {
        this.citys = citys;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return citys.length;
    }

    @Override
    public Object getItem(int i) {
        return citys[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder=null;
        if (view==null){
            view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_simple_str,viewGroup,false);
            holder=new MyViewHolder();
            holder.tv_simple_str= (TextView) view.findViewById(R.id.tv_simple_str);
            view.setTag(holder);
        }else {
            holder= (MyViewHolder) view.getTag();
        }
        holder.tv_simple_str.setText(citys[i]);

        return view;
    }

    class MyViewHolder{
        public TextView tv_simple_str;
    }

}
