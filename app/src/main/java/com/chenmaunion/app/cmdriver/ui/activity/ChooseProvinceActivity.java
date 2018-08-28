package com.chenmaunion.app.cmdriver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.ProvincesAdapter;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;


/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/6/1.
 */
public class ChooseProvinceActivity  extends BaseActivity {
    private ListView lv_proviences;
    private ProvincesAdapter madapter;
    private String[] provinces;
    private String strProvince;
    private final int mRequestcode=1003;
    private String strCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseprovince);
        provinces=getResources().getStringArray(R.array.province);
       initView();
    }

    private void initView() {
        lv_proviences= (ListView) this.findViewById(R.id.lv_provinces);
        madapter=new ProvincesAdapter();
        lv_proviences.setAdapter(madapter);
       lv_proviences.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               strProvince = provinces[i];
               Intent intent=new Intent(ChooseProvinceActivity.this,ChooseCityActivity.class);
               intent.putExtra("province",strProvince);
               startActivityForResult(intent,mRequestcode);
           }
       });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if (resultCode==RESULT_OK){
        switch (requestCode){
            case mRequestcode:
                strCity = data.getStringExtra("city");
                Intent intent1=new Intent();
                intent1.putExtra("province",strProvince);
                intent1.putExtra("city",strCity);
                ChooseProvinceActivity.this.setResult(RESULT_OK,intent1);
                finish();
                break;
            default:
                break;

        }
    }

    }
}
