package com.chenmaunion.app.cmdriver.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;

import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.util.HashMap;
import java.util.Map;


/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/22.
 */
public class FleetAddActivity  extends BaseActivity implements AsyncHttpCallback{
    private DriverAsyncHttpClient mClient;
    private EditText ed_fleet_name,ed_fleet_manager,ed_fleet_manager_phone,ed_company_name,ed_fleet_mark;
    private TextView tv_fleet_type;
    private LinearLayout ll_company_fleet;
    private SlidingTitleView titleview;
    private  String USER_IDX,FLEET_PROPERTY,TMS_NAME,FLEET_NAME,FLEET_DESC,CONTACT_PERSON,CONTACT_TEL;
    private final  String TAG_AddFleet="AddFleet";
    private AlertDialog mChooseFleetTypeDialog;
    private Button btn_truck_commit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleetadd);
        mClient=new DriverAsyncHttpClient(this,this);
        initView();
    }

    private void initView() {
        titleview= (SlidingTitleView) findViewById(R.id.slidingtilte_activity_fleetadd);
        titleview.setMode(SlidingTitleView.MODE_BACK);
        titleview.setText("创建车队");
        ed_fleet_name= (EditText) findViewById(R.id.ed_fleet_name);
        ed_fleet_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                FLEET_NAME=editable.toString().trim();
            }
        });
        ed_fleet_manager=(EditText)findViewById(R.id.ed_fleet_manager);
        ed_fleet_manager.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CONTACT_PERSON=editable.toString().trim();
            }
        });
        ed_fleet_manager_phone= (EditText) findViewById(R.id.ed_fleet_manager_phone);
        ed_fleet_manager_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                CONTACT_TEL=editable.toString().trim();
            }
        });
        ed_company_name= (EditText) findViewById(R.id.ed_company_name);
        ed_company_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                TMS_NAME=editable.toString().trim();
            }
        });
        ed_fleet_mark= (EditText) findViewById(R.id.ed_fleet_mark);
        ed_fleet_mark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                FLEET_DESC=editable.toString().trim();
            }
        });
        tv_fleet_type= (TextView) findViewById(R.id.tv_fleet_type);
        tv_fleet_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFleetTypeChooseDialog(tv_fleet_type);
            }
        });
        ll_company_fleet= (LinearLayout) findViewById(R.id.ll_company_fleet);
        btn_truck_commit= (Button) findViewById(R.id.btn_truck_commit);
        btn_truck_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comfitfleet();
            }
        });
    }

    private void comfitfleet(){
        USER_IDX= SharedPreferencesUtils.getUserId();
        if (USER_IDX==null||USER_IDX.isEmpty()){
            showToastMsg("App数据丢失，请退出重新登录！");
            Intent intent2 = new Intent(FleetAddActivity.this, LoginActivity.class);
            startActivity(intent2);
            MyApplication.IS_LOGIN=false;
            MyApplication.getInstance().exit(false);
        }else if (FLEET_PROPERTY==null||FLEET_PROPERTY.isEmpty()||
                FLEET_NAME==null||FLEET_NAME.isEmpty()||CONTACT_PERSON==null||CONTACT_PERSON.isEmpty()||
                CONTACT_TEL==null||CONTACT_TEL.isEmpty()){
            showToastMsg("请按格式填写完整的车队信息！");
            return;
        }else {
            Map<String,String> params=new HashMap<>();
            params.put("USER_IDX",USER_IDX);
            params.put("FLEET_PROPERTY",FLEET_PROPERTY);
            params.put("TMS_NAME",TMS_NAME+"");
            params.put("FLEET_NAME",FLEET_NAME);
            params.put("FLEET_DESC",FLEET_DESC+"");
            params.put("CONTACT_PERSON",CONTACT_PERSON);
            params.put("CONTACT_TEL",CONTACT_TEL);
            params.put("strLicense","");
            mClient.sendRequest(Constants.URL.AddFleet,params,TAG_AddFleet);
        }
    }

    /**
     * 版本更新对话框
     * @param tv_fleet_type
     */
    public void createFleetTypeChooseDialog(final TextView tv_fleet_type) {
        if (mChooseFleetTypeDialog==null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getMContext());
            builder.setMessage("根据所需创建的车队类型，选择：公司车队 / 个人车队");
            builder.setCancelable(false);
            builder.setTitle("车队类型");
            builder.setPositiveButton("个人车队", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FLEET_PROPERTY="INDIVIDUAL";//个人车队，代号
                    ll_company_fleet.setVisibility(View.GONE);
                    tv_fleet_type.setText("个人车队");
                    TMS_NAME="";
                    mChooseFleetTypeDialog.cancel();
                }
            });
            builder.setNegativeButton("公司车队", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mChooseFleetTypeDialog.cancel();
                    FLEET_PROPERTY="COMPANY";//公司车队，代号
                    ll_company_fleet.setVisibility(View.VISIBLE);
                    tv_fleet_type.setText("公司车队");
                }
            });
            mChooseFleetTypeDialog = builder.create();
        }
        mChooseFleetTypeDialog.show();
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            showToastMsg("网络状况异常，请稍好再试");
            return;
        }else if (request_tag.equals(TAG_AddFleet)){
            try {
                JSONObject jo= JSON.parseObject(msg);
                int type=jo.getInteger("type");
                if (type==1){
                    showToastMsg("车队提交成功");
                    TruckTeamListActivity.isrefresh=true;
                    finish();
                }else {
                    showToastMsg(jo.getString("msg"));
                }
            }catch (JSONException ex){
                ex.printStackTrace();
            }

        }
    }
}
