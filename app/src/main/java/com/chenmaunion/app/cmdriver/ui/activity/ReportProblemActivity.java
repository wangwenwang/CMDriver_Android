package com.chenmaunion.app.cmdriver.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.util.HashMap;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/5/19.
 */
public class ReportProblemActivity extends BaseActivity implements AsyncHttpCallback {
    private EditText ed_problem_content;
    private Button bt_report_problem;
    private SlidingTitleView titleView;
    private DriverAsyncHttpClient mClient;
    private final String TAG_UserFeedback="UserFeedback";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportproblem);
        mClient=new DriverAsyncHttpClient(this,this);
        initView();
    }

    private void initView() {
        titleView= (SlidingTitleView) findViewById(R.id.slidingtilte_activity_reportproblem);
        titleView.setText(getString(R.string.report_problem));
        titleView.setMode(SlidingTitleView.MODE_BACK);
        ed_problem_content= (EditText) findViewById(R.id.ed_problem_content);
        bt_report_problem= (Button) findViewById(R.id.bt_report_problem);
        bt_report_problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportdata();
            }
        });
    }

    private void reportdata() {
        HashMap<String,String> params=new HashMap<>();
            params.put("strLicense","");
            params.put("UserId", SharedPreferencesUtils.getUserId());
            params.put("Content",ed_problem_content.getText().toString());
        mClient.sendRequest(Constants.URL.UserFeedback,params,TAG_UserFeedback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            showToastMsg("网络提交失败~");
        }else if (request_tag.equals(TAG_UserFeedback)){
            JSONObject jo= JSON.parseObject(msg);
            int type=jo.getInteger("type");
            if (type==1){
                showToastMsg("您的反馈提交成功，谢谢！");
                finish();
            }else {
                showToastMsg("提交失败:"+msg);
            }
        }
    }
}
