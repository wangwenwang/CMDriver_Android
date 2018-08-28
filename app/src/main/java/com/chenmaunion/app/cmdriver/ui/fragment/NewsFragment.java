package com.chenmaunion.app.cmdriver.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.adapter.DBNotifyAdapter;
import com.chenmaunion.app.cmdriver.databinding.FragmentNewsBinding;

import com.chenmaunion.app.cmdriver.ui.activity.MainActivity;
import com.chenmaunion.app.cmdriver.viewmodel.DBModel_News;
import com.kaidongyuan.app.basemodule.ui.fragment.BaseLifecyclePrintFragment;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/7.
 */
public class NewsFragment extends BaseLifecyclePrintFragment  {
    public DBNotifyAdapter dbNotifyAdapter;
    private View parant;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentNewsBinding newsBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_news,container,false);
        LinearLayoutManager manager=new LinearLayoutManager(this.getMContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        newsBinding.recyclerviewDatabing.setLayoutManager(manager);
        dbNotifyAdapter=new DBNotifyAdapter();
        newsBinding.recyclerviewDatabing.setAdapter(dbNotifyAdapter);
        SlidingTitleView titleView=newsBinding.slidingtilteFragmentNews;
        titleView.setMode(SlidingTitleView.MODE_NULL);
        titleView.setText(getString(R.string.news_page));
        newsBinding.setModel(new DBModel_News((MainActivity) getActivity(),this));
        return newsBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
