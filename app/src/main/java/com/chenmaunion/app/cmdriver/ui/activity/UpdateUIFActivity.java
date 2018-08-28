package com.chenmaunion.app.cmdriver.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.UserInfo;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.ui.fragment.MineFragment;
import com.chenmaunion.app.cmdriver.utils.GlideCircleTransform;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.utils.nomalutils.BitmapUtil;
import com.kaidongyuan.app.basemodule.utils.nomalutils.FileUtil;
import com.kaidongyuan.app.basemodule.widget.MLog;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 更改个人信息
 */
public class UpdateUIFActivity extends BaseActivity implements AsyncHttpCallback {

	private ImageView iv_user_icon;
	//private EditText userNameEditText;
	private EditText userCodeEditText;
	private EditText addressEditText;
	private DriverAsyncHttpClient mClient;
	private final String TAG_UPDATE_UIF = "update_userinfo";
	private String strAddress;
	private String strAvatar;//url地址信息式的图像
	private String strPicture;//Bitmap转换成String类型的图像
	private String strUserCode;
	private String strUserName;
	/** 保存图片和签名的文件夹 */
	private final String mCacheFileName = "cmdriver";
	/** 照片1保存的文件名 */
	private final String mPictureFileName = "mineImage.jpg";
	/**　照片1调用系统相机是的请求码　*/
	private final int SystemCapture = 10;
	/**　照片保存的绝对路径　*/
	private String mPictureFilePath;
	/** 储存添加照片是的临时文件名 */
	private String mTempPictureFileName;
	/** 储存添加照片时的临时请求码 */
	private int mTempRequestCode;
	/** 储存添加照片时的临时文件路径 */
	private String mTempPictureFilePath;
	/** 储存添加照片时的临时Bitmap */
	private Bitmap mTempbitmap;
	/**　签名和照片文件宽度 单位（px）　*/
	private final int mBitmapWidth = 400;
	/** 签名和照片的质量 */
	private final int mPictureQuity = 100;
	private Snackbar choicsSnackbar;
	private final int mRequestcode=1003;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updata_uif);
		SlidingTitleView titleView;
		titleView = (SlidingTitleView) findViewById(R.id.slidingtilte_activity_update_uif);
		titleView.setText("更改个人信息");
		titleView.setMode(SlidingTitleView.MODE_BACK);
		iv_user_icon= (ImageView) this.findViewById(R.id.iv_user_icon);
		userCodeEditText= (EditText) this.findViewById(R.id.ed_user_code);
		addressEditText= (EditText) this.findViewById(R.id.ed_ADDRESS);
		addressEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean b) {
				if (b){
					Intent intent=new Intent(UpdateUIFActivity.this,ChooseProvinceActivity.class);
					startActivityForResult(intent,mRequestcode);
				}
				return;
			}
		});
		mClient = new DriverAsyncHttpClient(this, this);
		iv_user_icon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
					choicsSnackbar.dismiss();
					return;
				}else {
					addPicture(mPictureFileName, SystemCapture);
				}
			}
		});
		initData();
		findViewById(R.id.btn_update_uif).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateUserInfo();
			}
		});

	}

	private void initData() {
		Intent intent0=getIntent();
//		if (intent0.hasExtra("USER_NAME")){
//			strUserName=intent0.getStringExtra("USER_NAME");
//
//		}
		if (intent0.hasExtra("USER_CODE")){
			strUserCode=intent0.getStringExtra("USER_CODE");
			userCodeEditText.setText(strUserCode);
		}else strUserCode="";
		if (intent0.hasExtra("ADDRESS")){
			strAddress=intent0.getStringExtra("ADDRESS");
			addressEditText.setText(strAddress);
		}else strAddress="";
		if (intent0.hasExtra("Avatar")){
			strAvatar=intent0.getStringExtra("Avatar");
			Glide.with(UpdateUIFActivity.this).load(Constants.URL.Load_Url+"Uploadfile/"+ strAvatar).crossFade()
					.diskCacheStrategy(DiskCacheStrategy.RESULT)
					.transform(new GlideCircleTransform(getMContext())).into(iv_user_icon);
		}else strAvatar="";
		strPicture="";
	}

	private void addPicture(String mPictureFileName, int systemCapture) {
		mTempPictureFileName=mPictureFileName;
		mTempRequestCode=systemCapture;
		showUpdataChoice();
	}

	private void showUpdataChoice() {
		choicsSnackbar = Snackbar.make(findViewById(R.id.ll_activity_updateuif),"相册选取", Snackbar.LENGTH_INDEFINITE);
		View v= choicsSnackbar.getView();
		v.setBackgroundColor(getResources().getColor(R.color.details_text));
		final TextView tv_snackbar= (TextView) v.findViewById(R.id.snackbar_text);
		tv_snackbar.setGravity(Gravity.CENTER);
		tv_snackbar.setTextColor(getResources().getColor(R.color.white));
		tv_snackbar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//从相册获取照片上传
				if (choicsSnackbar !=null) {
					choicsSnackbar.dismiss();
				}
				mTempRequestCode *= 2;
				Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, mTempRequestCode);
			}
		});
		choicsSnackbar.setAction("拍照上传", new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				File dirFile = FileUtil.getCacheDirFile(mCacheFileName);
				if (dirFile == null) {
					showToastMsg("请先授权读写sd卡权限~");
					return;
				}
				File pictureFile = new File(dirFile, mTempPictureFileName);
				mTempPictureFilePath = pictureFile.getAbsolutePath();
				Uri pictureUri = Uri.fromFile(pictureFile);
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
				startActivityForResult(intent, mTempRequestCode);
			}
		}).show();
	}




	public void updateUserInfo() {

		UserInfo userinfo=new UserInfo();
		userinfo.setUserId(SharedPreferencesUtils.getUserId());
		strAddress = addressEditText.getText().toString().trim();
		strUserCode = userCodeEditText.getText().toString().trim();
		userinfo.setADDRESS(strAddress);
		userinfo.setUSER_CODE(strUserCode);
		userinfo.setAvatar(strPicture);
		Map<String,String> params=new HashMap<>();
		params.put("UserInfo", JSON.toJSONString(userinfo));
		params.put("strLicense","");
		mClient.sendRequest(Constants.URL.ModifyUserInfo,params,TAG_UPDATE_UIF);
	}
	private void getPictureResultHandle(ImageView imageView1, Bitmap bitmap, String mPictureFileName) {
		if (bitmap!=null){
			Bitmap squrebitmap= BitmapUtil.resizeSquareImage(bitmap,400);
			byte[] bytes= BitmapUtil.changeBitmapToBytes(squrebitmap);
			Glide.with(UpdateUIFActivity.this).load(bytes).diskCacheStrategy(DiskCacheStrategy.NONE)
					.crossFade().transform(new GlideCircleTransform(UpdateUIFActivity.this)).into(imageView1);
			MLog.w("squrebitmap:"+squrebitmap.getWidth()+"|"+squrebitmap.getHeight());
			strPicture = BitmapUtil.changeBitmapToString(squrebitmap);
		}
	}
	@Override
	protected void onDestroy() {
		mClient.cancleRequest(TAG_UPDATE_UIF);
		super.onDestroy();
	}

	@Override
	public void postSuccessMsg(String msg, String request_tag) {
		if (msg.equals("error"))return;
		if (request_tag.equals(TAG_UPDATE_UIF)){
			JSONObject jo= JSON.parseObject(msg);
			//更改成功则销毁updateUIFActivity
			if (jo.getString("type").equals("1")){
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				InformationActivity.IsRefresh=true;
				MineFragment.IsRefresh=true;
				UpdateUIFActivity.this.finish();
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode== Activity.RESULT_OK){
			switch (requestCode){
				case SystemCapture:
					Bitmap bitmap0= BitmapUtil.resizeImage(mTempPictureFilePath,mBitmapWidth);
					getPictureResultHandle(iv_user_icon,bitmap0, mPictureFileName);
					break;
				case SystemCapture*2:
					Uri uri=data.getData();
					Bitmap bitmap1= BitmapUtil.getBitmap(getMContext(),uri,mBitmapWidth);
					getPictureResultHandle(iv_user_icon,bitmap1,mPictureFileName);
					break;
				case mRequestcode:
					String pv=data.getStringExtra("province");
					String ct=data.getStringExtra("city");
					addressEditText.setText(pv.trim()+ct.trim());
					break;
				default:
					break;
			}
		}
	}
}
