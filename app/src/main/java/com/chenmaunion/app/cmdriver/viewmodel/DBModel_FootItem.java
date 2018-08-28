package com.chenmaunion.app.cmdriver.viewmodel;

import android.databinding.ObservableField;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/1/12.
 */
public class DBModel_FootItem {
    public ObservableField<Integer> isVISIBLE;//进度条是否透明，View.Visible
    public ObservableField<String> tv_more;//String

    public DBModel_FootItem(int isVISIBLE, String tv_more) {
        this.isVISIBLE =new ObservableField<>(isVISIBLE);
        this.tv_more =new ObservableField<>(tv_more);
    }
}
