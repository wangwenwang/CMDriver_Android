package com.chenmaunion.app.cmdriver.ui.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;


/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/5/18.
 */
public class NormalProblemsActivity extends BaseActivity {
    private final String DEFAULT_URL = "http://www.chengma.co/common.aspx";
    private WebView webView;
    private SlidingTitleView titleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinstruction);//fragment_mine下的暂时各个简单web页面共用同一套布局
        titleView= (SlidingTitleView) findViewById(R.id.slidingtilte_activity_opinstruction);
        titleView.setMode(SlidingTitleView.MODE_BACK);
        titleView.setText(getString(R.string.normal_problems));
        webView= (WebView) findViewById(R.id.webview_opinstruction);
        WebSettings webset=webView.getSettings();
        webset.setJavaScriptEnabled(true);
        webset.setSupportZoom(true);
        webset.setBuiltInZoomControls(true);
        webset.setDisplayZoomControls(true);
//        webset.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webset.setUseWideViewPort(true);
        webset.setLoadWithOverviewMode(true);
        webView.loadUrl(DEFAULT_URL);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.onResume();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
