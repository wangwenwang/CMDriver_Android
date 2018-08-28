package com.chenmaunion.app.cmdriver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.fleet.Truck;
import com.chenmaunion.app.cmdriver.bean.fleet.TruckDetail;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.utils.nomalutils.DensityUtil;

import java.util.HashMap;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/23.
 */
public class TruckDetailActivity extends BaseActivity implements AsyncHttpCallback {
    private String PLATE_NUMBER,fleetId;
    private DriverAsyncHttpClient mClient;
    private final String TAG_SearchVehicle="SearchVehicle";
    private TruckDetail truckDetail;
    private TextView tv_truck_number,tv_truck_brand,tv_truck_type,tv_truck_size,tv_truck_color,
            tv_truck_maxwight,tv_truck_maxvolume;
    private ImageView imageView_picture0,imageView_picture1,imageView_picture2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truckdetail);
        Intent intent=getIntent();
        if (intent.hasExtra("PLATE_NUMBER")&&intent.hasExtra("strFleetIdx")){
            PLATE_NUMBER=intent.getStringExtra("PLATE_NUMBER");
            fleetId=intent.getStringExtra("strFleetIdx");
        }else {finish();}
        mClient=new DriverAsyncHttpClient(this,this);
        initView();
        initdata();
    }

    private void initdata() {
        HashMap<String,String> params=new HashMap<>();
        params.put("strFleetIdx",fleetId);
        params.put("PLATE_NUMBER",PLATE_NUMBER);
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.SearchVehicle,params,TAG_SearchVehicle);
    }

    private void initView() {
        tv_truck_number= (TextView) findViewById(R.id.tv_truck_number);
        tv_truck_brand= (TextView) findViewById(R.id.tv_truck_brand);
        tv_truck_type= (TextView) findViewById(R.id.tv_truck_type);
        tv_truck_size= (TextView) findViewById(R.id.tv_truck_size);
        tv_truck_color= (TextView) findViewById(R.id.tv_truck_color);
        tv_truck_maxwight= (TextView) findViewById(R.id.tv_truck_maxwight);
        tv_truck_maxvolume= (TextView) findViewById(R.id.tv_truck_maxvolume);
        imageView_picture0= (ImageView) findViewById(R.id.imageView_picture0);
        imageView_picture1= (ImageView) findViewById(R.id.imageView_picture1);
        imageView_picture2= (ImageView) findViewById(R.id.imageView_picture2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            switch (request_tag){
                case TAG_SearchVehicle:
                    showToastMsg("车辆信息加载失败，请稍后再试");
                    break;

            }
        }else if (request_tag.equals(TAG_SearchVehicle)){
            try {
                JSONObject jos= JSON.parseObject(msg);
                JSONArray ja=jos.getJSONArray("result");
                truckDetail = JSON.parseObject(ja.get(0).toString(),TruckDetail.class);
                tv_truck_number.setText(truckDetail.getPLATE_NUMBER());
                tv_truck_brand.setText(truckDetail.getBRAND_MODEL());
                tv_truck_type.setText(truckDetail.getVEHICLE_MODEL());
                tv_truck_size.setText(truckDetail.getVEHICLE_SIZE());
                tv_truck_color.setText(truckDetail.getVEHICLE_COLOR());
                tv_truck_maxwight.setText(truckDetail.getMAX_WEIGHT());
                tv_truck_maxvolume.setText(truckDetail.getMAX_VOLUME());
                Glide.with(TruckDetailActivity.this).load(Constants.URL.Load_Url+"Uploadfile/"+truckDetail.getPictureFile1()).error(R.drawable.ic_imageview_default_bg).override(DensityUtil.dip2px(400), DensityUtil.dip2px(400))
                        .diskCacheStrategy(DiskCacheStrategy.NONE).crossFade().fitCenter().into(imageView_picture0);
                Glide.with(TruckDetailActivity.this).load(Constants.URL.Load_Url+"Uploadfile/"+truckDetail.getPictureFile2()).error(R.drawable.ic_imageview_default_bg).override(DensityUtil.dip2px(400), DensityUtil.dip2px(400))
                        .diskCacheStrategy(DiskCacheStrategy.NONE).crossFade().fitCenter().into(imageView_picture1);
                Glide.with(TruckDetailActivity.this).load(Constants.URL.Load_Url+"Uploadfile/"+truckDetail.getAutographFile()).error(R.drawable.ic_imageview_default_bg).override(DensityUtil.dip2px(400), DensityUtil.dip2px(400))
                        .diskCacheStrategy(DiskCacheStrategy.NONE).crossFade().fitCenter().into(imageView_picture2);
            }catch (JSONException ex){
                ex.printStackTrace();
            }

        }
    }
}
