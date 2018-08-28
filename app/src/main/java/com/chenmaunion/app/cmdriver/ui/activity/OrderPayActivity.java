package com.chenmaunion.app.cmdriver.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.bean.order.LocationContineTime;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.chenmaunion.app.cmdriver.utils.baiduMapUtils.LocationFileHelper;
import com.chenmaunion.app.cmdriver.utils.baiduMapUtils.UploadCacheLocationUtil;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.utils.nomalutils.AnimationUtil;
import com.kaidongyuan.app.basemodule.utils.nomalutils.BitmapUtil;
import com.kaidongyuan.app.basemodule.utils.nomalutils.FileUtil;
import com.kaidongyuan.app.basemodule.widget.AutographView;
import com.kaidongyuan.app.basemodule.widget.MLog;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2016/10/19.
 */
public class OrderPayActivity extends BaseActivity implements AsyncHttpCallback,View.OnClickListener {
    private SlidingTitleView mslidingTitleView;
    private String orderIDX;
    private String orderDriverPay;//运输订单状态 “已到达”，“已交付”，“未交付”
    private final String Tag_Pay = "Tag_Pay";//交付订单Tag
   // private final String Tag_isPayed="Tag_IsPayedOrder";
    private ImageView imageView1,imageView2;
    private Button button1,button2,cancelbutton,submitbutton,autographbutton,albumbutton,camerabutton;
    private AutographView mautographView;
   // private AlertDialog mchoicealertDialog;
    private DriverAsyncHttpClient mClient;
    /** 保存图片和签名的文件夹 */
    private final String mCacheFileName = "cmdriver";
    /** 签名保存的文件名 */
    private final String autographFileName="autograph.jpg";
    /**　签名保存的绝对路径　*/
       private  String autographFilePath;
    /** 照片1保存的文件名 */
    private final String mPictureFileName = "orderImage.jpg";
    /**　照片1调用系统相机是的请求码　*/
    private final int SystemCapture = 10;
    /**　照片保存的绝对路径　*/
    private String mPictureFilePath;
    /** 照片2保存的文件名 */
    private final String mPictureFileName2 = "orderImage2.jpg";
    /**照片2调用系统相机是的请求码2　*/
    private final int SystemCapture2 = 11;
    /**　照片2保存的绝对路径　*/
    private String mPictureFilePath2;
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
    private final int mPictureQuity = 60;
    private static boolean IsCamera=false;
    private Snackbar choicsSnackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderpay);
        initview();
    }

//    @Override
//    public void initWindow() {
//
//    }

    private void initview() {
        mslidingTitleView= (SlidingTitleView) findViewById(R.id.orderpay_titelview);
        mslidingTitleView.setText("订单交付");
        mslidingTitleView.setMode(SlidingTitleView.MODE_BACK);
        orderIDX=getIntent().getStringExtra("order_id");
        if (orderIDX==null||orderIDX.length()<=0){
            showToastMsg("订单信息丢失，请重新加载");
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finish();
        }
        imageView1= (ImageView) findViewById(R.id.imageView_picture);
        imageView1.setOnClickListener(this);
        imageView2= (ImageView) findViewById(R.id.imageView_picture2);
        imageView2.setOnClickListener(this);
        button1= (Button) findViewById(R.id.button_addPicture);
        button1.setOnClickListener(this);
        button2= (Button) findViewById(R.id.button_addPicture2);
        button2.setOnClickListener(this);
        cancelbutton= (Button) findViewById(R.id.button_cancel);
        cancelbutton.setOnClickListener(this);
        submitbutton= (Button) findViewById(R.id.button_submit);
        submitbutton.setOnClickListener(this);
        mClient=new DriverAsyncHttpClient(this,this);
        mautographView= (AutographView) findViewById(R.id.autographView_write);
        autographbutton= (Button) findViewById(R.id.button_againAutograph);
        autographbutton.setOnClickListener(this);

    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")&&request_tag.equals(Tag_Pay)){
            //muploadCachlocation();
           MLog.e("postSuccessMsg error");
            finish();
        }else if (request_tag.equals(Tag_Pay)){
            try {
                JSONObject jo= JSON.parseObject(msg);
                int type=jo.getInteger("type");
                if (type==1){
                    showToastMsg("提交成功");
                    ShipmentInfoActivity.isrefresh=true;
                    MyApplication.getInstance().finishActivity(OrderDetailActivity.class);
                    finish();
                }else {
                    showToastMsg(jo.getString("msg"));
                }
            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }
    }

    private void muploadCachlocation() {
        String mFileName=getApplicationContext().getFilesDir().getAbsolutePath();
        List<LocationContineTime> locationList = LocationFileHelper.readFromFile2(mFileName);
        String mUserId = SharedPreferencesUtils.getUserId();
        RequestQueue mRequestQueue=Volley.newRequestQueue(getMContext());
        UploadCacheLocationUtil.uploadCacheLocation(getMContext(), mRequestQueue, mFileName, mUserId, locationList);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView_picture:
                if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
                    choicsSnackbar.dismiss();
                    return;
                }
                if (mPictureFilePath==null||mPictureFilePath.length()<=0){
                    addPicture(mPictureFileName, SystemCapture);
                }else {
                    Intent intent=new Intent(this,PhotoActivity.class);
                    intent.putExtra("picturePath",mPictureFilePath);
                    startActivity(intent);
                }
                break;
            case R.id.imageView_picture2:
                if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
                    choicsSnackbar.dismiss();
                    return;
                }
                if (mPictureFilePath2==null||mPictureFilePath2.length()<=0){
                    addPicture(mPictureFileName2,SystemCapture2);
                }else {
                    Intent intent=new Intent(this,PhotoActivity.class);
                    intent.putExtra("picturePath",mPictureFilePath2);
                    startActivity(intent);
                }
                break;
            case R.id.button_addPicture:
                if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
                    choicsSnackbar.dismiss();
                    return;
                }
                addPicture(mPictureFileName,SystemCapture);
                break;
            case R.id.button_addPicture2:
                if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
                    choicsSnackbar.dismiss();
                    return;
                }
                addPicture(mPictureFileName2,SystemCapture2);
                break;
            case R.id.button_againAutograph:
                mautographView.clear();
                break;
            case R.id.button_cancel:
                    finish();
                break;
            case R.id.button_submit:
                if (saveAutographInLocaFile()){
                    updataOrderWithPicture();
                }
                break;
        }
    }

    private void updataOrderWithPicture() {
        if ((mPictureFilePath!=null&&mPictureFilePath.length()>0)
                &&(mPictureFilePath2!=null&&mPictureFilePath2.length()>0)
                &&(autographFilePath!=null&&autographFilePath.length()>0)){
            Bitmap pictureBitmap= BitmapUtil.resizeImage(mPictureFilePath,mBitmapWidth);
            Bitmap pictureBitmap2= BitmapUtil.resizeImage(mPictureFilePath2,mBitmapWidth);
            Bitmap autographBitmap= BitmapUtil.resizeImage(autographFilePath,mBitmapWidth);
            if (pictureBitmap!=null&&pictureBitmap2!=null&&autographBitmap!=null){
                try {
                    String strpicture= BitmapUtil.changeBitmapToString(pictureBitmap);
                    String strpicture2= BitmapUtil.changeBitmapToString(pictureBitmap2);
                    String strautograph= BitmapUtil.changeBitmapToString(autographBitmap);
            //        String strorderBackNum=et_orderBackNum.getText().toString();
                    Map<String,String> params=new HashMap<>();
                    params.put("OrderId",orderIDX);
                    params.put("strLicense","");
                    params.put("PictureFile1",strpicture);
                    params.put("PictureFile2",strpicture2);
                    params.put("AutographFile",strautograph);
                  //  params.put("strDeliveNo",strorderBackNum);
                    mClient.sendRequest(Constants.URL.DriverPay,params,Tag_Pay);
                }catch (Exception e){
                    showToastMsg("请正确填写回单单号~");
                    e.printStackTrace();
                }
            }else {
                showToastMsg("请将照片和签名上传完整!");
            }
        }else {
            showToastMsg("请将照片和签名上传完整!");
        }
    }

    private boolean saveAutographInLocaFile() {
        Bitmap bitmap=mautographView.getPaintBitmap();
        if (bitmap!=null){
            bitmap= BitmapUtil.resizeImage(bitmap,mBitmapWidth);
            autographFilePath= BitmapUtil.writeBimapToFile(bitmap,autographFileName,mCacheFileName,mPictureQuity);
            if (autographFilePath==null||autographFilePath.length()<=0){
                showToast("签字失败！",Toast.LENGTH_SHORT);
                return false;
            }else {
                return true;
            }
        }else {
            showToastMsg("签字失败！");
            return false;
        }
    }

    private void addPicture(String mPictureFileName, int systemCapture) {
        mTempPictureFileName=mPictureFileName;
        mTempRequestCode=systemCapture;
        showUpdataChoice();
    }

    private void showUpdataChoice() {
        choicsSnackbar = Snackbar.make(findViewById(R.id.activity_orderpay),"相册选取", Snackbar.LENGTH_INDEFINITE);
        View v= choicsSnackbar.getView();
        v.setBackgroundColor(getResources().getColor(R.color.details_text));
        final TextView tv_snackbar= (TextView) v.findViewById(R.id.snackbar_text);
        // tv_snackbar.setPadding(50,50,50,50);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){
            switch (requestCode){
                case SystemCapture:
                    Bitmap bitmap0= BitmapUtil.resizeImage(mTempPictureFilePath,mBitmapWidth);
                    getPictureResultHandle(imageView1, button1, bitmap0, mPictureFileName);
                    break;
                case SystemCapture*2:
                    Uri uri=data.getData();
                    Bitmap bitmap1= BitmapUtil.getBitmap(getApplicationContext(),uri,mBitmapWidth);
                    getPictureResultHandle(imageView1,button1,bitmap1,mPictureFileName);
                    break;
                case SystemCapture2:
                    Bitmap bitmap2= BitmapUtil.resizeImage(mTempPictureFilePath,mBitmapWidth);
                    getPictureResultHandle(imageView2,button2,bitmap2,mPictureFileName2);
                    break;
                case SystemCapture2*2:
                    Uri uri1=data.getData();
                    Bitmap bitmap3= BitmapUtil.getBitmap(getApplicationContext(),uri1,mBitmapWidth);
                    getPictureResultHandle(imageView2,button2,bitmap3,mPictureFileName2);
                    break;
                default:
                    showToast("图片传输失败，请重新取图", Toast.LENGTH_LONG);
                    break;
            }

        }

    }



    @Override
    public void onBackPressed() {
        if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
            choicsSnackbar.dismiss();
        }else {
            super.onBackPressed();
        }
    }

    private void getPictureResultHandle(ImageView imageView1, Button button1, Bitmap bitmap, String mPictureFileName) {
        if (bitmap!=null){
            imageView1.setImageBitmap(bitmap);
            ScaleAnimation sa= AnimationUtil.createScaleAnimation(0.3f,1f,1000,true);
            imageView1.startAnimation(sa);
            button1.setText("重新上传");
            String picturePath= BitmapUtil.writeBimapToFile(bitmap,mPictureFileName,mCacheFileName,mPictureQuity);
            if (mTempRequestCode==SystemCapture||mTempRequestCode==SystemCapture*2){
                mPictureFilePath=picturePath;
            }else if (mTempRequestCode==SystemCapture2||mTempRequestCode==SystemCapture2*2){
                mPictureFilePath2=picturePath;
            }
        }
    }




}
