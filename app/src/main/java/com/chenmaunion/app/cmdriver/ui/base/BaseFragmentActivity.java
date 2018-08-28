package com.chenmaunion.app.cmdriver.ui.base;

import android.os.Bundle;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.kaidongyuan.app.basemodule.ui.fragmentactivity.BaseInputFragmentActivity;



/**
 * baseActivity 封装一些通用方法 所有的activity继承自该Activity
 *
 * @author ke
 * 
 */
public abstract class BaseFragmentActivity extends BaseInputFragmentActivity {
	private final String prefName = "account";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	//	requestWindowFeature(Window.FEATURE_NO_TITLE);
		MyApplication.getInstance().addActivityToManager(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	/*private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			unregisterReceiver(this);
			((Activity) context).finish();
		}
	};*/

	public void onResume() {
		super.onResume();
//		StatService.onResume(this);//百度统计页面
//		IntentFilter filter = new IntentFilter();
//		filter.addAction("exit");
//		registerReceiver(this.broadcastReceiver, filter);
	}

	@Override
	public void onPause() {
		super.onPause();
//		StatService.onPause(this);//百度统计页面
	}


	@Override
	public void finish() {
		super.finish();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		MyApplication.getInstance().removeActivityFromManager(this);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//		unregisterReceiver(broadcastReceiver);
	}



}
