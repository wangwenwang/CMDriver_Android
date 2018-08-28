package com.kaidongyuan.app.basemodule.utils.nomalutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/3/30.
 */
public class NatureFunctionUtils {
    /**
     * 拨打电话功能
     * @param context
     * @param strphoneNumber 电话号码 String
     */

    public static void CallPhone(Context context,String strphoneNumber){
        if (strphoneNumber!=null){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + strphoneNumber);
            intent.setData(data);
            context.startActivity(intent);
        }
    }
}
