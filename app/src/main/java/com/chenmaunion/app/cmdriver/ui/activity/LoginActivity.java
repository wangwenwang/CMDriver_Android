package com.chenmaunion.app.cmdriver.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.bean.User;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.databinding.ActivityLoginBinding;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_Login;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.utils.nomalutils.Des3;
import com.kaidongyuan.app.basemodule.widget.MLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2016/12/28.
 */
public class LoginActivity extends BaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding= DataBindingUtil.setContentView(LoginActivity.this,R.layout.activity_login);
        binding.setModel(new DBModel_Login(LoginActivity.this));
        MLog.e("LoginActivity:" + LoginActivity.this.toString());
    }
}
