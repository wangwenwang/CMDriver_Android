package com.chenmaunion.app.cmdriver.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chenmaunion.app.cmdriver.R;
import com.chenmaunion.app.cmdriver.app.MyApplication;
import com.chenmaunion.app.cmdriver.bean.UserInfo;
import com.chenmaunion.app.cmdriver.constants.Constants;
import com.chenmaunion.app.cmdriver.httpclient.DriverAsyncHttpClient;
import com.chenmaunion.app.cmdriver.ui.activity.AboutWeActivity;
import com.chenmaunion.app.cmdriver.ui.activity.InformationActivity;
import com.chenmaunion.app.cmdriver.ui.activity.LoginActivity;
import com.chenmaunion.app.cmdriver.ui.activity.NormalProblemsActivity;
import com.chenmaunion.app.cmdriver.ui.activity.OpInstructionActivity;
import com.chenmaunion.app.cmdriver.ui.activity.ReportProblemActivity;
import com.chenmaunion.app.cmdriver.ui.activity.TruckTeamListActivity;
import com.chenmaunion.app.cmdriver.utils.GlideCircleTransform;
import com.chenmaunion.app.cmdriver.utils.SharedPreferencesUtils;
import com.kaidongyuan.app.basemodule.interfaces.AsyncHttpCallback;
import com.kaidongyuan.app.basemodule.ui.fragment.BaseLifecyclePrintFragment;
import com.kaidongyuan.app.basemodule.utils.nomalutils.BitmapUtil;
import com.kaidongyuan.app.basemodule.utils.nomalutils.FileUtil;
import com.kaidongyuan.app.basemodule.widget.MLog;
import com.kaidongyuan.app.basemodule.widget.SlidingTitleView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * ${PEOJECT_NAME}
 * Created by Administrator on 2017/2/7.
 */
public class MineFragment extends BaseLifecyclePrintFragment implements AsyncHttpCallback,View.OnClickListener{
    public boolean isupdate;
    public static boolean IsRefresh=false;
    private View parent;
    private SlidingTitleView titleView;
    private ImageView icon_mine;
    private TextView tv_help;
    private DriverAsyncHttpClient mClient;
    // private final String TAG_GetFleetList="GetFleetList";
    private final String TAG_ModifyUserInfo="ModifyUserInfo";
    private final String TAG_GetUserInfo="TAG_GetUserInfo";
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
    private UserInfo userinfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parent=inflater.inflate(R.layout.fragment_mine,container,false);
        mClient=new DriverAsyncHttpClient(this,this);
        initView();
        getUserInfo();
        return parent;
    }

    private void initView() {
        titleView= (SlidingTitleView) parent.findViewById(R.id.slidingtilte_fragment_mine);
        titleView.setMode(SlidingTitleView.MODE_NULL);
        titleView.setText(getString(R.string.mine_page));
        tv_help=titleView.getManagment();
        tv_help.setText("设置");
        tv_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                startActivity(intent);
//                showToastMsg("设置后台持续自动运行，信任‘"+getString(R.string.app_name)+"’应用授权");
                Intent intent=new Intent(getActivity(), InformationActivity.class);
                startActivity(intent);
            }
        });
        //在tv_help中设置角标图片
        Drawable dra=getResources().getDrawable(R.drawable.setting);
        dra.setBounds(0,0,dra.getMinimumWidth(),dra.getMinimumHeight());
        tv_help.setCompoundDrawables(dra,null,null,null);
        icon_mine= (ImageView) parent.findViewById(R.id.iv_mine_icon);
        icon_mine.setOnClickListener(this);
        parent.findViewById(R.id.ll_driver_invite).setOnClickListener(this);
        parent.findViewById(R.id.ll_mine_truckteams).setOnClickListener(this);
        parent.findViewById(R.id.ll_mine_information).setOnClickListener(this);
        parent.findViewById(R.id.bt_ExitAccount).setOnClickListener(this);
        parent.findViewById(R.id.ll_operating_instructions).setOnClickListener(this);
        parent.findViewById(R.id.ll_normal_problems).setOnClickListener(this);
        parent.findViewById(R.id.ll_about_we).setOnClickListener(this);
        parent.findViewById(R.id.ll_report_problem).setOnClickListener(this);
        parent.findViewById(R.id.ll_mine_identity).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (IsRefresh){
            getUserInfo();
            IsRefresh=false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void addPicture(String mPictureFileName, int systemCapture) {
        mTempPictureFileName=mPictureFileName;
        mTempRequestCode=systemCapture;
        showUpdataChoice();
    }

    private void showUpdataChoice() {
        choicsSnackbar = Snackbar.make(parent,"相册选取", Snackbar.LENGTH_INDEFINITE);
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
    public void getUserInfo() {
        Map<String,String> params=new HashMap<>();
        params.put("UserId",SharedPreferencesUtils.getUserId());
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.GetUserInfo,params,TAG_GetUserInfo);
    }
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.iv_mine_icon:
                if (choicsSnackbar!=null&&choicsSnackbar.isShown()){
                    choicsSnackbar.dismiss();
                    return;
                }else {
                    addPicture(mPictureFileName, SystemCapture);
                }
                break;
            case R.id.ll_normal_problems:
                intent=new Intent(getActivity(),NormalProblemsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_mine_truckteams:
                intent=new Intent(getActivity(), TruckTeamListActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_mine_information:
                intent=new Intent(getActivity(), InformationActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_driver_invite:
                showToastMsg("持续开发中");
                break;
            case R.id.ll_operating_instructions:
                intent=new Intent(getActivity(),OpInstructionActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_about_we:
                intent=new Intent(getActivity(),AboutWeActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_report_problem:
                intent=new Intent(getActivity(), ReportProblemActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_mine_identity:
                showToastMsg("持续开发中");
                break;
            case R.id.bt_ExitAccount:
                Intent intent2 = new Intent(getMContext(), LoginActivity.class);
                startActivity(intent2);
                MyApplication.IS_LOGIN=false;
                MyApplication.getInstance().AppExit(getMContext());
                break;
            default:
                break;
        }
    }
    private void getPictureResultHandle(ImageView imageView1, Bitmap bitmap, String mPictureFileName) {
        if (bitmap!=null){
            Bitmap squrebitmap= BitmapUtil.resizeSquareImage(bitmap,400);
            MLog.w("squrebitmap:"+squrebitmap.getWidth()+"|"+squrebitmap.getHeight());
            String strpicture = BitmapUtil.changeBitmapToString(squrebitmap);
            modifyUserInfo("","",strpicture);

        }
    }

    private void modifyUserInfo(String USER_CODE,String ADDRESS,String Avatar) {
        if (userinfo==null){
            userinfo=new UserInfo();
        }
        userinfo.setUserId(SharedPreferencesUtils.getUserId());
        userinfo.setADDRESS("");
        userinfo.setAvatar(Avatar);
        userinfo.setUSER_CODE("");
        Map<String,String> params=new HashMap<>();
        params.put("UserInfo",JSON.toJSONString(userinfo));
        params.put("strLicense","");
        mClient.sendRequest(Constants.URL.ModifyUserInfo,params,TAG_ModifyUserInfo);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mClient.cancleRequest(TAG_GetUserInfo,TAG_ModifyUserInfo);
    }

    @Override
    public void postSuccessMsg(String msg, String request_tag) {
        if (msg.equals("error")){
            switch (request_tag){
                case TAG_ModifyUserInfo:
                    showToastMsg("头像上传失败");
                    return;
                case TAG_GetUserInfo:
                    showToastMsg("获取头像信息失败");

                    break;
                default:
                    return;
            }

        }else if (request_tag.equals(TAG_ModifyUserInfo)){
            JSONObject jos= JSON.parseObject(msg);
            int type=jos.getInteger("type");
            if (type==1){
                getUserInfo();
            }else {
                showToastMsg(msg);
            }
        }else if (request_tag.equals(TAG_GetUserInfo)){
            JSONObject jos= JSON.parseObject(msg);
            userinfo = JSON.parseObject(jos.getString("result"),UserInfo.class);
            Glide.with(MineFragment.this).load(Constants.URL.Load_Url+"Uploadfile/"+ userinfo.getAvatar()).error(R.drawable.mine_icon).crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .transform(new GlideCircleTransform(getMContext())).into(icon_mine);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){
            switch (requestCode){
                case SystemCapture:
                    Bitmap bitmap0= BitmapUtil.resizeImage(mTempPictureFilePath,mBitmapWidth);
                    getPictureResultHandle(icon_mine,bitmap0, mPictureFileName);
                    break;
                case SystemCapture*2:
                    Uri uri=data.getData();
                    Bitmap bitmap1= BitmapUtil.getBitmap(getMContext(),uri,mBitmapWidth);
                    getPictureResultHandle(icon_mine,bitmap1,mPictureFileName);
                    break;
                default:
                    break;


            }
        }
    }
}
