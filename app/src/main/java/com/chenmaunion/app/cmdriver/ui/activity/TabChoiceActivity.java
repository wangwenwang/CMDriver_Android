package com.chenmaunion.app.cmdriver.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.GoodTabTypesAdapter;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.ui.fragment.GoodsFragment;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/13.
 */
public class TabChoiceActivity extends BaseActivity {
    private ArrayList<String> tabtypes,selected;
    private Map<Integer,String> choiceMap=new HashMap<>();
    private SlidingTitleView titleview;
    private String tabtext;
    private RecyclerView mrecyclerview;
    private Button button;
    private GoodTabTypesAdapter madapter;
    private final int Requestcode=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabchoice);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("datas");
        tabtext = bundle.getString("tabtext");
        tabtypes=bundle.getStringArrayList("list");
        initView();
    }

    private void initView() {
        titleview= (SlidingTitleView) findViewById(R.id.slidingtilte_activity_tabchoice);
        titleview.setMode(SlidingTitleView.MODE_BACK);
        titleview.setText(tabtext);
        mrecyclerview= (RecyclerView) findViewById(R.id.recyclerview_tabchoice);
        madapter=GoodTabTypesAdapter.getInstance(tabtypes,tabtext,getMContext());
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mrecyclerview.setLayoutManager(layoutManager);
        mrecyclerview.setAdapter(madapter);
        button= (Button) findViewById(R.id.btn_tabchoice);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putStringArrayList("selectedlist",madapter.getSelectedlist());
                bundle.putString("tabtext", tabtext);
                intent.putExtra("datas",bundle);
                TabChoiceActivity.this.setResult(Activity.RESULT_OK,intent);
                TabChoiceActivity.this.finish();
            }
        });

    }
}
