package com.chenmaunion.app.cmdriver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.bean.UserInfo;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.utils.GlideCircleTransform;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.util.HashMap;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/22.
 */
public class InformationActivity extends BaseActivity implements AsyncHttpCallback{
    private SlidingTitleView titleView;
    private TextView tv_user_name,tv_user_phone,tv_user_type,tv_COMPANYNAME,tv_FLEETTYPE,tv_ADDRESS;
    private ImageView iv_user_icon;
    private TextView managment;
    private Button bt_UpdatePassword,bt_UpdateUserInfo;
    private DriverAsyncHttpClient mClient;
    private final String TAG_GetUserInfo="GetUserInfo";
    private UserInfo userInfo;
    public static boolean IsRefresh=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        mClient=new DriverAsyncHttpClient(this,this);
        initView();
        getData();
    }

    private void initView() {
        titleView= (SlidingTitleView) findViewById(R.id.slidingtilte_activity_information);
        titleView.setText(getResources().getString(R.string.mine_information));
        titleView.setMode(SlidingTitleView.MODE_BACK);
        managment = titleView.getManagment();
        managment.setText("账户切换");
        managment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(InformationActivity.this, LoginActivity.class);
                startActivity(intent2);
                MyApplication.IS_LOGIN=false;
                MyApplication.getInstance().AppExit(getMContext());
            }
        });
        tv_user_name= (TextView) findViewById(R.id.tv_user_name);
        tv_COMPANYNAME= (TextView) findViewById(R.id.tv_COMPANYNAME);
        tv_user_phone= (TextView) findViewById(R.id.tv_user_phone);
        tv_FLEETTYPE= (TextView) findViewById(R.id.tv_FLEETTYPE);
        tv_user_type= (TextView) findViewById(R.id.tv_user_type);
        tv_ADDRESS= (TextView) findViewById(R.id.tv_ADDRESS);
        bt_UpdatePassword= (Button) findViewById(R.id.bt_UpdatePassword);
        bt_UpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InformationActivity.this,UpdatePwdActivity.class);
                startActivity(intent);
            }
        });
        bt_UpdateUserInfo= (Button) findViewById(R.id.bt_UpdateUserInfo);
        bt_UpdateUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(InformationActivity.this,UpdateUIFActivity.class);
                intent1.putExtra("USER_CODE",userInfo.getUSER_CODE());
                intent1.putExtra("ADDRESS",userInfo.getADDRESS());
                intent1.putExtra("Avatar",userInfo.getAvatar());
                startActivity(intent1);
            }
        });
        iv_user_icon= (ImageView) findViewById(R.id.iv_user_icon);
    }
    public void getData() {
        Map<String,String> params=new HashMap<>();
        params.put("UserId",SharedPreferencesUtils.getUserId());
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.GetUserInfo,params,TAG_GetUserInfo);
    }

    private void setData(UserInfo userinfo) {
        tv_user_name.setText(userinfo.getUSER_NAME());
        tv_user_phone.setText(userinfo.getUSER_CODE());
        tv_user_type.setText(userinfo.getUSER_TYPE());
        tv_COMPANYNAME.setText(userinfo.getCOMPANYNAME());
        tv_FLEETTYPE.setText(userinfo.getFLEETTYPE());
        tv_ADDRESS.setText(userinfo.getADDRESS());
        Glide.with(InformationActivity.this).load(Constants.URL.Load_Url+"Uploadfile/"+ userinfo.getAvatar()).crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .transform(new GlideCircleTransform(getMContext())).into(iv_user_icon);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (IsRefresh){
            getData();
            IsRefresh=false;
        }
    }

    @Override
    protected void onDestroy() {
        mClient.cancleRequest(TAG_GetUserInfo);
        super.onDestroy();
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            showToastMsg("网络加载失败，请稍后再试");
        }else if (request_tag.equals(TAG_GetUserInfo)){
            JSONObject jos= JSON.parseObject(msg);
            userInfo = JSON.parseObject(jos.getString("result"),UserInfo.class);
            setData(userInfo);
            mClient.cancleRequest(TAG_GetUserInfo);
        }
    }





}
