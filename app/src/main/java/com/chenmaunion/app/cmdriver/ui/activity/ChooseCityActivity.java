package com.chenmaunion.app.cmdriver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.CitysAdapter;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;


/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/6/1.
 */
public class ChooseCityActivity extends BaseActivity {
    private ListView lv_citys;
    private CitysAdapter madapter;
    private String[] citys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseprovince);
        Intent intent=getIntent();
        if (intent.hasExtra("province")){
            initData(intent.getStringExtra("province"));
        }else {
            finish();
        }
       initView();
    }

    private void initData(String province) {
        switch (province.trim()){
            case "北京":citys=getResources().getStringArray(R.array.北京);
                break;
            case "天津":citys=getResources().getStringArray(R.array.天津);
                break;
            case "河北":citys=getResources().getStringArray(R.array.河北);
                break;
            case "山西":citys=getResources().getStringArray(R.array.山西);
                break;
            case "内蒙古":citys=getResources().getStringArray(R.array.内蒙古);
                break;
            case "辽宁":citys=getResources().getStringArray(R.array.辽宁);
                break;
            case "吉林":citys=getResources().getStringArray(R.array.吉林);
                break;
            case "黑龙江":citys=getResources().getStringArray(R.array.黑龙江);
                break;
            case "上海":citys=getResources().getStringArray(R.array.上海);
                break;
            case "江苏":citys=getResources().getStringArray(R.array.江苏);
                break;
            case "浙江":citys=getResources().getStringArray(R.array.浙江);
                break;
            case "安徽":citys=getResources().getStringArray(R.array.安徽);
                break;
            case "福建":citys=getResources().getStringArray(R.array.福建);
                break;
            case "江西":citys=getResources().getStringArray(R.array.江西);
                break;
            case "山东":citys=getResources().getStringArray(R.array.山东);
                break;
            case "河南":citys=getResources().getStringArray(R.array.河南);
                break;
            case "湖北":citys=getResources().getStringArray(R.array.湖北);
                break;
            case "湖南":citys=getResources().getStringArray(R.array.湖南);
                break;
            case "广东":citys=getResources().getStringArray(R.array.广东);
                break;
            case "广西":citys=getResources().getStringArray(R.array.广西);
                break;
            case "海南":citys=getResources().getStringArray(R.array.海南);
                break;
            case "重庆":citys=getResources().getStringArray(R.array.重庆);
                break;
            case "四川":citys=getResources().getStringArray(R.array.四川);
                break;
            case "贵州":citys=getResources().getStringArray(R.array.贵州);
                break;
            case "云南":citys=getResources().getStringArray(R.array.云南);
                break;
            case "西藏":citys=getResources().getStringArray(R.array.西藏);
                break;
            case "陕西":citys=getResources().getStringArray(R.array.陕西);
                break;
            case "甘肃":citys=getResources().getStringArray(R.array.甘肃);
                break;
            case "青海":citys=getResources().getStringArray(R.array.青海);
                break;
            case "宁夏":citys=getResources().getStringArray(R.array.宁夏);
                break;
            case "新疆":citys=getResources().getStringArray(R.array.新疆);
                break;
            case "台湾":citys=getResources().getStringArray(R.array.台湾);
                break;
            case "香港":citys=getResources().getStringArray(R.array.香港);
                break;
            case "澳门":citys=getResources().getStringArray(R.array.澳门);
                break;
            default:
                break;

        }
    }

    private void initView() {
        lv_citys= (ListView) this.findViewById(R.id.lv_provinces);
        madapter=new CitysAdapter();
        lv_citys.setAdapter(madapter);
        madapter.setCitys(citys);
        lv_citys.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String strCity=citys[i];
               Intent intent1=new Intent();
               intent1.putExtra("city",strCity);
               ChooseCityActivity.this.setResult(RESULT_OK,intent1);
               finish();
           }
       });
    }
}
