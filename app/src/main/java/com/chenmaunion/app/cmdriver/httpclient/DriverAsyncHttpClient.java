package com.chenmaunion.app.cmdriver.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.interfaces.BaseActivityListener;
import com.kaidongyuan.app.basemodule.network.BaseAsyncHttpClient;

import java.io.UnsupportedEncodingException;

/**
 * Created by changwei on 2015/8/30
 */
public class DriverAsyncHttpClient extends BaseAsyncHttpClient {

    public DriverAsyncHttpClient(BaseActivityListener activityListener, AsyncHttpCallback callback) {
        super(activityListener, callback);
    }

    public void postMsg(String result, String tag){
        try {
            result = new String(result.getBytes(),"UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        if (result.equals("error")){
            callback.postSuccessMsg(result, tag);
            return;
        }
        JSONObject jo = null;
        try{
            jo = JSON.parseObject(result);
        }catch (JSONException e){
            result="error";
            callback.postSuccessMsg(result, tag);
            return;
        }
        if (jo.containsKey("msg") && !jo.getString("msg").trim().equals("")){
            //Toast 返回的result中msg信息
            if (need_show_toast)activityListener.showToastMsg(jo.getString("msg"));
        }
        if (jo.containsKey("type")){
            switch (jo.getIntValue("type")){
                case -3:
                    result="error";
                    break;
                case -2:
                    result = "error";
                    break;
                case -1:
                    result = "error";
                    break;
                case 0:
//                    result = "error";
                    break;
                case 1:
                    break;
            }
        }
        callback.postSuccessMsg(result, tag);
    }
}
