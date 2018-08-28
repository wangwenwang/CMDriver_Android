package com.chenmaunion.app.cmdriver.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.bean.fleet.TruckDetail;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.base.BaseActivity;
import com.chenmaunion.app.cmdriver.utils.ExceptionUtil;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.utils.nomalutils.AnimationUtil;
import com.kaidongyuan.app.basemodule.utils.nomalutils.Base64;
import com.kaidongyuan.app.basemodule.utils.nomalutils.BitmapUtil;
import com.kaidongyuan.app.basemodule.utils.nomalutils.DensityUtil;
import com.kaidongyuan.app.basemodule.utils.nomalutils.FileUtil;
import com.kaidongyuan.app.basemodule.utils.nomalutils.StringUtils;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/23.
 */
public class AddTruckActivity extends BaseActivity implements AsyncHttpCallback,View.OnClickListener{
    private ImageView imageView0,//行驶证正面
            imageView1,//行驶证反面
            imageView2;//车头牌照
    private TextView textView0,textView1,textView2;
    /** 保存图片和签名的文件夹 */
    private final String mCacheFileName = "cmdriver";
    /** 照片0保存的文件名 */
    private final String mPictureFileName0="truckImage0.jpg";
    /**　照片0调用系统相机是的请求码　*/
    private final int SystemCapture0 = 10;
    /**　照片0保存的绝对路径　*/
    private  String mPictureFilePath0;
    /** 照片1保存的文件名 */
    private final String mPictureFileName1 = "truckImage1.jpg";
    /**　照片1调用系统相机是的请求码　*/
    private final int SystemCapture1 = 11;
    /**　照片保存的绝对路径　*/
    private String mPictureFilePath1;
    /** 照片2保存的文件名 */
    private final String mPictureFileName2 = "truckImage2.jpg";
    /**照片2调用系统相机是的请求码2　*/
    private final int SystemCapture2 = 12;
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
    private Snackbar choicsSnackbar;
    /**
     * 选择报表的 Dialog
     */
    private Dialog mTruckNumberDialog;
    private EditText ed_truck_number,ed_truck_type,ed_truck_brand,ed_truck_size,ed_truck_color,
            ed_truck_maxwight,ed_truck_maxvolume;
    private Button btn_truck_commit;
    private SlidingTitleView titleView;
    private String fleetIdx;
    private final String TAG_AddVehicle="AddVehicle";
    private final String TAG_SearchVehicle="SearchVehicle";
    private DriverAsyncHttpClient mClient;
    private EditText ed_dialog_trucknumber;
    private TruckDetail truckDetail;
    private TextView management;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtruck);
        Intent intent=getIntent();
        if (intent.hasExtra("FleetIdx")){
            fleetIdx=intent.getStringExtra("FleetIdx");
        }else {
            finish();
        }
        mClient=new DriverAsyncHttpClient(this,this);
        initView();
        showTruckNumberDialog();
    }

    private void initView() {
        titleView= (SlidingTitleView) findViewById(R.id.slidingtilte_activity_addtruck);
        titleView.setMode(SlidingTitleView.MODE_BACK);
        titleView.setText("添加车辆");
        management=titleView.getManagment();
        management.setText("搜索");
        management.setOnClickListener(this);
        imageView0= (ImageView) findViewById(R.id.imageView_picture0);
        imageView0.setOnClickListener(this);
        imageView1= (ImageView) findViewById(R.id.imageView_picture1);
        imageView1.setOnClickListener(this);
        imageView2= (ImageView) findViewById(R.id.imageView_picture2);
        imageView2.setOnClickListener(this);
        textView0= (TextView) findViewById(R.id.tv_picture0);
        textView0.setOnClickListener(this);
        textView1= (TextView) findViewById(R.id.tv_picture1);
        textView1.setOnClickListener(this);
        textView2= (TextView) findViewById(R.id.tv_picture2);
        textView2.setOnClickListener(this);
        ed_truck_number= (EditText) findViewById(R.id.ed_truck_number);
        ed_truck_type= (EditText) findViewById(R.id.ed_truck_type);
        ed_truck_brand= (EditText) findViewById(R.id.ed_truck_brand);
        ed_truck_size= (EditText) findViewById(R.id.ed_truck_size);
        ed_truck_color= (EditText) findViewById(R.id.ed_truck_color);
        ed_truck_maxwight= (EditText) findViewById(R.id.ed_truck_maxwight);
        ed_truck_maxvolume= (EditText) findViewById(R.id.ed_truck_maxvolume);
        btn_truck_commit= (Button) findViewById(R.id.btn_truck_commit);
        btn_truck_commit.setOnClickListener(this);
    }

    private void commitTruck() {
        if ((mPictureFilePath0!=null&&mPictureFilePath0.length()>0)
                &&(mPictureFilePath2!=null&&mPictureFilePath2.length()>0)
                &&(mPictureFilePath1!=null&&mPictureFilePath1.length()>0)) {
            Bitmap pictureBitmap0 = BitmapUtil.resizeImage(mPictureFilePath0, mBitmapWidth);
            Bitmap pictureBitmap1 = BitmapUtil.resizeImage(mPictureFilePath1, mBitmapWidth);
            Bitmap pictureBitmap2 = BitmapUtil.resizeImage(mPictureFilePath2, mBitmapWidth);
            if (ed_truck_number.getText().toString().isEmpty()||ed_truck_size.getText().toString().isEmpty()||
                    ed_truck_type.getText().toString().isEmpty()||ed_truck_brand.getText().toString().isEmpty()||
                    ed_truck_color.getText().toString().isEmpty()||ed_truck_maxwight.getText().toString().isEmpty()||
                    ed_truck_maxvolume.getText().toString().isEmpty()){
                showToastMsg("请将车辆信息填写完整无误，再添加上传");
            }else if (fleetIdx.isEmpty()){
                showToastMsg("车队信息丢失，请返回车队列表重选");
                finish();
            }
            else if (pictureBitmap0==null&&pictureBitmap1==null&&pictureBitmap2==null){
                showToastMsg("请将行驶证正反面和车头照上传!");
            }else {
                Map<String,String> params=new HashMap<>();
                params.put("USER_IDX", SharedPreferencesUtils.getUserId());
                params.put("FLEET_ID",fleetIdx);
                params.put("PLATE_NUMBER",ed_truck_number.getText().toString().trim());
                params.put("VEHICLE_MODEL",ed_truck_type.getText().toString().trim());
                params.put("VEHICLE_SIZE",ed_truck_size.getText().toString().trim());
                params.put("BRAND_MODEL",ed_truck_brand.getText().toString().trim());
                params.put("VEHICLE_COLOR",ed_truck_color.getText().toString().trim());
                params.put("MAX_WEIGHT",ed_truck_maxwight.getText().toString().trim());
                params.put("MAX_VOLUME",ed_truck_maxvolume.getText().toString().trim());
                params.put("PictureFile1",BitmapUtil.changeBitmapToString(pictureBitmap0));
                params.put("PictureFile2",BitmapUtil.changeBitmapToString(pictureBitmap1));
                params.put("AutographFile",BitmapUtil.changeBitmapToString(pictureBitmap2));
                params.put("strLicense","");
                mClient.sendRequest(Constants.URL.AddVehicle,params,TAG_AddVehicle);
            }

        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_truck_commit: commitTruck();
                break;
            case R.id.imageView_picture0:
                if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
                    choicsSnackbar.dismiss();
                    return;
                }
                if (mPictureFilePath0==null||mPictureFilePath0.length()<=0){
                    addPicture(mPictureFileName0, SystemCapture0);
                }else {
                    Intent intent=new Intent(this,PhotoActivity.class);
                    intent.putExtra("picturePath",mPictureFilePath0);
                    startActivity(intent);
                }
                break;
            case R.id.imageView_picture1:
                if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
                    choicsSnackbar.dismiss();
                    return;
                }
                if (mPictureFilePath1==null||mPictureFilePath1.length()<=0){
                    addPicture(mPictureFileName1, SystemCapture1);
                }else {
                    Intent intent=new Intent(this,PhotoActivity.class);
                    intent.putExtra("picturePath",mPictureFilePath1);
                    startActivity(intent);
                }
                break;
            case R.id.imageView_picture2:
                if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
                    choicsSnackbar.dismiss();
                    return;
                }
                if (mPictureFilePath2==null||mPictureFilePath2.length()<=0){
                    addPicture(mPictureFileName2, SystemCapture2);
                }else {
                    Intent intent=new Intent(this,PhotoActivity.class);
                    intent.putExtra("picturePath",mPictureFilePath2);
                    startActivity(intent);
                }
                break;
            case R.id.tv_picture0:
                if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
                    choicsSnackbar.dismiss();
                    return;
                }
                addPicture(mPictureFileName0,SystemCapture0);
                break;
            case R.id.tv_picture1:
                if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
                    choicsSnackbar.dismiss();
                    return;
                }
                addPicture(mPictureFileName1,SystemCapture1);
                break;
            case R.id.tv_picture2:
                if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
                    choicsSnackbar.dismiss();
                    return;
                }
                addPicture(mPictureFileName2,SystemCapture2);
                break;
            case R.id.bt_dialog_cancel:
                mTruckNumberDialog.dismiss();
                break;
            case R.id.bt_dialog_comfit:
                if (ed_dialog_trucknumber.getText().toString().isEmpty()){
                    mTruckNumberDialog.dismiss();
                }else {
                    SearchVehicle(ed_dialog_trucknumber.getText().toString().trim());
                }
                break;
            case R.id.managment:
                showTruckNumberDialog();
                break;
            default:
                break;
        }
    }

    private void SearchVehicle(String trucknumber) {
        HashMap<String,String> params=new HashMap<>();
        params.put("strFleetIdx","");
        params.put("PLATE_NUMBER",trucknumber.trim());
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.SearchVehicle,params,TAG_SearchVehicle);
    }


    /**
     * 显示选择报表 Dialog
     */
    private void showTruckNumberDialog() {
        try {
            if (mTruckNumberDialog == null) {
                mTruckNumberDialog=new Dialog(AddTruckActivity.this);
                mTruckNumberDialog.setCanceledOnTouchOutside(false);
                mTruckNumberDialog.show();
                Window window = mTruckNumberDialog.getWindow();
                window.setContentView(R.layout.dialog_truck_number);
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.gravity = Gravity.CENTER;
                lp.width = DensityUtil.dip2px(DensityUtil.getWidth_dp());//宽高可设置具体大小
                lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                mTruckNumberDialog.getWindow().setAttributes(lp);
                ed_dialog_trucknumber = (EditText) window.findViewById(R.id.ed_Truck_Number);
                window.findViewById(R.id.bt_dialog_cancel).setOnClickListener(this);
                window.findViewById(R.id.bt_dialog_comfit).setOnClickListener(this);
            } else {
                mTruckNumberDialog.show();
            }
        } catch (Exception e) {
            ExceptionUtil.handlerException(e);
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


    private void addPicture(String mPictureFileName, int systemCapture) {
        mTempPictureFileName=mPictureFileName;
        mTempRequestCode=systemCapture;
        showUpdataChoice();
    }

    private void showUpdataChoice() {
        choicsSnackbar = Snackbar.make(findViewById(R.id.activity_addtruck),"相册选取", Snackbar.LENGTH_INDEFINITE);
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
                    showToastMsg("请先授权应用SD卡存储权限~");
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


    private void getPictureResultHandle(ImageView imageView, TextView textView, Bitmap bitmap, String mPictureFileName) {
        if (bitmap!=null){
            imageView.setImageBitmap(bitmap);
            ScaleAnimation sa= AnimationUtil.createScaleAnimation(0.3f,1f,1000,true);
            imageView.startAnimation(sa);
            textView.setText("重新上传");
            String picturePath= BitmapUtil.writeBimapToFile(bitmap,mPictureFileName,mCacheFileName,mPictureQuity);
            if (mTempRequestCode==SystemCapture0||mTempRequestCode==SystemCapture0*2){
                mPictureFilePath0=picturePath;
            }else if (mTempRequestCode==SystemCapture1||mTempRequestCode==SystemCapture1*2){
                mPictureFilePath1=picturePath;
            }else if (mTempRequestCode==SystemCapture2||mTempRequestCode==SystemCapture2*2){
                mPictureFilePath2=picturePath;
            }
        }
    }





    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            switch (request_tag){
                case TAG_AddVehicle:
                    showToastMsg("车辆添加失败，请稍后再试");
                    return;
                case TAG_SearchVehicle:
                    mTruckNumberDialog.dismiss();
                    break;
                case mPictureFileName0:
                    showToastMsg("行驶证正面图加载失败");
                    break;
                case mPictureFileName1:
                    showToastMsg("行驶证反面图加载失败");
                    break;
                case mPictureFileName2:
                    showToastMsg("车头照片加载失败");
                    break;
                default:
                    break;
            }

        }else if (request_tag.equals(TAG_AddVehicle)){
            try {
                JSONObject jo= JSON.parseObject(msg);
                int type=jo.getInteger("type");
                if (type==1){
                    showToastMsg("车辆成功添加");
                    TruckManageActivity.isrefresh=true;
                    finish();
                }else {
                    showToastMsg(jo.getString("msg"));
                }
            }catch (JSONException ex){
                ex.printStackTrace();
            }

        }else if (request_tag.equals(TAG_SearchVehicle)){
                mTruckNumberDialog.dismiss();
                ed_truck_number.setText(ed_dialog_trucknumber.getText().toString().trim());
            try {
                JSONObject jos= JSON.parseObject(msg);
                JSONArray ja=jos.getJSONArray("result");
                truckDetail = JSON.parseObject(ja.get(0).toString(),TruckDetail.class);
                showToastMsg("从车源库成功提取车辆信息~");
                ed_truck_number.setText(truckDetail.getPLATE_NUMBER());
                ed_truck_brand.setText(truckDetail.getBRAND_MODEL());
                ed_truck_type.setText(truckDetail.getVEHICLE_MODEL());
                ed_truck_size.setText(truckDetail.getVEHICLE_SIZE());
                ed_truck_color.setText(truckDetail.getVEHICLE_COLOR());
                ed_truck_maxwight.setText(truckDetail.getMAX_WEIGHT());
                ed_truck_maxvolume.setText(truckDetail.getMAX_VOLUME());
                if (truckDetail.getPictureFile1()!=null&&!truckDetail.getPictureFile1().isEmpty()){
                    mClient.sendFileRequest(Constants.URL.Load_Url+"Uploadfile/"+truckDetail.getPictureFile1(),mPictureFileName0);
                }


            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }else if (request_tag.equals(mPictureFileName0)){
            String bst=Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"truckImage0.jpg";
            Bitmap bm_pf0=BitmapUtil.resizeImage(bst
                    ,mBitmapWidth);
            mTempRequestCode=SystemCapture0;
            getPictureResultHandle(imageView0, textView0, bm_pf0, mPictureFileName0);

            if (truckDetail.getPictureFile2()!=null&&!truckDetail.getPictureFile2().isEmpty()){
                mClient.sendFileRequest(Constants.URL.Load_Url+"Uploadfile/"+truckDetail.getPictureFile2(),mPictureFileName1);
            }
        }else if (request_tag.equals(mPictureFileName1)){
            Bitmap bm_pf1=BitmapUtil.resizeImage(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"truckImage1.jpg"
                    ,mBitmapWidth);
            mTempRequestCode=SystemCapture1;
            getPictureResultHandle(imageView1, textView1, bm_pf1, mPictureFileName1);
            if (truckDetail.getAutographFile()!=null&&!truckDetail.getAutographFile().isEmpty()){
                mClient.sendFileRequest(Constants.URL.Load_Url+"Uploadfile/"+truckDetail.getAutographFile(),mPictureFileName2);
            }
        }else if (request_tag.equals(mPictureFileName2)){
            Bitmap bm_pf2=BitmapUtil.resizeImage(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"truckImage2.jpg"
                    ,mBitmapWidth);
            mTempRequestCode=SystemCapture2;
            getPictureResultHandle(imageView2, textView2, bm_pf2, mPictureFileName2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){
            switch (requestCode){
                case SystemCapture0:
                    Bitmap bitmap0= BitmapUtil.resizeImage(mTempPictureFilePath,mBitmapWidth);
                    getPictureResultHandle(imageView0, textView0, bitmap0, mPictureFileName0);
                    break;
                case SystemCapture0*2:
                    Uri uri0=data.getData();
                    Bitmap bitmap1= BitmapUtil.getBitmap(getApplicationContext(),uri0,mBitmapWidth);
                    getPictureResultHandle(imageView0,textView0,bitmap1,mPictureFileName0);
                    break;
                case SystemCapture1:
                    Bitmap bitmap2= BitmapUtil.resizeImage(mTempPictureFilePath,mBitmapWidth);
                    getPictureResultHandle(imageView1, textView1, bitmap2, mPictureFileName1);
                    break;
                case SystemCapture1*2:
                    Uri uri1=data.getData();
                    Bitmap bitmap3= BitmapUtil.getBitmap(getApplicationContext(),uri1,mBitmapWidth);
                    getPictureResultHandle(imageView1,textView1,bitmap3,mPictureFileName1);
                    break;
                case SystemCapture2:
                    Bitmap bitmap4= BitmapUtil.resizeImage(mTempPictureFilePath,mBitmapWidth);
                    getPictureResultHandle(imageView2,textView2,bitmap4,mPictureFileName2);
                    break;
                case SystemCapture2*2:
                    Uri uri2=data.getData();
                    Bitmap bitmap5= BitmapUtil.getBitmap(getApplicationContext(),uri2,mBitmapWidth);
                    getPictureResultHandle(imageView2,textView2,bitmap5,mPictureFileName2);
                    break;
                default:
                    showToast("图片传输失败，请重新取图", Toast.LENGTH_LONG);
                    break;
            }

        }
    }
}
