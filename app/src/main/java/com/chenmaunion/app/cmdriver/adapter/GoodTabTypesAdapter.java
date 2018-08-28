package com.chenmaunion.app.cmdriver.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.ui.fragment.GoodsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/13.
 */
public class GoodTabTypesAdapter extends RecyclerView.Adapter<GoodTabTypesAdapter.MyViewHolder> implements View.OnClickListener {
    //数据源
    private List<String> list;
    private Context context;
    private static GoodTabTypesAdapter cityadapter,companyadapter,trucktypeadapter,trucksizeadapter;
    private static final String TAG_City="线路城市";
    private static final String TAG_Company="发布公司";
    private static final String TAG_TruckType="车辆类型";
    private static final String TAG_TruckSize="车辆尺寸";


    //是否显示单选框,默认false
    private boolean isshowBox = false;
    // 存储勾选框状态的map集合
    private Map<Integer, Boolean> map = new HashMap<>();
    private GoodTabTypesAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        initMap();
    }

    public static GoodTabTypesAdapter getInstance(List<String> list,String tabtext, Context context){
        switch (tabtext){
            case TAG_City:
                if (cityadapter==null) {
                    cityadapter = new GoodTabTypesAdapter(list, context);
                }
                return cityadapter;
            case TAG_Company:
                if (companyadapter==null) {
                    companyadapter = new GoodTabTypesAdapter(list, context);
                }
                    return companyadapter;
            case TAG_TruckType:
                if (trucktypeadapter==null) {
                    trucktypeadapter = new GoodTabTypesAdapter(list, context);
                }
                    return trucktypeadapter;
            case TAG_TruckSize:
                if (trucksizeadapter==null) {
                    trucksizeadapter = new GoodTabTypesAdapter(list, context);
                }
                    return trucksizeadapter;
            default:
                return null;

        }

    }


    //初始化map集合,默认为不选中
    private void initMap() {
        for (int i = 0; i < list.size(); i++) {
            map.put(i, true);
        }
    }
    public void setMap(Map<Integer,Boolean> selectedMap){
        map=selectedMap;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<Integer, Boolean> getMap() {
        return map;
    }
    public ArrayList<String> getSelectedlist(){
        Iterator iterator=map.entrySet().iterator();
        ArrayList<String> selectedlist=new ArrayList<>();
            while (iterator.hasNext()){
                Map.Entry entry= (Map.Entry) iterator.next();
                if ((Boolean) entry.getValue()){
                    selectedlist.add(list.get((Integer) entry.getKey()));
                }
            }
        return selectedlist;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goodtabs_listview, parent, false);
        MyViewHolder vh = new MyViewHolder(root);
        //为Item设置点击事件
        root.setOnClickListener(this);
        return vh;
    }
    //点击事件
    @Override
    public void onClick(View v) {
            //注意这里使用getTag方法获取数据

        if (map.get(v.getTag())){
            map.put((Integer) v.getTag(),false);
        }else {
            map.put((Integer) v.getTag(),true);
        }
        notifyItemChanged((Integer) v.getTag());
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(list.get(position));
        //设置Tag 用于
        holder.root.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //用map集合保存
                map.put(position, isChecked);
            }
        });
        // 设置CheckBox的状态
        if (map.get(position) == null) {
            map.put(position, false);
        }
        holder.checkBox.setChecked(map.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //视图管理
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private CheckBox checkBox;
        private View root;

        public MyViewHolder(View root) {
            super(root);
            this.root = root;
            title = (TextView) root.findViewById(R.id.tv_data);
            checkBox = (CheckBox) root.findViewById(R.id.cb_data);
        }
    }
}
