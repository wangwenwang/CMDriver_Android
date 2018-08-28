package com.chenmaunion.app.cmdriver.viewmodel;

import android.databinding.ObservableField;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.activity.LoginActivity;
import com.chenmaunion.app.cmdriver.ui.activity.RegisterActivity;
import com.chenmaunion.app.cmdriver.ui.activity.TruckTeamListActivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.utils.nomalutils.MD5Util;

import java.util.HashMap;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/1/21.
 */
public class DBModel_Register implements AsyncHttpCallback {
    public ObservableField<String> obPhone;
    public ObservableField<String> obVerification;
    public ObservableField<String> obName;
    public ObservableField<String> obAddress;
    public ObservableField<String> obCompanyName;
    public ObservableField<String> obMotorcade;
    public ObservableField<String> obPassword0;
    public ObservableField<String> obPassword1;
    public ObservableField<String> obUserType;
    public RegisterActivity mActivtiy;
    public DriverAsyncHttpClient mClient;
    private final String Tag_Verification="verification";
    private final String Tag_Register = "register";
    private final String [] usertypes ={"司机","承运商","客户"};
    public DBModel_Register(RegisterActivity mActivtiy) {
        this.mActivtiy = mActivtiy;
        mClient=new DriverAsyncHttpClient(mActivtiy,DBModel_Register.this);
        obPhone=new ObservableField<>("");
        obPassword0=new ObservableField<>("");
        obPassword1=new ObservableField<>("");
        obVerification=new ObservableField<>("");
        obName=new ObservableField<>("");
        obAddress=new ObservableField<>("");
        obCompanyName=new ObservableField<>("");
        obMotorcade=new ObservableField<>("");
        obUserType=new ObservableField<>("");
        Spinner spinner= (Spinner) mActivtiy.findViewById(R.id.spinner_usertype);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(mActivtiy.getMContext(), android.support.v7.appcompat.R.layout.support_simple_spinner_dropdown_item,usertypes);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.VISIBLE);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                obUserType.set(""+((TextView)view).getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
        //注册短信认证的调用方法
    public void onCheckClick(View view){
        String userId=SharedPreferencesUtils.getUserId();



    }
    //申请注册的调用方法
    public void onRegisterClick(View view){
        if (obPhone==null||obPhone.get().isEmpty()){
            mActivtiy.showToastMsg("注册手机号不可为空!");
            return;
        }else if (obPassword0==null||obPassword0.get().trim().isEmpty()||obPassword0.get().trim().length()<6
                ||obPassword1==null||obPassword1.get().trim().isEmpty()||obPassword1.get().trim().length()<6){
            mActivtiy.showToastMsg("设置密码不可为空");
            return;
        }else if (!obPassword0.get().trim().equals(obPassword1.get().trim())){
            obPassword0.set("");
            obPassword1.set("");
            mActivtiy.showToastMsg("两次输入的密码不一致，请重新输入~");
            return;
        }else if (obAddress==null||obAddress.get().trim().isEmpty()){
            mActivtiy.showToastMsg("请填写常住地址");
            return;
        }
//        else if (obVerification==null||obVerification.get().trim().isEmpty()){
//            mActivtiy.showToastMsg("短信验证码不可为空");
//            return;
//        }
        else if (obName==null||obName.get().trim().isEmpty()){
            mActivtiy.showToastMsg("请填写真实姓名");
            return;
        }else if (obUserType==null||obUserType.get().trim().isEmpty()){
            mActivtiy.showToastMsg("请设置用户角色类型");
            return;
        }
        else {
            Map<String,String> params=new HashMap<>();
            params.put("userCode",obPhone.get().trim());
            params.put("passWord", obPassword0.get());
            params.put("userType",getUserType(obUserType));
            params.put("companyName",obCompanyName.get().trim());
            params.put("userName",obName.get().trim());
            params.put("address",obAddress.get().trim());
            params.put("strLicense","");
            mClient.sendRequest(Constants.URL.Register,params,Tag_Register);
        }

    }

    private String getUserType(ObservableField<String> obUserType) {
        switch (obUserType.get().trim()){
            case "司机": return "Driver";
            case "承运商":return "Carrier";
            case "客户":return "Client";
            default:return "Driver";
        }
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            mActivtiy.showToastMsg("网络异常，请稍后再试~");
            return;
        }else if (request_tag.equals(Tag_Register)){
            try {
                JSONObject jo= JSON.parseObject(msg);
                int type=jo.getInteger("type");
                if (type==1){
                    mActivtiy.showToastMsg("账户信息，提交成功！");
                    mActivtiy.finish();
                }else {
                    mActivtiy.showToastMsg(jo.getString("msg"));
                }
            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }
    }
}
