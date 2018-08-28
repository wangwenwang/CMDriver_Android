package com.chenmaunion.app.cmdriver.viewmodel;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.NotifyOrderListAdapter;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.bean.Notify;
import com.chenmaunion.app.cmdriver.bean.news.CmNotify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/1/9.
 */
/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/1/9.
 */
public class DBModel_Notify implements View.OnClickListener {

    public ObservableField<String> IDX;//用户手机号
    public ObservableField<String> SHIPMENTNO;
    public ObservableField<String>SHIPMENTIDX;
    //  public ObservableArrayList<Notify.OrderMap>SHIPMENT_List;
    public ObservableField<String>USER_ID;
    //    public ObservableField<String>ORD_NO;
//    public ObservableField<String>ORD_IDX;
    public ObservableField<String>TITLE;
    public ObservableField<String>MESSAGE;
    public ObservableField<String>ADD_DATE;
    public ObservableField<Integer>ISREAD;//用于设置是否显示右侧：阅读小红点
    public ObservableField<Integer>TYPE;
    private Dialog mOrdersDialog;
    private NotifyOrderListAdapter mNotifyOrderListAdapter;//用户业务类型 Adapter

    public void notify2DBModelNotify(CmNotify notify){
        IDX=new ObservableField<>(notify.getIDX());
        ADD_DATE=new ObservableField<>(notify.getADD_DATE());
        ISREAD=new ObservableField<>(setIsRead(notify.getISREAD()));
        MESSAGE=new ObservableField<>(notify.getMESSAGE());
//        ORD_IDX=new ObservableField<>(notify.getORD_IDX());
//        SHIPMENT_List=new ObservableArrayList<>();
//        SHIPMENT_List.addAll(notify.getSHIPMENT_List());
        SHIPMENTIDX=new ObservableField<>(notify.getSHIPMENTIDX());
        SHIPMENTNO=new ObservableField<>(notify.getSHIPMENTNO());
//        ORD_NO=new ObservableField<>(notify.getORD_NO());
        USER_ID=new ObservableField<>(notify.getUSER_ID());
        TITLE=new ObservableField<>(notify.getTITLE());
        TYPE=setType(notify.getTYPE());
    }

    private Integer setIsRead(String isread) {
        switch (isread){
            case "y":
                return View.GONE;
            case "n":
                return View.VISIBLE;
            case "0":
                return View.VISIBLE;
            default:
                return View.GONE;
        }


    }

    /**
     * 点击消息条目跳转到查看公告详情页面或弹出选择订单编号对话框
     * @param view
     */
    public void onNotifyClick(View view){
        if (TYPE.get()==0){
            ISREAD.set(View.GONE);
            Intent intent1=new Intent("com.chenmaunion.app.cmdriver.ui.activity.ShipmentInfoActivity");
            intent1.putExtra("notify_id",IDX.get());
            intent1.putExtra("SHIPMENT_IDX",SHIPMENTIDX.get());
            view.getContext().startActivity(intent1);
        }else {
            ISREAD.set(View.GONE);
            Intent intent0=new Intent("com.chenmaunion.app.cmdriver.ui.activity.NotifyActivity");
            intent0.putExtra("title", TITLE.get());
            intent0.putExtra("notify_id", IDX.get());
            intent0.putExtra("message",MESSAGE.get());
            intent0.putExtra("add_date",ADD_DATE.get());
            intent0.putExtra("ISREAD",ISREAD.get());
            view.getContext().startActivity(intent0);
        }
    }

    @BindingAdapter("icon_type")
    public static void setImage(ImageView view,int isOrder){
        int src=getIconType(isOrder);
        view.setImageResource(src);
    }

    private static int getIconType(int isOrder) {
        switch (isOrder){
            case 0:
                return R.drawable.icon_notify1;
            case 1:
                return R.drawable.icon_notify;
            default:
                return R.drawable.icon_notify;
        }
    }

    public ObservableField<Integer>setType(String type){
        if (type!=null&&!type.isEmpty()) {
            switch (Integer.parseInt(type)) {
                case 0:
                    return new ObservableField<>(0);
                case 1:
                    return new ObservableField<>(1);
                default:
                    return new ObservableField<>(1);
            }
        }else {
            return new ObservableField<>(1);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_cancel:mOrdersDialog.dismiss();
                break;
            default:
                break;
        }
    }
}
