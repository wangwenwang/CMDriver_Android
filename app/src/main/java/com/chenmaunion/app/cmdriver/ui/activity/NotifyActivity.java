package com.chenmaunion.app.cmdriver.ui.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;
import java.util.HashMap;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/9.
 */
public class NotifyActivity extends BaseActivity implements AsyncHttpCallback {
    private SlidingTitleView titleview;
    private TextView tv_title,tv_content,tv_info;
    private Button btn_read;
    private DriverAsyncHttpClient mClient;
    private final String TAG_GetMessageDetils="GetMessageDetils";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_details);
        mClient=new DriverAsyncHttpClient(this,this);
        initview();
        initdata();
    }

    private void initview() {
        titleview= (SlidingTitleView) findViewById(R.id.slidingtilte_activity_notify);
        titleview.setText("资讯详情");
        titleview.setMode(SlidingTitleView.MODE_BACK);
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_content= (TextView) findViewById(R.id.tv_content);
        tv_info= (TextView) findViewById(R.id.tv_info);
        btn_read= (Button) findViewById(R.id.btn_read);
    }

    private void initdata() {
        Intent intent=getIntent();
        if (intent.hasExtra("title")&&intent.hasExtra("notify_id")&&intent.hasExtra("message")&&intent.hasExtra("add_date")
                &&intent.hasExtra("ISREAD")){
            tv_title.setText(intent.getStringExtra("title"));
            tv_content.setText(intent.getStringExtra("message"));
            tv_info.setText(intent.getStringExtra("add_date"));
            if (intent.getIntExtra("ISREAD", View.VISIBLE)==View.VISIBLE){
                setisReadMessage(intent.getStringExtra("notify_id").trim());
            }
        }else {
            finish();
        }
    }
    /**
     * 仅用于访问服务器，更改消息的读取状态ISREAD
     * @param notify_id
     */
    private void setisReadMessage(String notify_id) {
        Map<String,String> params=new HashMap<>();
        params.put("IDX",notify_id);
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.GetMessageDetils,params,TAG_GetMessageDetils,false);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            switch (request_tag){
                case TAG_GetMessageDetils:
                    break;

                default:
                    break;
            }
        }else if (TAG_GetMessageDetils.equals(request_tag)){
            JSONObject jos= JSON.parseObject(msg);


        }
    }
}
