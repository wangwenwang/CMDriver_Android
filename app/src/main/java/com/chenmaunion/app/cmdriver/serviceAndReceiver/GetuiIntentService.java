package com.chenmaunion.app.cmdriver.serviceAndReceiver;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.kaidongyuan.app.basemodule.widget.MLog;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/1.
 */
public class GetuiIntentService extends GTIntentService {
    private RequestQueue mRequestQueue;
    public GetuiIntentService() {

    }
    @Override
    public void onReceiveServicePid(Context context, int i) {

    }

    @Override
    public void onReceiveClientId(Context context, String s) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + s);
        final String cid=s;
        if (mRequestQueue==null){
            RequestQueue mRequestQueue= Volley.newRequestQueue(context);
            StringRequest request=new StringRequest(Request.Method.POST, Constants.URL.SavaPushToken, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    com.alibaba.fastjson.JSONObject jo = JSON.parseObject(response);
                    int type = Integer.parseInt(jo.getString("type"));
                    if(type>=1) {
                        MLog.w("上传CID与UserID成功");
                    }else {
                        MLog.w("上传失败，后台返回值type："+type);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    MLog.w("SavaPushToken网络上传失败");
                    error.printStackTrace();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    String mUserId = SharedPreferencesUtils.getUserId();
                    params.put("UserId", mUserId);
                    params.put("CID", cid);
                    params.put("Token", "");
                    params.put("strLicense", "");
                    MLog.i("params:"+params.toString());
                    return params;
                }
            };
            request.setRetryPolicy(new DefaultRetryPolicy(30*1000, 1, 1.0f));  // 设置超时
            mRequestQueue.add(request);
        }
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage gtTransmitMessage) {

    }

    @Override
    public void onReceiveOnlineState(Context context, boolean b) {

    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage gtCmdMessage) {

    }
}
