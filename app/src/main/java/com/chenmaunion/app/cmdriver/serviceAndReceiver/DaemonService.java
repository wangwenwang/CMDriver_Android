package com.chenmaunion.app.cmdriver.serviceAndReceiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2016/12/27.
 */
public class DaemonService  extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //2016.08.19  START_NOT_STICKY改为START_STICKY
        return Service.START_STICKY;
    }
}
