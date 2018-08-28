package com.chenmaunion.app.cmdriver.viewmodel;

import android.content.Intent;
import android.databinding.ObservableField;
import android.text.Editable;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.bean.User;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.activity.AainAcitivity;
import com.chenmaunion.app.cmdriver.ui.activity.LoginActivity;
import com.chenmaunion.app.cmdriver.ui.activity.MainActivity;
import com.chenmaunion.app.cmdriver.ui.activity.RegisterActivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.utils.nomalutils.Des3;
import com.kaidongyuan.app.basemodule.utils.nomalutils.MD5Util;
import com.kaidongyuan.app.basemodule.widget.MLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/1/17.
 */
public class DBModel_Login implements AsyncHttpCallback {
    public ObservableField<String> obName;
    public ObservableField<String> obPwd;
    public LoginActivity mActivtiy;
    public DriverAsyncHttpClient mClient;
    private final String LOGIN = "login";
    public Editable afterTextChanged;
    private final int registerCode = 10;
    public DBModel_Login(LoginActivity mActivtiy) {
        this.mActivtiy = mActivtiy;
        mClient=new DriverAsyncHttpClient(mActivtiy,this);
        obName=new ObservableField<>();
        obPwd=new ObservableField<>();
    }
    public void onLoginClick(View view){
        if (obName==null||obName.get()==null|| obName.get().isEmpty()){
            MLog.e("obName: null" );
            mActivtiy.showToastMsg("请输入用户名~");
            return;
        }
        if (obPwd==null||obPwd.get()==null||obPwd.get().isEmpty()){
            MLog.e("obPwd: null" );
            mActivtiy.showToastMsg("请输入密码~");
            return;
        }
        Map<String,String> params=new HashMap<>();
        params.put("UserCode",obName.get());
        params.put("PassWord", MD5Util.getMD5String(obPwd.get()));
        params.put("strLicense", "");
        MLog.e("params:" + params.toString());
        mClient.sendRequest(Constants.URL.Login, params, LOGIN);
    }
    public void onRegisterClick(View view){
        Intent intent=new Intent(mActivtiy, RegisterActivity.class);
        //mActivtiy.startActivityForResult(intent,registerCode);
        mActivtiy.startActivity(intent);
    }
    public void saveSharedData(){
        SharedPreferencesUtils.WriteSharedPreferences(Constants.BasicInfo, Constants.UserCode,obName.get());
        try {
            SharedPreferencesUtils.WriteSharedPreferences(Constants.BasicInfo, Constants.UserPWD, Des3.encode(obPwd.get()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharedPreferencesUtils.WriteSharedPreferences(Constants.BasicInfo, Constants.IsUsedApp,true);
    }
    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        synchronized (mActivtiy){
            if (msg.equals("error")) return;
            if (request_tag.equals(LOGIN)&&!MyApplication.IS_LOGIN){
                JSONObject jo= JSON.parseObject(msg);
                try {
                    List<User> users= JSON.parseArray(jo.getString("result"),User.class);
                    MLog.e("user.size():" + users.size() + "IDX:" + users.get(0).getIDX());
                    if (users.size() > 0) {
                        users.get(0).setUSERCODE(obName.get());
                        //MLog.e("User:"+user.get(0).getIDX());
                        MyApplication.getInstance().setUser(users.get(0));
                        MyApplication.IS_LOGIN = true;
                    }
                    saveSharedData();
                    SharedPreferencesUtils.saveUserId(MyApplication.getInstance().getUser().getIDX());
//                    MyApplication.getInstance().finishActivity(AainAcitivity.class);
//                    mActivtiy.mStartActivity(AainAcitivity.class);
                    MyApplication.getInstance().finishActivity(MainActivity.class);
                    mActivtiy.mStartActivity(MainActivity.class);
                    mActivtiy.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    mActivtiy.finish();
                }catch (Exception ex){
                   ex.printStackTrace();
                }
            }
        }
    }
}
