package com.chenmaunion.app.cmdriver.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.fleet.SDriver;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.kaidongyuan.app.basemodule.widget.MLog;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/13.
 */
public class TestActivity extends Activity implements View.OnClickListener {
private SlidingTitleView titleView;
    private Dialog driverAddDialog;
    private EditText ed_driver_search;
    private ImageButton bt_driverSearch;
    private Button bt_dialog_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }



    private void init() {
        titleView= (SlidingTitleView) findViewById(R.id.slidingtilte_activity_test);
        titleView.setMode(SlidingTitleView.MODE_BACK);
        titleView.setText("测试用Acitivity");
        TextView managment=titleView.getManagment();
        managment.setText("管理");
        managment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driverAddDialog();
            }
        });
    }

    /**
     * 创建选择添加司机 Dialog
     */
    private void driverAddDialog() {
        try {
          //  AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
            driverAddDialog = new Dialog(TestActivity.this);
            driverAddDialog.setCanceledOnTouchOutside(false);
            driverAddDialog.show();
            Window window = driverAddDialog.getWindow();
            window.setContentView(R.layout.dialog_driver_search);
            ed_driver_search= (EditText) window.findViewById(R.id.ed_driver_search);
            ed_driver_search.setOnClickListener(this);
//            ed_driver_search.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(final View view) {
//                    view.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                            if (imm!=null){
//                                ed_driver_search.requestFocus();
//                                Boolean isFocusable=ed_driver_search.isFocusable();
//                                ed_driver_search.requestFocusFromTouch();
//                                Boolean isShow=imm.showSoftInput(ed_driver_search,InputMethodManager.SHOW_IMPLICIT);
//                                MLog.e("isFocusable:"+isFocusable.toString()+"\tisShow:"+isShow.toString());
//                            }
//                        }
//                    },500);
//                }
//            });
            bt_driverSearch= (ImageButton) window.findViewById(R.id.bt_driver_search);
            bt_driverSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    if (ed_driver_search.getText().toString().length()>=7){
//                        getDriverInfo(ed_driver_search.getText().toString());
//                    }
                }
            });

            bt_dialog_cancel = (Button) window.findViewById(R.id.bt_dialog_cancel);
            bt_dialog_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    driverAddDialog.cancel();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {

    }
}
