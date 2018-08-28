package com.chenmaunion.app.cmdriver.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.bean.User;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.utils.nomalutils.Des3;
import com.kaidongyuan.app.basemodule.utils.nomalutils.MD5Util;
import com.kaidongyuan.app.basemodule.widget.MLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WelcomeActivity extends BaseActivity implements AsyncHttpCallback {
    private ImageView iv_cm;
    private AnimationSet as;
    private final String LOGIN = "login";
    private DriverAsyncHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initview();


    }

    @Override
    public void onResume() {
        super.onResume();
        //把动画放在onResume()中有利于界面加载流畅性
        startAnimation();
        initEvent();
    }

    private void initview() {
        iv_cm= (ImageView) findViewById(R.id.welcomeActivty_cm_logo);
        MyApplication.IS_LOGIN=false;
    }

    /**
     * 开始播放动画：旋转，缩放，渐变
     */
    private void startAnimation() {
        // false 代表动画集中每种动画都采用各自的动画插入器(数学函数)
        as = new AnimationSet(false);
        // 旋转动画,锚点
        RotateAnimation ra = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);// 设置锚点为图片的中心点
        // 设置动画播放时间
        ra.setDuration(1500);
        // 动画播放完之后，停留在当前状态
        ra.setFillAfter(true);
        // 添加到动画集
        as.addAnimation(ra);
        // 渐变动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);// 由完全透明到不透明
        // 设置动画播放时间
        aa.setDuration(1500);
        // 动画播放完之后，停留在当前状态
        aa.setFillAfter(true);
        // 添加到动画集
        as.addAnimation(aa);
        // 缩放动画
        ScaleAnimation sa = new ScaleAnimation(0.1f, 1f, 0.1f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        // 设置动画播放时间
        sa.setDuration(1500);
        sa.setFillAfter(true);// 动画播放完之后，停留在当前状态
        // 添加到动画集
        as.addAnimation(sa);
        // 播放动画
        iv_cm.startAnimation(as);
    }

    private void initEvent() {
        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (SharedPreferencesUtils.getValueByName(Constants.BasicInfo, Constants.IsUsedApp, 2)){
                    if (SharedPreferencesUtils.getUserId()!=null
                            && SharedPreferencesUtils.getValueByName(Constants.BasicInfo, Constants.UserName, 0)!=null
                            &&SharedPreferencesUtils.getValueByName(Constants.BasicInfo,Constants.UserPWD,0)!=null){
                        client=new DriverAsyncHttpClient(WelcomeActivity.this,WelcomeActivity.this);
                        try{
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("UserCode", (String) SharedPreferencesUtils.getValueByName(Constants.BasicInfo, Constants.UserCode, 0));
                            String strpwd=SharedPreferencesUtils.getValueByName(Constants.BasicInfo,Constants.UserPWD,0);
                            params.put("PassWord", MD5Util.getMD5String(Des3.decode(strpwd)));
                            params.put("strLicense", "");
                            client.setShowToast(false);
                            client.sendRequest(Constants.URL.Login,params,LOGIN);
                            return;
                        }catch (Exception e){
                            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                        }

                    }else {
                        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    }
                }else {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                 //   startActivity(new Intent(WelcomeActivity.this,GuideActivity.class));

                }
                WelcomeActivity.this.finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            mStartActivity(LoginActivity.class);
            finish();
            return;
        }else if (request_tag.equals(LOGIN)){
            JSONObject jo= JSON.parseObject(msg);
            try {
                List<User> users= JSON.parseArray(jo.getString("result"),User.class);
                MLog.e("user.size():" + users.size() + "IDX:" + users.get(0).getIDX());
                if (users.size() > 0) {
                    users.get(0).setUSERCODE((String) SharedPreferencesUtils.getValueByName(Constants.BasicInfo, Constants.UserCode, 0));
                    //MLog.e("User:"+user.get(0).getIDX());
                    MyApplication.getInstance().setUser(users.get(0));
                    MyApplication.IS_LOGIN = true;
                }

                SharedPreferencesUtils.saveUserId(MyApplication.getInstance().getUser().getIDX());
//                    MyApplication.getInstance().finishActivity(AainAcitivity.class);
//                    mActivtiy.mStartActivity(AainAcitivity.class);
                MyApplication.getInstance().finishActivity(MainActivity.class);
                mStartActivity(MainActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
