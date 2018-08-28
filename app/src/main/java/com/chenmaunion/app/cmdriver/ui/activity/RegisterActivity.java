package com.chenmaunion.app.cmdriver.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.databinding.ActivityRegisterBinding;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_Register;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/1/19.
 */
public class RegisterActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setModel(new DBModel_Register(RegisterActivity.this));
        binding.slidingtitelViewRegisterActivity.setMode(SlidingTitleView.MODE_BACK);
        binding.slidingtitelViewRegisterActivity.setText("帐号注册");
    }




}
