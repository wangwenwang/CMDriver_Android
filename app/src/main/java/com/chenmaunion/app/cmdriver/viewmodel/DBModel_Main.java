package com.chenmaunion.app.cmdriver.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.adapter.DBNotifyAdapter;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.bean.Notify;
import com.chenmaunion.app.cmdriver.bean.User;
import com.chenmaunion.app.cmdriver.bean.news.CmNotify;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.activity.AainAcitivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/1/5.
 */
public class DBModel_Main implements AsyncHttpCallback {
    public  ObservableField<String> USER_NAME;//用户手机号
    public  ObservableField<String> USER_TYPE;
    public  ObservableField<String> USER_CODE;//暂时未用上？用户手机号码
    public  ObservableField<String> IDX;
    public AainAcitivity mActivtiy;
    public DriverAsyncHttpClient mClient;
    public ObservableArrayList<DBModel_Notify> notifies;
    private int page = 1;// 用于存储当前数据的页数
    private final int pageSize = 10;// 每页的数据条数
    private final String Tag_Notify="Tag_Notify";
    private DBModel_Notify dbModelNotify;

    public DBModel_Main(AainAcitivity activtiy) {
        User user= MyApplication.getInstance().getUser();
        this.mActivtiy=activtiy;
        mClient=new DriverAsyncHttpClient(mActivtiy,this);
        this.USER_NAME=new ObservableField<>(user.getUSERNAME());
        this.USER_TYPE=new ObservableField<>(user.getUSERTYPE());
        this.USER_CODE=new ObservableField<>(user.getUSERCODE());
        this.IDX=new ObservableField<>(user.getIDX());
        getInformation(page);
    }

    public void onIDXClick(View view){
        Toast.makeText(view.getContext(),this.IDX.get()+"吐丝",Toast.LENGTH_LONG).show();
        USER_NAME.set("ToastName");
        getInformation(page);
    }
    /**
     *@auther: Tom
     *created at 2016/6/12 17:24
     *通过司机的IDX 查找对应的订单消息资讯列表
     */
    private void getInformation(int current_page) {
        if (page==1&&notifies!=null&&notifies.size()>0){
            notifies=new ObservableArrayList<>();
            mActivtiy.dbNotifyAdapter.loadState= DBNotifyAdapter.LOADING_MORE;
        }
        Map<String, String> params = new HashMap<>();
        params.put("strLicense", "");
        params.put("strUserId", SharedPreferencesUtils.getUserId());
        params.put("strPage", current_page + "");
        params.put("strPageCount", pageSize +"");
        mClient.sendRequest(Constants.URL.GetMessage, params, Tag_Notify);
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error") ) {
            Toast.makeText(mActivtiy.getMContext(),msg,Toast.LENGTH_LONG).show();
            return;
        }else if (request_tag.equals(Tag_Notify)){
            if (notifies==null){
                notifies= new ObservableArrayList<>();
            }
            try {
                JSONObject jo= JSON.parseObject(msg);
                List<CmNotify>jo2notifies=JSON.parseArray(jo.getString("result"),CmNotify.class);
                for (int i=0;i<jo2notifies.size();i++){
                    dbModelNotify = new DBModel_Notify();
                    dbModelNotify.notify2DBModelNotify(jo2notifies.get(i));
                    notifies.add(dbModelNotify);
                }
                page++;
                if (jo2notifies.size()<pageSize){
                    mActivtiy.dbNotifyAdapter.loadState=DBNotifyAdapter.NO_MORE;
                }
                mActivtiy.dbNotifyAdapter.setData(notifies);
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}
