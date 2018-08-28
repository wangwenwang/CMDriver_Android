package com.chenmaunion.app.cmdriver.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Process;


import com.baidu.mapapi.SDKInitializer;
import com.chenmaunion.app.cmdriver.bean.User;
import com.chenmaunion.app.cmdriver.serviceAndReceiver.DaemonReceiver;
import com.chenmaunion.app.cmdriver.serviceAndReceiver.DaemonService;
import com.chenmaunion.app.cmdriver.serviceAndReceiver.TrackReceiver;
import com.chenmaunion.app.cmdriver.serviceAndReceiver.TrackService;
import com.chenmaunion.app.cmdriver.utils.ExceptionUtil;
import com.kaidongyuan.app.basemodule.utils.nomalutils.DensityUtil;
import com.kaidongyuan.app.basemodule.widget.MLog;
import com.marswin89.marsdaemon.DaemonApplication;
import com.marswin89.marsdaemon.DaemonConfigurations;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * Created by Administrator on 2016/5/16.
 * Application
 */
public class MyApplication extends DaemonApplication {

    /**
     * 发布时改为 true 控制日志输出
     */
    public static final boolean isRelas = true;

//    /**
//     * 日志输出标记
//     */
//    private final String mLoggerTag = "ZJL";

    /**
     * MyApplication 实例
     */
    private static MyApplication instance;

    /**
     * MyApplication 的上下文
     */
    private static Context applicationContext;

    /**
     * 管理 Activity 的集合
     */
    private Stack<Activity> mActivityManagerList;

    /**
     * 记录用户是否为登录状态
     */
    public static boolean IS_LOGIN = false;

    /**
     * 用户信息实体类
     */
    private User user;

//    /**
//     * 用户业务类型实体类
//     */
//    private Business business;

    /**
     * app 资源
     */
    private static Resources mRes;
    private OkHttpClient okHttpClient;
    public OkHttpUtils okHttpUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            initCrashHandler();
         //   SDKInitializer.initialize(this);// 初始化百度地图
         //   initLoggerUtil();
            initData();
            createOkhttpUtils(15*1000L,15*1000L,20*1000L);
        } catch (Exception e) {
            ExceptionUtil.handlerException(e);
        }
    }

    /**
     * 捕获未捕获的异常处理
     */
    private void initCrashHandler() {
        try {
            Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable ex) {
                    ExceptionUtil.handleUncaughtException(ex);
                }
            };
            Thread.setDefaultUncaughtExceptionHandler(handler);
        } catch (Exception e) {
            ExceptionUtil.handlerException(e);
        }
    }

    /**
     *管理注册双进程守护功能
     *created at 2016/7/1 14:16
     *
     */
    @Override
    protected DaemonConfigurations getDaemonConfigurations() {
        DaemonConfigurations.DaemonConfiguration daemonConfiguration1=new DaemonConfigurations.DaemonConfiguration(
                "com.chenmaunion.app.cmdriver:pushservice",
                TrackService.class.getCanonicalName(),
                TrackReceiver.class.getCanonicalName()
        );
        DaemonConfigurations.DaemonConfiguration daemonConfiguration2=new DaemonConfigurations.DaemonConfiguration(
                "com.chenmaunion.app.cmdriver:daemon",
                DaemonService.class.getCanonicalName(),
                DaemonReceiver.class.getCanonicalName()
        );
        DaemonConfigurations.DaemonListener listener = new MyDaemonListener();
        return new DaemonConfigurations(daemonConfiguration1,daemonConfiguration2,listener);
    }


    /**
     * 初始化数据
     */
    private void initData() {
        try {
            MyApplication.instance = this;
            MyApplication.applicationContext = this;
            this.mActivityManagerList = new Stack<>();
            DensityUtil.setContext(getBaseContext());
            SDKInitializer.initialize(getApplicationContext());
        } catch (Exception e) {
            ExceptionUtil.handlerException(e);
        }
    }

    /**
     *@auther: Tom
     *created at 2016/6/6 11:15
     * 初始化OkHttpUtils对象
     */
    private OkHttpUtils createOkhttpUtils(long connectTimeOut, long readTimeOut, long writeTimeOuts) {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(connectTimeOut, TimeUnit.MILLISECONDS).readTimeout(readTimeOut,TimeUnit.MILLISECONDS).writeTimeout(writeTimeOuts,TimeUnit.MILLISECONDS).build();
        okHttpUtils = OkHttpUtils.initClient(okHttpClient);
        return okHttpUtils;
    }
//    /**
//     * 初始化日志输出工具
//     */
//    private void initLoggerUtil() {
//        try {
//            Settings init = Logger.init(mLoggerTag)// default PRETTYLOGGER or use just init()
//                    .methodCount(0)                 // default 2
//                    .logLevel(LogLevel.FULL)        // default LogLevel.FULL
//                    .methodOffset(0)                // default 0
//                    .logTool(new AndroidLogTool()); // custom log tool, optional
//            if (isRelas) {
//                init.logLevel(LogLevel.NONE)
//                        .hideThreadInfo();// default shown
//            }
//        } catch (Exception e) {
//            ExceptionUtil.handlerException(e);
//        }
//    }

    /**
     * 将 Activity 添加到管理集合中
     *
     * @param activity 需要添加的集合
     */
    public void addActivityToManager(Activity activity) {
        try {
            if (activity == null) {
                return;
            }
            mActivityManagerList.add(activity);
            MLog.w("MyApplication.exit.mActivitysManagerSize:" + mActivityManagerList.size());
        } catch (Exception e) {
            ExceptionUtil.handlerException(e);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Iterator iterator = mActivityManagerList.iterator(); iterator.hasNext(); ) {
            Activity activity = (Activity) iterator.next();
            if (activity.getClass().equals(cls)) {
                iterator.remove();
                activity.finish();
            }
        }
    }

    /**
     * 将 Activity 从管理集合中移除
     *
     * @param activity 要移除的 Activity
     */
    public void removeActivityFromManager(Activity activity) {
        try {
            if (activity == null) {
                return;
            }
            mActivityManagerList.remove(activity);
            MLog.w("MyApplication.exit.mActivitysManagerSize:" + mActivityManagerList.size());
        } catch (Exception e) {
            ExceptionUtil.handlerException(e);
        }
    }

    /**
     *退出程序
     * @param iskill 是否关闭主进程
     */
    public void exit(boolean iskill) {
        try {
            Activity activity;
            for (int i = mActivityManagerList.size() - 1; i >= 0; i--) {
                try {
                    activity = mActivityManagerList.get(i);
                    if (activity != null) {
                        activity.finish();
                    }
                } catch (Exception e) {
                    ExceptionUtil.handlerException(e);
                }
            }
            mActivityManagerList.clear();
            MLog.w("MyApplication.exit.mActivitysManagerSize:" + mActivityManagerList.size());

           if (iskill){
               Process.killProcess(Process.myPid());
           }
            MLog.w("MyApplication.exit.退出程序");
        } catch (Exception e) {
            ExceptionUtil.handlerException(e);
        }
    }

    /**
     * 退出所有Activity
     *
     * @return 是否完全退出
     */
    public boolean exitAllActivity() {
        MLog.e("AppManager " + "应用程序退出exitAllActivity()");
        boolean isFinished = true;
        for (Activity act : mActivityManagerList) {
            if (act != null) {
                act.finish();
            }
        }
        for (Activity act : mActivityManagerList) {
            if (!act.isFinishing()) {
                isFinished = false;
            }
        }
        return isFinished;
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
//            finishAllMainActivity();
//            finishAllActivity();
//			ActivityManager activityMgr = (ActivityManager) context
//					.getSystemService(Context.ACTIVITY_SERVICE);
            if (exitAllActivity()) {
                // activityMgr.killBackgroundProcesses(context.getPackageName());
                System.exit(0);
            }
        } catch (Exception e) {
            MLog.e("AppManager " + "退出应用程序异常!");
            StackTraceElement[] stacks = e.getStackTrace();
            StringBuffer sb = new StringBuffer();
            for (StackTraceElement stack : stacks) {
                sb.append(stack.toString() + "\n");
            }
            MLog.e("AppManager " + sb.toString());
        }
    }


    /**
     * 获取运行的所有的 Activity
     *
     * @return 运行的 Activity 集合
     */
    public List<Activity> getActivitysList() {
        return mActivityManagerList;
    }

    /**
     * 获取 Application 的实例
     *
     * @return Application 实例
     */
    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 获取 Application 的上下文
     *
     * @return Application 的上下文
     */
    public static Context getAppContext() {
        return applicationContext;
    }

    /**
     * 获取登录用户信息实体类
     *
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * 设置用户登录信息实体类
     *
     * @param user User
     */
    public void setUser(User user) {
        this.user = user;
    }

//    /**
//     * 获取用户类型实体类
//     *
//     * @return Business
//     */
//    public Business getBusiness() {
//        return business;
//    }
//
//    /**
//     * 设置用户类型实体类
//     *
//     * @param business Business
//     */
//    public void setBusiness(Business business) {
//        this.business = business;
//    }

    /**
     * 获取 app 资源
     *
     * @return app 资源
     */
    public static Resources getmRes() {
        if (mRes == null) {
            mRes = MyApplication.getAppContext().getResources();
        }
        return mRes;
    }



    class MyDaemonListener implements DaemonConfigurations.DaemonListener{
        @Override
        public void onPersistentStart(Context context) {
            MLog.e("*****AppContext.MyDaemonListener.onPersistentStart()******");
        }

        @Override
        public void onDaemonAssistantStart(Context context) {
            MLog.e("*****AppContext.MyDaemonListener.onDaemonAssistantStart()******");
        }

        @Override
        public void onWatchDaemonDaed() {
            MLog.e("*****AppContext.MyDaemonListener.onWatchDaemonDaed()******");
            return;
        }
    }

}









